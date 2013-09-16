package de.dhbw.die5gewinnt.controller;

import de.dhbw.die5gewinnt.controller.db.DBConnector;
import de.dhbw.die5gewinnt.controller.db.DBSelects;
import de.dhbw.die5gewinnt.controller.logic.ModelController;

public class Controller {

	private static Controller controller;
	private ModelController modelController;
	
	private Controller() {
		DBConnector.closeDBConnection();
		DBSelects.initAutoIncrementKeys();
	}
	
	public static Controller getController() {
		if(controller == null)
			controller = new Controller();
		return controller;
	}
	
	public ModelController getModelController() {
		if(this.modelController == null)
			this.modelController = new ModelController();
		return this.modelController;
	}
	
}