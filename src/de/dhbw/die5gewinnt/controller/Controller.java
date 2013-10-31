package de.dhbw.die5gewinnt.controller;

import de.dhbw.die5gewinnt.controller.view.PlayingFieldController;
import de.dhbw.die5gewinnt.controller.communication.CommunicationController;
import de.dhbw.die5gewinnt.controller.db.DBConnector;
import de.dhbw.die5gewinnt.controller.db.DBSelects;

public class Controller extends Thread {

	private static Controller controller;
	private ModelController modelController;
	private CommunicationController communicationController;
	public PlayingFieldController playingFieldController;
	
	@Override
	public void run() {
		this.modelController.startGame();
	}
	
	public Controller() {
		controller = this;
		this.modelController = new ModelController();
		DBConnector.closeDBConnection();
		DBSelects.initAutoIncrementKeys();
	}
	
	public static Controller getController() {
		return controller;
	}
	
	/* ModelController */
	public ModelController getModelController() {
		return this.modelController;
	}
	
	
	/* CommunicationController */
	public CommunicationController getCommunicationController() {
		if(this.communicationController == null)
			this.communicationController = new CommunicationController();
		return this.communicationController;
	}
	
	public CommunicationController setCommunicationController(String path, String serverFile, String agentFile) {
		if(this.communicationController == null)
			this.communicationController = new CommunicationController(path, serverFile, agentFile);
		else {
			communicationController.setPath(path);
			communicationController.setServerFileName(serverFile);
			communicationController.setAgentFileName(agentFile);
		}
		return this.communicationController;
	}

	public void setPlayingFieldController(PlayingFieldController playingFieldController) {
		this.playingFieldController = playingFieldController;
	}
	
	public PlayingFieldController getPlayingFieldController() {
		return this.playingFieldController;
	}

	
}