package de.dhbw.die5gewinnt.controller;


import de.dhbw.die5gewinnt.controller.algorithm.MastermindAlgorithm;
import de.dhbw.die5gewinnt.controller.communication.TXTWriter;
import de.dhbw.die5gewinnt.controller.communication.XMLReader;
import de.dhbw.die5gewinnt.controller.db.DBDeletes;
import de.dhbw.die5gewinnt.controller.db.DBInserts;
import de.dhbw.die5gewinnt.controller.db.DBUpdates;
import de.dhbw.die5gewinnt.model.Game;
import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.ServerFile;
import de.dhbw.die5gewinnt.model.Set;


public class ModelController {
	
	private ServerFile serverFile;
	private Game game;
	private Set set = new Set();
	private Move move;
	
	
	private int score[];
	private Set[] sets;

	private int moveId;
	private int setCounter;
	private int column, row;
	private int[] columnHeight;

	private Move[][] field;
	private Move[] moves;
	private String ownPlayer, opponentPlayer;
	
	private final int YELLOW = 1;
	private final int RED = 2;
	
	
	public void startSet(){
		Controller.getController().getPlayingFieldController().disappearLbStatus();
		System.out.println("Modelcontroller satz: "+setCounter+ " mit Score "+ score[0]+" - " +score[1]);
		moveId = 0;	
		sets[setCounter-1] = newSet();
		set = sets[setCounter-1];
		Controller.getController().getCommunicationController().setGameOver(false);
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
	}

	public void startGame(){
		
		startSet();
	
		do{
			serverFile = new XMLReader().getServerFile();
			
			if (serverFile.getApproval()==true){
				Controller.getController().getPlayingFieldController().disappearLbStatus();
				if (serverFile.getOpponentMove() == -1)
				{
					proceedOwnMove();
					set.setFirstMove(true);
					Controller.getController().getPlayingFieldController().setNextMoveField(game.getOpponentName());
				}
				else 
				{
					proceedOpponentMove();
					Controller.getController().getPlayingFieldController().setNextMoveField("die5gewinnt");
					proceedOwnMove();
					Controller.getController().getPlayingFieldController().setNextMoveField(game.getOpponentName());
				}
				set.setMoves(moves);
				set.setColumnHeight(columnHeight);
				set.setField(field);
			}
			
			else if(!serverFile.getWinner().equals("offen")){
				identifyWinner();

				System.out.println("Set is over");
//				game.setScore(score);

				Controller.getController().getPlayingFieldController().updateValues(setCounter, score);
				Controller.getController().playingFieldController.endNormalSet();
			}
			else {
				
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
//			System.out.println("Opponent move: " + " Spalte " + column +", Zeile "+row);
			
			// Save move
			move = new Move(row, column, opponentPlayer);
			move = DBInserts.insertMove(move,game.getId(),set.getId());
			moves[moveId] = move;
			field[column][row]=move;
			moveId++;			
		} catch (Exception e) {
			System.out.println("Feld ist voll - ungültiger Zug");
		}

	}
	
	
	private void proceedOwnMove()
	{	 
//		Random Algorithmus
//		do{
//			column = (int)((Math.random()) * 6);
//			columnHeight = set.getColumnHeight();
//		}while(columnHeight[column]>6);
		
		// Calculate move
		MastermindAlgorithm algorithm = new MastermindAlgorithm(set);
		column = algorithm.calcNextMove();

		row = columnHeight[column];
		columnHeight[column]++;
		set.setColumnHeight(columnHeight);

		// Write data
		TXTWriter.setAgentFile(column);
		
		// Show move
		Controller.getController().getPlayingFieldController().showMove(column, row, RED);
//		System.out.println("Own move: " + " Spalte " + column +", Zeile "+row);
		
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

			int scoreNr = score[1];
			if (isFieldFull()) {
				score[1]=scoreNr+1;
				set.setStatus(2);
				Controller.getController().getPlayingFieldController().setTextForStatus("Spielfeld voll - Spieler "+ opponentPlayer+ "gewinnt");
			}
			else {
				score[1]=scoreNr+2;
				set.setStatus(0);
				Controller.getController().getPlayingFieldController().setTextForStatus("Wir haben verloren :(");
			}
		}
		else if (serverFile.getWinner().equals("Spieler "+ownPlayer)) {
			System.out.println("Wir haben gewonnen :)");

			int scoreNr = score[0];
			if (isFieldFull()) {
				score[0]=scoreNr+1;
				set.setStatus(3);
				Controller.getController().getPlayingFieldController().setTextForStatus("Spielfeld voll - Spieler "+ ownPlayer+ "gewinnt");
			}
			else {
				score[0]=scoreNr+2;
				set.setStatus(1);
				Controller.getController().getPlayingFieldController().setTextForStatus("Wir haben gewonnen :)");
			}
		} 
		else {
			System.out.println("ungültig!");
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
		setCounter=1;
		this.setGame(newGame);
		this.game = newGame;
		return this.getGame();
	}
	
	public Set newSet() {
		System.out.println("neues Set wird erstellt");
		Set newSet = null;
		moves = new Move[42];
		field = new Move[7][6];
		columnHeight = new int[7];

			newSet = new Set(moves, field, columnHeight, false, 0);
			newSet = DBInserts.insertSet(newSet);
			return newSet;
	
	}

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
	public void updateScore(int[] score){
		this.score=score;
		game.setScore(score);
	}
	
	public int deleteSet(){
//		System.out.println("deleteSet");
		DBDeletes.deleteSet(set.getId());
		set = null;
//		System.out.println("ModContr. setCounter: "+setCounter);
		Controller.getController().getPlayingFieldController().setTextForStatus("Satz wurde gelöscht");
		return setCounter;
	}
	public void updateSet(){
//		game.setScore(score);
//		System.out.println("updateSet");
		DBUpdates.updateSet(set);
	}
	public void updateGame(){
//		System.out.println("updateGame");
		DBUpdates.updateGame(game);
	}
	
	public boolean isFieldFull(){
		columnHeight = set.getColumnHeight();
		for(int i = 0; i < columnHeight.length; i++){
			if(columnHeight[i] != 5)return false;
		}
		return true;
	}
	public void setSetId(int setCounter){
		this.setCounter = setCounter;
	}
	
}
