package de.dhbw.die5gewinnt.controller.logic;




import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.controller.algorithm.AlgorithmManager;
import de.dhbw.die5gewinnt.controller.communication.CommunicationController;
import de.dhbw.die5gewinnt.controller.communication.XMLReader;
import de.dhbw.die5gewinnt.model.Game;
import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.ServerFile;
import de.dhbw.die5gewinnt.model.Set;


public class ModelController {
	
	private Game game;
	private Set set;		
	private Move move;
	private Set[] sets;
	private Move[] moves;

	
	private AlgorithmManager AlgManager;
	private ServerFile serverFile;
	private final  Lock lock             = new ReentrantLock();
	private final  Condition conditionServerFile     = lock.newCondition();

	
	private int setId = 0;
	private int column, row;
	private int[] columnheight;
	@SuppressWarnings("unused")
	private Move[][] field = new Move[7][6];
	private String ownPlayer, opponentPlayer;
	
	private final int YELLOW = 1;
	private final int RED = 2;
	
	
	public void startGame(String name, String player){
//		System.out.println("Spiel "+ name + player);		
		
		//AlgManager = AlgorithmManager.getAlgorithmManager();
		
		
		//game = newGame(name,player);
		//game.getSets();
		//sets[0] = newSet();
	}
	public void test()
	{
		while (true){
		System.out.println("TEST");}
	}
	
	public void startSet(){
		System.out.println("StartSet");
		
		XMLReader xMLReader = new XMLReader();
		xMLReader.start();
	}
	
	public void continueSet(ServerFile serverFile)
	{
		Controller.getController().getPlayingFieldController().showMove(2, 2, 2);
	}
		
//	if (game.getPlayer()=="X")
//	{
//		ownPlayer = "X";
//		opponentPlayer = "O";
//	}
//	else
//	{
//		ownPlayer = "O";
//		opponentPlayer = "X";
//	}
//	
//	
//	while (true)
//	{
//		serverFile = CommunicationController.getServerFile();
//		if (serverFile != null)
//		{
//			System.out.println("ServerFile eingelesen");
//			break;
//		}
//	}
					
//		sets = getGame().getSets();
//		sets[setId] = newSet();
//		set = newSet();
//		moves = set.getMoves();
//
//		

//
//		serverFile = new ServerFile();


//		System.out.println(serverFile.getOpponentMove() + "nic");
		
		
//		for(int i = 0; i<42; i++){
//			lock.lock();
//			System.out.println("Model lock");
//			try {
//				conditionServerFile.await();
//				System.out.println("Hallo ich warte im ModelController");
//
//			if (serverFile.getOpponentMove() != -1)
//			{
//				column = serverFile.getOpponentMove();
//				columnheight = set.getColumnHeight();
//				row = columnheight[column];
//				
//				columnheight[column]++;
//				set.setColumnHeight(columnheight);
//
//				move = new Move(row, column, opponentPlayer);
//				moves[i] = move;
//				Controller.getController().getPlayingFieldController().showMove(column, row, YELLOW);
//				System.out.println("Gegnerzug: " + " Spalte " + column +", Zeile "+row);
//				i++;
//			}
//		
//			//eigenen Zug erstellen
//			row = 0;
//			do{
//				//column = AlgManager.getNextColumn(100);
//				column = (int) (Math.random() * 7);
//				columnheight = set.getColumnHeight();
//				row = columnheight[column];
//			   
//			   
//			}while(row==6);
//
//			columnheight[column]++;
//			set.setColumnHeight(columnheight);
//			
//			move = new Move(row, column, ownPlayer);
//			moves[i]=move;
//			Controller.getController().getPlayingFieldController().showMove(column, row, RED);
//			System.out.println("eigener Zug: " + " Spalte " + column +", Zeile "+row);
//			i++;	
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("Exception wait Model"+e.getMessage());
//			}
//			finally{
//				lock.unlock();
//				System.out.println("Model unlock");
//			}
//
//		}
			


		
	
		
	/* Create new game objects */
	public Game newGame(String name, String player) {
		Game newGame = null;
				Set[] sets = new Set[3];
				int[] score = new int[2];
				score[0] = score[1] = 0;
		newGame = new Game(name, sets, score, false, player);
//		newGame = DBInserts.insertGame(newGame);
		this.setGame(newGame);
		return this.getGame();
	}
	
	public Set newSet() {
		Set newSet = null;
			Move[] moves = new Move[42];
			Move[][] field = new Move[7][6];
			int[] columnHeight = new int[7];

			newSet = new Set(moves, field, columnHeight);
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
