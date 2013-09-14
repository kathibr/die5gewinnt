package de.dhbw.die5gewinnt.controller;

import java.io.IOException;

import org.jdom2.JDOMException;

import de.dhbw.die5gewinnt.controller.xmlaccess.XMLReader;

public class Controller {

	public Controller() throws JDOMException, IOException, InterruptedException 
	{
		//readXML();
	}

	public void readXML() throws JDOMException, IOException, InterruptedException{
		XMLReader reader = new XMLReader();
		System.out.println(reader.getServerFile().getRoot());
		System.out.println(reader.getServerFile().getApproval());
		System.out.println(reader.getServerFile().getSetStatus());
		System.out.println(reader.getServerFile().getOpponentMove());
		System.out.println(reader.getServerFile().getWinner());
	}
	
	
	
}