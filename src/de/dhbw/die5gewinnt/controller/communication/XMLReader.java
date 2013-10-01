package de.dhbw.die5gewinnt.controller.communication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.model.ServerFile;


public class XMLReader extends Thread {
	private ServerFile serverFile = null;

	
	public XMLReader ()
	{
	}
	
	public void run()
	{
		File file = null;
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = null;
		Element root = null;
		file = new File(Controller.getController().getCommunicationController().getServerFilePath());
		System.out.println(Controller.getController().getCommunicationController().getServerFilePath());
		while(true) {
			if(file.exists()) {
				break;
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}	
		}	
		try {		
			document = saxBuilder.build(Controller.getController().getCommunicationController().getServerFilePath());
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		root = document.getRootElement();
		
		serverFile.setApproval(root.getChild("freigabe").getText());
		System.out.println("Freigabe" +root.getChild("freigabe").getText());
		serverFile.setOpponentMove(Integer.parseInt(root.getChild("gegnerzug").getText()));
		System.out.println("Gegnerzug" +root.getChild("gegnerzug").getText());
		serverFile.setSetStatus(root.getChild("satzstatus").getText());
		serverFile.setWinner(root.getChild("sieger").getText());
		
		file.delete();
		Controller.getController().getCommunicationController().setServerFile(serverFile);	
		Controller.getController().getModelController().continueSet(serverFile);
	}

	
}