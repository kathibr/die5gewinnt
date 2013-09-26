package de.dhbw.die5gewinnt.controller.communication;

import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import de.dhbw.die5gewinnt.controller.Controller;

public class XMLWriter {

	public static void setServerFile() {	
		Element serverFileForServer = new Element("content");
 
		serverFileForServer.addContent(new Element("freigabe").setText("true"));
		serverFileForServer.addContent(new Element("satzstatus").setText("Satz spielen"));
		serverFileForServer.addContent(new Element("gegnerzug").setText("-1"));
		serverFileForServer.addContent(new Element("sieger").setText("offen"));
		
		Document document = new Document(serverFileForServer);
 
		XMLOutputter xmlOutputter = new XMLOutputter();
		xmlOutputter.setFormat(Format.getPrettyFormat());
		try {
			xmlOutputter.output(document, new FileWriter(Controller.getCommunicationController().getServerFilePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}