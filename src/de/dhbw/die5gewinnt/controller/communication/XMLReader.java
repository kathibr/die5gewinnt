package de.dhbw.die5gewinnt.controller.communication;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.model.ServerFile;

public class XMLReader {

	public static ServerFile getServerFile() {
		File file = null;
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = null;
		Element root = null;
		ServerFile serverFile = null;
		
		file = new File(Controller.getCommunicationController().getServerFilePath());
		while(true) {
			if(file.exists()) 
				break;
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}	
		try {		
			document = saxBuilder.build(Controller.getCommunicationController().getServerFilePath());
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		root = document.getRootElement();
		serverFile = new ServerFile(root.getChild("freigabe").getText(), root.getChild("satzstatus").getText(), Integer.parseInt(root.getChild("gegnerzug").getText()), root.getChild("sieger").getText());
		file.delete();
		return serverFile;
	}
	
}