package de.dhbw.die5gewinnt.controller;

import java.io.IOException;

import org.jdom2.JDOMException;

import de.dhbw.die5gewinnt.controller.xmlaccess.XMLAccess;
import de.dhbw.die5gewinnt.controller.xmlaccess.XMLReader;
import de.dhbw.die5gewinnt.controller.xmlaccess.XMLWriter;
import de.dhbw.die5gewinnt.model.ServerFile;

public class Controller {
	
	private int GameID;
	private int SetID;
	private int moveID;
	

	public Controller() {}
	
	public void startGame() {
		GameID++;
		SetID = 0;
		startSet();	
	}
	
	public void startSet(){
		SetID++;
		moveID = 0;			
	}
	
}