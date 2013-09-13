package de.dhbw.die5gewinnt;

import de.dhbw.die5gewinnt.controller.db.DBSelects;
import javafx.fxml.FXML;

public class OldGamesController {

	private MainApp mainApp;
	private String[] gameNames;

	public OldGamesController() {
		gameNames = DBSelects.selectGameNames();
	    for(int i = 0; i < gameNames.length; i++)
	    	System.out.println(gameNames[i]);
	}

//	@FXML
//	private void initialize() {
//
//	}
	
	public void setMainApp(MainApp mainApp) {
	      this.mainApp = mainApp;
	}
	
}