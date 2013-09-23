package de.dhbw.die5gewinnt.controller.logic;

import java.util.ArrayList;

import de.dhbw.die5gewinnt.PlayingFieldController;
import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.controller.db.DBInserts;
import de.dhbw.die5gewinnt.controller.db.DBUpdates;
import de.dhbw.die5gewinnt.model.*;
import de.dhbw.die5gewinnt.controller.algorithm.*;

public class ModelController {
	
	private Game game;
	private Set set;																																																																																																																																																																																																																																																																				
	private ArrayList<Set> sets;
	private ArrayList<Move> moves;
	private AlgorithmManager AlgManager;
	
	private int column, row, color;
	private int[] columnheight;
	
	
	public void startGame(String name, String player){
		System.out.println("Spiel "+ name + player);		
		
		AlgManager = AlgorithmManager.getAlgorithmManager();
		   
		//game = newGame(name,player);
		//game.getSets();
		//sets[0] = newSet();
		
		set = newSet();
		Move[] moves = set.getMoves();
		
		for (int i = 0;i<42;i++){
			
		   Move move = moves[i];
		   row = 0;
		   color =1;
		   
		   do{
			   //column = AlgManager.getNextColumn(100);
			   column = (int) (Math.random() * 6);
			   columnheight = set.getColumnHeight();
			   row = columnheight[column];
			   
		   }while(row==7);
		   
		   //move = new Move(row, column, player);
		   if (color==1) color = 2;
		   else if (color==2) color =1;
		   System.out.println("Stein mit Spalte "+column+" und Zeile "+ row);
		   //Anzeige des Schrittes durch showMove 
		   
		   
		   columnheight[column]++;
		   set.setColumnHeight(columnheight);
		   
			
		}
		
		
	}
		
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
	
	/* GETTER-Methods */
	public Game getGame() {
		return this.game;
	}
	
	public Set getSet(int index) {
		return this.sets.get(index);
	}
	
	public ArrayList<Set> getSets() {
		return this.sets;
	}
	
	public Move getMove(int index) {
		return this.moves.get(index);
	}
	
	public ArrayList<Move> getMoves() {
		return this.moves;
	}
	
	/* SETTER-Methods */
	private void setGame(Game game) {
		this.game = game;
	}
	
//	private void setSet(int index) {}
	
	private int addSet(Set set) {
		this.sets.add(set);
		return this.sets.size();
	}
	
	private void addMove(){
	}
//	
//	private void setMove(int index) {}
//	
//	private int addMove(Move move) {
//		this.moves.add(move);
//		return this.sets.size();
//	}
	
}