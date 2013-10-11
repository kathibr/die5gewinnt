package de.dhbw.die5gewinnt.test;

import java.io.IOException;

import org.jdom2.JDOMException;

import de.dhbw.die5gewinnt.controller.db.DBConnector;
import de.dhbw.die5gewinnt.controller.db.DBInserts;
import de.dhbw.die5gewinnt.controller.db.DBSelects;
import de.dhbw.die5gewinnt.model.*;

public class Main {

	public static void main(String[] args) throws JDOMException, IOException, InterruptedException {		
		// (1) Init the auto increment keys for Game, Set and Move
		DBSelects.initAutoIncrementKeys();
		
		// --- Test to insert sample DB data
//		loadSampleDBData();
		
		// (2) Create a new game
//		Controller.getController().getModelController().newGame("Battle Royal", "X");
		
		// (3) Set the path and the names for the serverFile and the agentFile
//		CommunicationCenter.getCommunicationCenter("", "server2spielero.xml", "spielero2server.txt");
		
		// (4) Create a new set
//		int[][] field = new int[7][6];
//		int[] columnHeight = new int[7];
//		for(int i = 0; i < columnHeight.length; i++)
//			columnHeight[i] = 2;
//		
//		Set set = new Set(null, field, columnHeight);
//		set = DBInserts.insertSet(set);
		
//		// (5) Wait for the first serverFile
//		ServerFile serverFile = XMLReader.getServerFile();
//		if(serverFile.getApproval()) {
//			// freigabe: true => Agent begins or Opponent took a move => Agent have to start his AlgorithmManager
//			switch(serverFile.getOpponentMove()) {
//			case -1: // Own agent begins with his first move
//				break;
//			case 0:		// Opponent choose the first column
//				break;
//			case 1:		// Opponent choose the second column
//				break;
//			case 2:		// Opponent choose the third column
//				break;
//			case 3:		// Opponent choose the fourth column
//				break;
//			case 4:		// Opponent choose the fifth column
//				break;
//			case 5:		// Opponent choose the sixth column
//				break;
//			case 6:		// Opponent choose the seventh column
//				break;
//			}
//		} else {
//			// freigabe: false => One player won / Server ends the game => only refresh the playing field
//		}
//	
//		// Load the AlgorithmManager and get the next Move
////		System.out.println(AlgorithmManager.getAlgorithmManager().getNextColumn(System.currentTimeMillis()));
//		TXTWriter.setAgentFile(AlgorithmManager.getAlgorithmManager().getNextColumn(System.currentTimeMillis()));
//		
//		// Close the HSQL Database Connection
	    DBConnector.closeDBConnection();
//	    
//	    // Delete the last AgentFile
//	    TXTWriter.deleteAgentFile();
	}
	
	/* TEST-Methods for Database testing */
	@SuppressWarnings("unused")
	private static void loadSampleDBData() {
		Game game = null;
			int[] score = new int[2];
			score[0] = 2; score[1] = 1;
		Set set = null;
			Move[] moves = new Move[42];
			Move[][] field = new Move[7][6];
			int[] columnHeight = new int[7];
			for(int i = 0; i < columnHeight.length; i++)
				columnHeight[i] = 2;
		Move move = null;
		
		for(int i = 0; i < 4; i++) { // Create a game
			game = new Game("Spiel-Nr. "+i, null, score, false, "X", "blutwurst1");
			game = DBInserts.insertGame(game);
			for(int j = 0; j < (int) (Math.random() * (3 - 2) + 2); j++) { // Create a set
				set = new Set(moves, field, columnHeight);
				set = DBInserts.insertSet(set);
				for(int k = 0; k < (int) (Math.random() * (40 - 15) + 15); k++) { // Create a move
					move = new Move((int) (Math.random() * 7), (int) (Math.random() * 6), "X");
					move = DBInserts.insertMove(move, game.getId(), set.getId());
				}
			}
		}
	}

}