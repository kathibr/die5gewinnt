package de.dhbw.die5gewinnt.controller;

import java.io.IOException;

import org.jdom2.JDOMException;

import de.dhbw.die5gewinnt.controller.xmlaccess.XMLReader;
import de.dhbw.die5gewinnt.controller.xmlaccess.XMLWriter;

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
		XMLReader reader = new XMLReader();
		System.out.println(reader.getServerFile().getRoot());
		System.out.println(reader.getServerFile().getApproval());
		System.out.println(reader.getServerFile().getSetStatus());
		System.out.println(reader.getServerFile().getOpponentMove());
		System.out.println(reader.getServerFile().getWinner());
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
