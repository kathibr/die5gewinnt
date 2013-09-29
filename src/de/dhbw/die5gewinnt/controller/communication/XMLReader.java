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


public class XMLReader implements Runnable {
	private ServerFile serverFile = null;
	private  Lock lock;
	private  Condition conditionServerFile;

	
	public XMLReader (ServerFile sf1)
	{
		this.serverFile = sf1;
	}

	public XMLReader(ServerFile sf1, Lock lock,
			Condition conditionServerFile) {
		this.serverFile = sf1;
		this.lock = lock;
		this.conditionServerFile = conditionServerFile;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		do{
		lock.lock();
		System.out.println("Reader lock");
		File file = null;
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = null;
		Element root = null;
		
		file = new File(Controller.getController().getCommunicationController().getServerFilePath());
//		System.out.println(Controller.getController().getCommunicationController().getServerFilePath());
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
//		System.out.println("Freigabe" +root.getChild("freigabe").getText());
		serverFile.setOpponentMove(Integer.parseInt(root.getChild("gegnerzug").getText()));
		System.out.println("Gegnerzug" +root.getChild("gegnerzug").getText());
		serverFile.setSetStatus(root.getChild("satzstatus").getText());
		serverFile.setWinner(root.getChild("sieger").getText());
		
		file.delete();

//		System.out.println("test");
		CommunicationController.setServerFile(serverFile);	
		// TODO Auto-generated method stub	
		try {
			conditionServerFile.signal();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception wait Reader"+e.getMessage());
		}
		finally{
			lock.unlock();
			System.out.println("Reader unlock");
		}
		

		}while(true);
	}

	
}