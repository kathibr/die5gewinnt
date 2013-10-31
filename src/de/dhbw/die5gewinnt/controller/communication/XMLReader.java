package de.dhbw.die5gewinnt.controller.communication;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.model.ServerFile;

public class XMLReader  {
	private ServerFile serverFile;
	private SAXBuilder saxBuilder;
	private File file = null;
	private Document document;
	private Element root;
	
	public ServerFile getServerFile()
	{
		serverFile = new ServerFile();
		saxBuilder = new SAXBuilder();
		document = null;
		root = null;
		file = new File(Controller.getController().getCommunicationController().getServerFilePath());
		System.out.println(Controller.getController().getCommunicationController().getServerFilePath());
		while(true) 
		{
			
			if(file.exists()) {
				fillFile();
				break;
			}
			else if(Controller.getController().getCommunicationController().getGameOver()==true){
				System.out.println("Spielende in XML");
				break;
			}
		}
						
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return serverFile;	
	}
		
	private void fillFile(){

		try {		
			document = saxBuilder.build(Controller.getController().getCommunicationController().getServerFilePath());
			root = document.getRootElement();
			
			serverFile.setApproval(root.getChild("freigabe").getText());
			serverFile.setOpponentMove(Integer.parseInt(root.getChild("gegnerzug").getText()));
			serverFile.setSetStatus(root.getChild("satzstatus").getText());
			serverFile.setWinner(root.getChild("sieger").getText());
			
			file.delete();
			Controller.getController().getCommunicationController().setServerFile(serverFile);	
			Controller.getController().getPlayingFieldController().disappearLbStatus();
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

		
}