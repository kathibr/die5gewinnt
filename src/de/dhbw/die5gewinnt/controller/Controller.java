package de.dhbw.die5gewinnt.controller;

import java.io.IOException;
import org.jdom2.JDOMException;
import de.dhbw.die5gewinnt.controller.xmlaccess.XMLReader;

public class Controller {

	public Controller() 
	{

	}


	public void xmlRead() throws JDOMException, IOException, InterruptedException
	{
		XMLReader reader = new XMLReader();

		String freigabe = reader.getFreigabe();
		System.out.println(freigabe);
		
		String satzstatus = reader.getSatzstatus();
		System.out.println(satzstatus);
		
		String gegnerzug = reader.getGegnerzug();
		System.out.println(gegnerzug);
		
		String sieger = reader.getSieger();
		System.out.println(sieger);
	}
}