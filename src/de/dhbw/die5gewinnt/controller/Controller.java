package de.dhbw.die5gewinnt.controller;

import de.dhbw.die5gewinnt.controller.view.PlayingFieldController;
import de.dhbw.die5gewinnt.controller.communication.CommunicationController;
import de.dhbw.die5gewinnt.controller.db.DBConnector;
import de.dhbw.die5gewinnt.controller.db.DBSelects;
import de.dhbw.die5gewinnt.controller.logic.ModelController;

public class Controller implements Runnable {

	private Controller controller;
	private static ModelController modelController;
	private static CommunicationController communicationController;
	public static PlayingFieldController playingFieldController;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public Controller() {
		DBConnector.closeDBConnection();
		DBSelects.initAutoIncrementKeys();
	}
	
	public Controller getController() {
		this.controller = this;
		return controller;
	}
	
	/* ModelController */
	public static ModelController getModelController() {
		//getController();
		if(modelController == null)
			modelController = new ModelController();
		return modelController;
	}
	
	
	/* CommunicationController */
	public static CommunicationController getCommunicationController() {
		//getController();
		if(communicationController == null)
			communicationController = new CommunicationController();
		return communicationController;
	}
	
	public static void setCommunicationController(String path, String serverFile, String agentFile) {
		//getController();
		if(communicationController == null)
			communicationController = new CommunicationController(path, serverFile, agentFile);
	}

	public void setPlayingFieldController(PlayingFieldController playingFieldController) {
		this.playingFieldController = playingFieldController;
	}
	
}