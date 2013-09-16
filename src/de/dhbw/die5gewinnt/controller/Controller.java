package de.dhbw.die5gewinnt.controller;

import de.dhbw.die5gewinnt.controller.logic.ModelController;

public class Controller {

	private static Controller controller;
	private ModelController modelController;
	
	private Controller() {}
	
	public static Controller getController() {
		if(controller == null)
			controller = new Controller();
		return controller;
	}
	
	public ModelController getModelController() {
		if(modelController == null)
			modelController = new ModelController();
		return modelController;
	}
	
}