package de.dhbw.die5gewinnt.controller.logic;

import de.dhbw.die5gewinnt.controller.db.DBInserts;
import de.dhbw.die5gewinnt.model.*;

public class ModelController {
	
	private int moveID;
	
	public void ownMove() {}
	
	public void OpponentMove() {}
	
	public void showMove(){
		//Aufzeigen des Steines
		moveID++;	
	}
	
	public static Game createNewGame(String name, String player) {
		Game game = null;
			Set[] sets = new Set[3];
			int[] score = new int[2];
		game = new Game(name, sets, score, false, player);
		game = DBInserts.insertGame(game);
		return game;
	}
	
	public static Set createNewSet() {
		return new Set();
	}
	
	public static Move createNewMove() {
		return new Move();
	}
}