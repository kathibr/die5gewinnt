package de.dhbw.die5gewinnt.controller.xmlaccess;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.dhbw.die5gewinnt.model.ServerFile;

public class XMLReader {
	private ServerFile serverfile;
	private File f;

	public XMLReader() throws JDOMException, IOException, InterruptedException {
		while (true)
		{
			f = new File(XMLAccess.getXMLAccess().getFilePath());
			if(f.exists()) 
			{ 
				break;
			}
			Thread.sleep(300);	
		}
		
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(XMLAccess.getXMLAccess().getFilePath());
		Element root = doc.getRootElement();
		serverfile = new ServerFile(root.getChild("freigabe").getText(), root.getChild("satzstatus").getText(), root.getChild("gegnerzug").getText(), root.getChild("sieger").getText(), root);
		f.delete();
	}
	
	public ServerFile getServerFile() {
		return serverfile;
	}
}