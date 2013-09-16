package de.dhbw.die5gewinnt.controller;

import java.io.IOException;

import org.jdom2.JDOMException;

import de.dhbw.die5gewinnt.controller.communication.CommunicationCenter;
import de.dhbw.die5gewinnt.controller.communication.XMLReader;
import de.dhbw.die5gewinnt.controller.communication.TXTWriter;
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