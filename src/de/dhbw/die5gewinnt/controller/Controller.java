package de.dhbw.die5gewinnt.controller;

import de.dhbw.die5gewinnt.controller.view.PlayingFieldController;
import de.dhbw.die5gewinnt.controller.communication.CommunicationController;
import de.dhbw.die5gewinnt.controller.db.DBConnector;
import de.dhbw.die5gewinnt.controller.db.DBSelects;
import de.dhbw.die5gewinnt.controller.logic.ModelController;

public class Controller implements Runnable {

	private static Controller controller;
	private ModelController modelController;
	private CommunicationController communicationController;
	public PlayingFieldController playingFieldController;
	
	@Override
	public void run() {}
	
	private Controller() {
		DBConnector.closeDBConnection();
		DBSelects.initAutoIncrementKeys();
	}
	
	public static Controller getController() {
		if(controller == null)
			controller = new Controller();
		return controller;
	}
	
	/* ModelController */
	public ModelController getModelController() {
		if(this.modelController == null)
			this.modelController = new ModelController();
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
		return this.communicationController;
	}

	public void setPlayingFieldController(PlayingFieldController playingFieldController) {
		this.playingFieldController = playingFieldController;
	}
	
	public PlayingFieldController getPlayingFieldController() {
		return this.playingFieldController;
	}
	
}