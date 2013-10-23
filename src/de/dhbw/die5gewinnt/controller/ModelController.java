package de.dhbw.die5gewinnt.controller;


import de.dhbw.die5gewinnt.controller.algorithm.AlgorithmManager;
import de.dhbw.die5gewinnt.controller.algorithm.MastermindAlgorithm;
import de.dhbw.die5gewinnt.controller.communication.TXTWriter;
import de.dhbw.die5gewinnt.controller.communication.XMLReader;
import de.dhbw.die5gewinnt.controller.db.DBInserts;
import de.dhbw.die5gewinnt.model.Game;
import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.ServerFile;
import de.dhbw.die5gewinnt.model.Set;


public class ModelController {
	
	private Game game;
	private Set set = new Set();
	private int score[];
	private Move move;
	private Set[] sets;
//	private boolean forceStop = false;

	
	private AlgorithmManager AlgManager;
	private ServerFile serverFile;


	private int moveId;
	private int setId;
	private int column, row;
	private int[] columnHeight;

	private Move[][] field = new Move[7][6];
	private Move[] moves = new Move[42];
	private String ownPlayer, opponentPlayer;
	
	private final int YELLOW = 1;
	private final int RED = 2;
	
	

	public void startSet(){
		
		set = newSet();
		sets[setId] = set;
		
		if (game.getPlayer()=="X")
		{
			ownPlayer = "X";
			opponentPlayer = "O";
		}
		else
		{
			ownPlayer = "O";
			opponentPlayer = "X";
		}
//		i = 0;
		
		do{
			serverFile = new XMLReader().getServerFile();
			
			if (serverFile.getApproval()==true){
				if (serverFile.getOpponentMove() == -1)
				{
					proceedOwnMove();
				}
				else 
				{
					proceedOpponentMove();
					proceedOwnMove();
				}
			}
			
			else{
				identifyWinner();

				System.out.println("Set is over");
				game.setScore(score);
				setId++;
				
				if(setId<3){
					set = newSet();
					sets[setId] = set;
					moveId = 0;	
				}
				Controller.getController().getPlayingFieldController().updateDisplay(setId, score);
				Controller.getController().playingFieldController.handleEndSet();
			}

			
		}while(true);

	}
	
	private void proceedOpponentMove()
	{
		try {
			// Calculate move
			column = serverFile.getOpponentMove();
			columnHeight = set.getColumnHeight();
			row = columnHeight[column];
			columnHeight[column]++;
			set.setColumnHeight(columnHeight);

			// Show move
			Controller.getController().getPlayingFieldController().showMove(column, row, YELLOW);
			System.out.println("Opponent move: " + " Spalte " + column +", Zeile "+row);
			
			// Save move
			move = new Move(row, column, opponentPlayer);
			move = DBInserts.insertMove(move,game.getId(),set.getId());
			moves[moveId] = move;
			field[column][row]=move;
			moveId++;			
		} catch (Exception e) {
			System.out.println("Feld ist voll - ung�ltiger Zug");
		}

	}
	
	
	private void proceedOwnMove()
	{	 
		// Calculate move
		//Later with algorithm
		do{
//			column = (int)((Math.random()) * 6);
//			columnHeight = set.getColumnHeight();
		}while(columnHeight[column]>6);
		MastermindAlgorithm algorithm = new MastermindAlgorithm(set);
		column = algorithm.calcNextMove();

		row = columnHeight[column];
		columnHeight[column]++;
		set.setColumnHeight(columnHeight);

		// Write data
		TXTWriter.setAgentFile(column);
		
		// Show move
		Controller.getController().getPlayingFieldController().showMove(column, row, RED);
		System.out.println("Own move: " + " Spalte " + column +", Zeile "+row);
		
		// Save move 
		move = new Move(row, column, ownPlayer);
		move = DBInserts.insertMove(move,game.getId(),set.getId());
		moves[moveId] = move;		
		field[column][row]=move;
		
		moveId++;
	}
	
	public void identifyWinner(){
//		if (serverFile.getOpponentMove() != -1){
		if(serverFile.getWinner().equals("Spieler "+opponentPlayer)){
			proceedOpponentMove();
			System.out.println("Wir haben verloren :(");
			set.setWinner(false);
			int scoreNr = score[1];
			score[1]=scoreNr+2;
		}
		else if (serverFile.getWinner().equals("Spieler "+ownPlayer)) {
			System.out.println("Wir haben gewonnen :)");
			set.setWinner(true);
			int scoreNr = score[0];
			score[0] =scoreNr+2;
		} 
		else {
			System.out.println("ung�ltig!");
		}
		
	}
		

	/* Create new game objects */
	public Game newGame(String name, String player, String opponentName) {
		Game newGame = null;
				sets = new Set[3];
				score = new int[2];
				score[0] = score[1] = 0;
		newGame = new Game(name, sets, score, false, player, opponentName);
		newGame = DBInserts.insertGame(newGame);
		setId=0;
		this.setGame(newGame);
		return this.getGame();
	}
	
	public Set newSet() {
		Set newSet = null;
			moves = new Move[42];
			field = new Move[7][6];
			columnHeight = new int[7];

			newSet = new Set(moves, field, columnHeight, false);
			newSet = DBInserts.insertSet(newSet);
			return newSet;
		
			/*
			
		if(this.sets.size() == 3) {
			System.err.println("--- There are already 3 sets!");
			return this.getSet(3);
		} else {
			newSet = new Set(moves, field, columnHeight);
//			newSet = DBInserts.insertSet(newSet);
			int newIndex = this.addSet(newSet);
			return this.getSet(newIndex);
		}
		*/
	}
	
	
//	public Move newMove() {
//		if(this.moves.size() == 42) {
//			System.err.println("--- There are already 42 moves!");
//			return this.getMove(42);
//		} else {
//			int newIndex = this.addMove(ModelController.createNewMove());
//			return this.getMove(newIndex);
//		}
//	}
	/*
	public void deleteCurrentGame() {
		if(this.game != null)
			DBUpdates.updateGame(this.game);
		this.game = null;
		for(int i = 0; i < this.sets.size(); i++) {
			if(this.sets.get(i) != null)
				DBUpdates.updateSet(sets.get(i));
			this.sets.remove(i);
		}
		for(int i = 0; i < this.moves.size(); i++) {
			if(this.moves.get(i) != null)
				DBUpdates.updateMove(this.moves.get(i));
			this.moves.remove(i);
		}
	}
	*/
	
	/* GETTER-Methods */
	public Game getGame() {
		return this.game;
	}
	
	public Set getSet(int index) {
		return this.sets[index];
	}
	
	public Set[] getSets() {
		return this.sets;
	}
	
	public Move getMove(int index) {
		return moves[index];
	}
	
	public Move[] getMoves() {
		return this.moves;
	}
	
	/* SETTER-Methods */
	private void setGame(Game game) {
		this.game = game;
	}

	public String getWinner() {
		return serverFile.getWinner();
		
	}

//	public void endSet() {
////		forceStop = true;
//		//Sieger anzeigen -> Popup?/ updaten der Oberfl�che
//	}
	/*
	public boolean getForceStop(){
		return this.forceStop;
	}
	*/
//	private void setSet(int index) {}
	/*
	@SuppressWarnings("unused")
	private int addSet(Set set) {
		this.sets.add(set);
		return this.sets.size();
	}
	
	@SuppressWarnings("unused")
	private void addMove(){
	}
	*/
//	
//	private void setMove(int index) {}
//	
//	private int addMove(Move move) {
//		this.moves.add(move);
//		return this.sets.size();
//	}
	
	
	
}
