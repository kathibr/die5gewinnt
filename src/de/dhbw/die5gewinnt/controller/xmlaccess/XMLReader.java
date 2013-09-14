package de.dhbw.die5gewinnt.controller.xmlaccess;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.dhbw.die5gewinnt.model.ServerFile;

public class XMLReader extends XMLAccess {
	
	private Element root;

	public XMLReader() {
		super();
	}
	
	public void readXML() throws InterruptedException, JDOMException, IOException 
	{
		while (true)
		{
			File f = new File(getFilePath());
			if(f.exists()) 
			{ 
				break;
			}
			Thread.sleep(300);	
		}
		
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(getFilePath());
		this.root = doc.getRootElement(); 
//		delete File
	}

	
	private String getApproval() {
	    return this.root.getChild("freigabe").getText();
	}

	private String getSetStatus() {
	    return this.root.getChild("satzstatus").getText();
	}

	private String getOpponentMove() {
		return this.root.getChild("gegnerzug").getText();
	}

	private String getWinner() {
	    return this.root.getChild("sieger").getText();
	}
	
	public ServerFile getObject() {
		return new ServerFile(getApproval(), getSetStatus(), getOpponentMove(), getWinner());
	}
}