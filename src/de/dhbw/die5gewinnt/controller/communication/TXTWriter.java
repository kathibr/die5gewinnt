package de.dhbw.die5gewinnt.controller.communication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TXTWriter {
	
	public static void setAgentFile(int nextColumn) {
		File file = null;
		FileWriter fileWriter = null;
		
		file = new File(CommunicationCenter.getCommunicationCenter().getAgentFilePath());
		if(file.exists())
			file.delete();
		
		try {
			fileWriter = new FileWriter(CommunicationCenter.getCommunicationCenter().getAgentFilePath());
			fileWriter.write(""+nextColumn);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}