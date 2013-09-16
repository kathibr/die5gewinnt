package de.dhbw.die5gewinnt.controller.logic;

import java.util.ArrayList;

import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.controller.db.DBInserts;
import de.dhbw.die5gewinnt.controller.db.DBUpdates;
import de.dhbw.die5gewinnt.model.*;

public class ModelController {
	
	private Game game;
	private ArrayList<Set> sets;
	private ArrayList<Move> moves;
	
	public static ModelController getModelController() {
		return Controller.getController().getModelController();
	}
	
	/* Create new game objects */
	public Game newGame(String name, String player) {
		Game newGame = null;
				Set[] sets = new Set[3];
				int[] score = new int[2];
				score[0] = score[1] = 0;
		newGame = new Game(name, sets, score, false, player);
		newGame = DBInserts.insertGame(newGame);
		this.setGame(newGame);
		return this.getGame();
	}
	
//	public Set newSet() {
//		if(this.sets.size() == 3) {
//			System.err.println("--- There are already 3 sets!");
//			return this.getSet(3);
//		} else {
//			int newIndex = this.addSet(ModelController.createNewSet());
//			return this.getSet(newIndex);
//		}
//	}
//	
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
	
	private void setSet(int index) {}
	
	private int addSet(Set set) {
		this.sets.add(set);
		return this.sets.size();
	}
	
	private void setMove(int index) {}
	
	private int addMove(Move move) {
		this.moves.add(move);
		return this.sets.size();
	}
	
}