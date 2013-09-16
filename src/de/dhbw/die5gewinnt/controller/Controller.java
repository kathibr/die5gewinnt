package de.dhbw.die5gewinnt.controller;

import java.io.IOException;

import org.jdom2.JDOMException;

import de.dhbw.die5gewinnt.controller.xmlaccess.XMLReader;
import de.dhbw.die5gewinnt.controller.xmlaccess.XMLWriter;
import de.dhbw.die5gewinnt.model.ServerFile;

public class Controller {
	
	private int GameID;
	private int SetID;
	private int moveID;
	

	public Controller() throws JDOMException, IOException, InterruptedException 
	{
		//readXML();
		//XMLReader reader = new XMLReader();
		//new XMLWriter(reader.getServerFile());
		
	}

	public void readXML() throws JDOMException, IOException, InterruptedException{
		ServerFile serverFile = XMLReader.getServerFile();
		System.out.println(serverFile.getRoot());
		System.out.println(serverFile.getApproval());
		System.out.println(serverFile.getSetStatus());
		System.out.println(serverFile.getOpponentMove());
		System.out.println(serverFile.getWinner());
	}
	
	public void startGame(){
		GameID++;
		SetID = 0;
		startSet();
		
	}
	
	public void startSet(){
		SetID++;
		moveID = 0;
				
	}
	
}
