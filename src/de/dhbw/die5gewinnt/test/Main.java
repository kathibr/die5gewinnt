package de.dhbw.die5gewinnt.test;

import java.io.IOException;

import org.jdom2.JDOMException;

import de.dhbw.die5gewinnt.controller.algorithm.AlgorithmManager;
import de.dhbw.die5gewinnt.controller.communication.CommunicationCenter;
import de.dhbw.die5gewinnt.controller.communication.XMLReader;
import de.dhbw.die5gewinnt.controller.communication.TXTWriter;
import de.dhbw.die5gewinnt.controller.communication.XMLWriter;
import de.dhbw.die5gewinnt.controller.db.DBConnector;
import de.dhbw.die5gewinnt.controller.db.DBInserts;
import de.dhbw.die5gewinnt.controller.db.DBSelects;
import de.dhbw.die5gewinnt.model.*;

public class Main {

	public static void main(String[] args) throws JDOMException, IOException, InterruptedException {
		// Init the auto increment keys for Game, Set and Move
		DBSelects.initAutoIncrementKeys();
		
		// Test to read a XML File
		CommunicationCenter.getCommunicationCenter("", "server2spielero.xml", "spielero2server.txt");
		readXML();
		TXTWriter.setAgentFile(2);
		
		/* Test to insert sample DB data */
//		loadSampleDBData();
	
		// Load the AlgorithmManager and get the next Move
		System.out.println(AlgorithmManager.getAlgorithmManager().getNextColumn(System.currentTimeMillis()));
		
		// Close the HSQL Database Connection
	    DBConnector.closeDBConnection();
	}
	
	private static void readXML() {
		ServerFile serverFile = XMLReader.getServerFile();
		
		System.out.println(serverFile.getApproval());
		System.out.println(serverFile.getSetStatus());
		System.out.println(serverFile.getOpponentMove());
		System.out.println(serverFile.getWinner());
	}
	
	/* TEST-Methods for Database testing */
	private static void loadSampleDBData() {
		Game game = null;
			int[] score = new int[2];
			score[0] = 2; score[1] = 1;
		Set set = null;
			int[][] field = new int[7][6];
			int[] columnHeight = new int[7];
			for(int i = 0; i < columnHeight.length; i++)
				columnHeight[i] = 2;
		Move move = null;
		
		for(int i = 0; i < 4; i++) { // Create a game
			game = new Game("Spiel-Nr. "+i, null, score, false, "X");
			game = DBInserts.insertGame(game);
			for(int j = 0; j < (int) (Math.random() * (3 - 2) + 2); j++) { // Create a set
				set = new Set(null, field, columnHeight);
				set = DBInserts.insertSet(set);
				for(int k = 0; k < (int) (Math.random() * (40 - 15) + 15); k++) { // Create a move
					move = new Move((int) (Math.random() * 7), (int) (Math.random() * 6), "X");
					move = DBInserts.insertMove(move, game.getId(), set.getId());
				}
			}
		}
	}

}