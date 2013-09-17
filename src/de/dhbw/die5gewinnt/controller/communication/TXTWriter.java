package de.dhbw.die5gewinnt.controller.communication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import de.dhbw.die5gewinnt.controller.Controller;

public class TXTWriter {
	
	public static void setAgentFile(int nextColumn) {
		File file = null;
		FileWriter fileWriter = null;
		
		file = new File(Controller.getCommunicationController().getAgentFilePath());
		if(file.exists())
			file.delete();
		
		try {
			fileWriter = new FileWriter(Controller.getCommunicationController().getAgentFilePath());
			fileWriter.write(""+nextColumn);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteAgentFile() {
		File file = new File(Controller.getCommunicationController().getAgentFilePath());
		
		if(file.exists())
			file.delete();
	}
	
}