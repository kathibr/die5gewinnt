package de.dhbw.die5gewinnt.controller.xmlaccess;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.dhbw.die5gewinnt.model.ServerFile;

public class XMLReader {
	
	private ServerFile serverFile;
	private File f;
	
	private XMLReader() throws JDOMException, IOException, InterruptedException {
		f = new File(XMLAccess.getXMLAccess().getFilePath());
		while(true) {
			if(f.exists()) 
				break;
			Thread.sleep(300);	
		}		
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(XMLAccess.getXMLAccess().getFilePath());
		Element root = doc.getRootElement();
		this.setServerFile(new ServerFile(root.getChild("freigabe").getText(), root.getChild("satzstatus").getText(), root.getChild("gegnerzug").getText(), root.getChild("sieger").getText(), root));
		f.delete();
	}
	
	/* GETTER-Methods */
	public static ServerFile getServerFile() {
		XMLReader xmlReader = null;
		try {
			xmlReader = new XMLReader();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return xmlReader.serverFile;
	}

	/* SETTER-Methods */
	private void setServerFile(ServerFile serverFile) {
		this.serverFile = serverFile;
	}
	
}