package de.dhbw.die5gewinnt;

import de.dhbw.die5gewinnt.controller.xmlaccess.XMLAccess;

public class TestMain {

	public static void main(String[] args) {
		XMLAccess.getXMLAccess("", "server2spielero.xml");
		System.out.println(XMLAccess.getXMLAccess().getFilePath());
	}

}