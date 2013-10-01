package de.dhbw.die5gewinnt.controller.logic;




import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.controller.algorithm.AlgorithmManager;
import de.dhbw.die5gewinnt.controller.communication.CommunicationController;
import de.dhbw.die5gewinnt.controller.communication.TXTWriter;
import de.dhbw.die5gewinnt.controller.communication.XMLReader;
import de.dhbw.die5gewinnt.model.Game;
import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.ServerFile;
import de.dhbw.die5gewinnt.model.Set;


public class ModelController {
	
	private Game game;
	private Set set = new Set();		
	private Move move;
	private Set[] sets;
	private int i;


	
	private AlgorithmManager AlgManager;
	private ServerFile serverFile;
	private final  Lock lock             = new ReentrantLock();
	private final  Condition conditionServerFile     = lock.newCondition();

	
	private int setId = 0;
	private int column, row;
	private int[] columnheight;
	@SuppressWarnings("unused")
	private Move[][] field = new Move[7][6];
	private Move[] moves = new Move[42];
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

	public void startSet(){
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
		
		for(int i = 0; i<42; i++){
			serverFile = new XMLReader().getServerFile();
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
	}
	
	private void proceedOpponentMove()
	{
		// Calculate move
		column = serverFile.getOpponentMove();
		columnheight = set.getColumnHeight();
		row = columnheight[column];
		columnheight[column]++;
		set.setColumnHeight(columnheight);

		// Show move
		Controller.getController().getPlayingFieldController().showMove(column, row, YELLOW);
		System.out.println("Opponent move: " + " Spalte " + column +", Zeile "+row);
		
		// Save move
		move = new Move(row, column, opponentPlayer);
		moves[i] = move;
		
		i++;
	}
	
	
	private void proceedOwnMove()
	{	 
		// Calculate move
		column = (int)((Math.random()) * 7 + 1);
		columnheight = set.getColumnHeight();
		row = columnheight[column];
		columnheight[column]++;
		set.setColumnHeight(columnheight);

		// Write data
		TXTWriter.setAgentFile(column);
		
		// Show move
		Controller.getController().getPlayingFieldController().showMove(column, row, RED);
		System.out.println("Own move: " + " Spalte " + column +", Zeile "+row);
		
		// Save move 
		move = new Move(row, column, ownPlayer);
		moves[i] = move;		
		
		i++;
	}
		

					
//		sets = getGame().getSets();
//		sets[setId] = newSet();
//		set = newSet();
//		moves = set.getMoves();
//

			
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
