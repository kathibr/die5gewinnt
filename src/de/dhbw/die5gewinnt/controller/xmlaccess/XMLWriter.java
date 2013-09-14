package de.dhbw.die5gewinnt.controller.xmlaccess;

import java.io.FileWriter;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import de.dhbw.die5gewinnt.model.ServerFile;

public class XMLWriter extends XMLAccess {
	public XMLWriter(ServerFile serverfile) throws JDOMException, IOException {
		super();
		
		Element game = new Element("game");
 
		game.addContent(new Element("freigabe").setText(serverfile.getApproval()));
		game.addContent(new Element("satzstatus").setText(serverfile.getSetStatus()));
		game.addContent(new Element("gegnerzug").setText(serverfile.getOpponentMove()));
		game.addContent(new Element("sieger").setText(serverfile.getWinner()));
		
		Document doc = new Document(game);
 
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		xmlOutput.output(doc, new FileWriter(getFilePath()));		
	}
	
}
