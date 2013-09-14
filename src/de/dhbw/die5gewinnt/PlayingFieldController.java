package de.dhbw.die5gewinnt;

import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.shape.Circle;

public class PlayingFieldController {

	private MainApp mainApp;
	
	@FXML
	private Circle circle1;

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
		// Dialogs.showWarningDialog(mainApp.getPrimaryStage(),
		  //        "Hallo Hallo",
		    //      "Irgendein Schund", "Neuer Dialog");
		
		circle1.getStyleClass().remove("emptyCircle");
		circle1.getStyleClass().add("yellowCircle");
		
	
	}
	
	@FXML
	public void circleAction(){
		
	}

}
