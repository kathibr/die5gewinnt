package de.dhbw.die5gewinnt.controller.communication;

import java.io.FileWriter;
import java.io.IOException;

public class TXTWriter {
	
	public static void setAgentFile(int nextColumn) {
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(CommunicationCenter.getCommunicationCenter().getAgentFilePath());
			fileWriter.write(nextColumn);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}