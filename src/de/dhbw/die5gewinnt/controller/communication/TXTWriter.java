package de.dhbw.die5gewinnt.controller.communication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import de.dhbw.die5gewinnt.controller.Controller;

public class TXTWriter {
	
	private Controller controller;
	
	public TXTWriter(Controller controller)
	{
		this.controller = controller;
	}
	
	public void setAgentFile(int nextColumn) {
		File file = null;
		FileWriter fileWriter = null;
		
		file = new File(controller.getCommunicationController().getAgentFilePath());
		if(file.exists())
			file.delete();
		
		try {
			fileWriter = new FileWriter(controller.getCommunicationController().getAgentFilePath());
			fileWriter.write(""+nextColumn);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAgentFile() {
		File file = new File(controller.getCommunicationController().getAgentFilePath());
		
		if(file.exists())
			file.delete();
	}
	
}