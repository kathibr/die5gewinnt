package de.dhbw.die5gewinnt.test;

import de.dhbw.die5gewinnt.controller.db.DBConnector;
import de.dhbw.die5gewinnt.controller.db.DBSelects;
//import de.dhbw.die5gewinnt.controller.fileaccess.FileChecker;
import de.dhbw.die5gewinnt.model.*;

public class TestDBScript {
	
	public static void main(String[] args) {
		// Init the auto increment keys for Game, Set and Move
		DBSelects.initAutoIncrementKeys();
		
		// Create an int-Array field for object of set
		Move[][] field = new Move[7][6];
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++)
				field[i][j] = new Move();
		}
		
		// Create an int-Array columnHeight for object set
		int[] columnHeight = new int[7];
		for(int i = 0; i < 7; i++)
			columnHeight[i] = 0;
		
		// Create an Set-Array sets for object game
		Set[] sets = new Set[3];
		sets[0] = new Set(new Move[42], field, columnHeight, true, 0);	
		sets[1] = new Set(new Move[42], field, columnHeight, false, 0);
		sets[2] = new Set(new Move[42], field, columnHeight, false, 0);
	
		// Create an int-Array score for object game
		int[] score = new int[2];
		score[0] = 2;
		score[1] = 1;
		
		// Create an Game-Object
//		Game game = new Game(0, "die5gewinnt", sets, score, true, "X");
		
//		game = DBInserts.insertGame(game);
//		sets[0] = DBInserts.insertSet(sets[0]);
//		sets[1] = DBInserts.insertSet(sets[1]);
//		sets[2] = DBInserts.insertSet(sets[2]);
		
		// Test SELECT-Queries
//		Game selectGame = DBSelects.selectGame(AutoIncrementKeys.getGameId());
//		Set selectSet = DBSelects.selectSet(AutoIncrementKeys.getSetId());
		
//		System.out.println("Game.name: "+selectGame.getName());
//		System.out.println("Set.columnHeight: "+selectGame.getSets()[0].getColumnHeight()[0]);

		// Close the HSQL Database Connection
	    DBConnector.closeDBConnection();
	}

}