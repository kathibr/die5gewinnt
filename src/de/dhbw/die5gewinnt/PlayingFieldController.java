package de.dhbw.die5gewinnt;

import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;

public class PlayingFieldController {

	private MainApp mainApp;

	public PlayingFieldController() {

	}

	@FXML
	private void initialize() {

	}
	
	public void setMainApp(MainApp mainApp) {
	      this.mainApp = mainApp;
	}
	
	@FXML
	public void handleClickMe(){
		 Dialogs.showWarningDialog(mainApp.getPrimaryStage(),
		          "Hallo Hallo",
		          "Irgendein Schund", "Neuer Dialog");
	}

}
