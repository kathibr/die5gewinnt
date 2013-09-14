package de.dhbw.die5gewinnt.controller.xmlaccess;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLReader {
	
	private static String path = "C:/Users/D056939/Desktop/die5gewinnt/server2spielero.xml";
	private Element root;


	public XMLReader() {
	
	}
	
	public void readXML() throws InterruptedException, JDOMException, IOException 
	{
		while (true)
		{
			File f = new File(path);
			if(f.exists()) 
			{ 
				break;
			}
			Thread.sleep(300);	
		}
		
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(path);
		this.root = doc.getRootElement(); 
	}

	
	public String getFreigabe() 
	{
	    String freigabe = this.root.getChild("freigabe").getText();
	    return freigabe;
	}

	public String getSatzstatus() 
	{
	    String satzstatus = this.root.getChild("satzstatus").getText();
	    return satzstatus;
	}

	public String getGegnerzug() 
	{
	    String gegnerzug = this.root.getChild("gegnerzug").getText();
	    return gegnerzug;
	}

	public String getSieger() 
	{
	    String sieger = this.root.getChild("sieger").getText();
	    return sieger;
	}	
}