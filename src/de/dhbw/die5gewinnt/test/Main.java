package de.dhbw.die5gewinnt.test;

import java.io.IOException;

import org.jdom2.JDOMException;

import de.dhbw.die5gewinnt.controller.db.DBConnector;
import de.dhbw.die5gewinnt.controller.db.DBSelects;
import de.dhbw.die5gewinnt.controller.xmlaccess.XMLAccess;
import de.dhbw.die5gewinnt.controller.xmlaccess.XMLReader;
import de.dhbw.die5gewinnt.model.ServerFile;

public class Main {

	public static void main(String[] args) throws JDOMException, IOException, InterruptedException {
		// Init the auto increment keys for Game, Set and Move
		DBSelects.initAutoIncrementKeys();
		
		// Test to read a XML File
		readXML();
	
		// Show the current time in milliseconds
		System.out.println("Current time in milliseconds: "+System.currentTimeMillis());
		
		// Close the HSQL Database Connection
	    DBConnector.closeDBConnection();
	}
	
	private static void readXML() throws JDOMException, IOException, InterruptedException{
		XMLAccess.getXMLAccess("", "server2spielero.xml");
		System.out.println(XMLAccess.getXMLAccess().getFilePath());
		ServerFile serverFile = XMLReader.getServerFile();
		
//		System.out.println(serverFile.getRoot());
//		System.out.println(serverFile.getApproval());
//		System.out.println(serverFile.getSetStatus());
//		System.out.println(serverFile.getOpponentMove());
//		System.out.println(serverFile.getWinner());
	}

}