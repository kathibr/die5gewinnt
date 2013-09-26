package de.dhbw.die5gewinnt.controller;

import de.dhbw.die5gewinnt.controller.view.PlayingFieldController;
import de.dhbw.die5gewinnt.controller.communication.CommunicationController;
import de.dhbw.die5gewinnt.controller.db.DBConnector;
import de.dhbw.die5gewinnt.controller.db.DBSelects;
import de.dhbw.die5gewinnt.controller.logic.ModelController;

public class Controller implements Runnable {

	private Controller controller;
	private ModelController modelController;
	private CommunicationController communicationController;
	public static PlayingFieldController playingFieldController;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public Controller() {
		DBConnector.closeDBConnection();
		DBSelects.initAutoIncrementKeys();
		this.modelController = new ModelController(controller);
		this.communicationController = new CommunicationController();		
		this.controller = this;
	}
	
	public Controller getController() {
		return controller;
	}
	
	/* ModelController */
	public ModelController getModelController() {
		//getController();
		return this.modelController;
	}
	
	
	/* CommunicationController */
	public CommunicationController getCommunicationController() {
		//getController();
		return this.communicationController;
	}
	
	public void setCommunicationController(String path, String serverFile, String agentFile) {
		this.communicationController = new CommunicationController(path, serverFile, agentFile);
	}

	public void setPlayingFieldController(PlayingFieldController playingFieldController) {
		this.playingFieldController = playingFieldController;
	}
	
}