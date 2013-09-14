package de.dhbw.die5gewinnt.controller;

import java.io.IOException;

import org.jdom2.JDOMException;

import de.dhbw.die5gewinnt.controller.xmlaccess.XMLReader;
import de.dhbw.die5gewinnt.model.ServerFile;

public class Controller {

	public Controller() {}


	public void xmlRead() throws JDOMException, IOException, InterruptedException{
		XMLReader reader = new XMLReader();

		ServerFile serverFile = reader.getObject();
		System.out.println(serverFile.getApproval());
		System.out.println(serverFile.getSetStatus());
		System.out.println(serverFile.getOpponentMove());
		System.out.println(serverFile.getWinner());
	}
}