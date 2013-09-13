package de.dhbw.die5gewinnt.controller.db;

//import de.dhbw.die5gewinnt.controller.fileaccess.FileChecker;
import de.dhbw.die5gewinnt.model.*;

public class TestDBScript {
	
	public static void main(String[] args) {
		// Init the auto increment keys for Game, Set and Move
		DBSelects.initAutoIncrementKeys();
		
		// Create an int-Array field for object of set
		int[][] field = new int[7][6];
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++)
				field[i][j] = 0;
		}
		
		// Create an int-Array columnHeight for object set
		int[] columnHeight = new int[7];
		for(int i = 0; i < 7; i++)
			columnHeight[i] = 0;
		
		// Create an Set-Array sets for object game
		Set[] sets = new Set[3];
		sets[0] = new Set(1, new Move[42], field, columnHeight);	
		sets[1] = new Set(2, new Move[42], field, columnHeight);
		sets[2] = new Set(3, new Move[42], field, columnHeight);
	
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
	    
//	    FileChecker fileChecker = new FileChecker("","file.txt");
//	    fileChecker.start();    
//	    while(!fileChecker.getExistingServerFile());
//	    {
//	    	System.out.println("TestDBScript: "+fileChecker.getExistingServerFile());
//	    	System.out.println("TestDBScript");
//	    }
//	    System.out.println("while-Schleife verlassen, d.h. die Datei wurde entdeckt!");
	    String[] gameNames = DBSelects.selectGameNames();
	    for(int i = 0; i < gameNames.length; i++)
	    	System.out.println(gameNames[i]);
	}

}