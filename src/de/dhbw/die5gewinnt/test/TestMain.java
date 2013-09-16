package de.dhbw.die5gewinnt.test;

import de.dhbw.die5gewinnt.controller.xmlaccess.XMLAccess;

public class TestMain {

	public static void main(String[] args) {
		// Test the XMLAccess class
		XMLAccess.getXMLAccess("", "server2spielero.xml");
		System.out.println(XMLAccess.getXMLAccess().getFilePath());
		
		// Show the milli seconds
		System.out.println("Timestamp: "+System.currentTimeMillis());
	}

}