package de.dhbw.die5gewinnt.controller.view;

import de.dhbw.die5gewinnt.MainApp;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class RootLayoutController {

	private MainApp mainApp;
	@FXML
	private ImageView appLogo;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleNewGame() {
		boolean okClicked = mainApp.showGameNameDialog();
		if(okClicked) {
			mainApp.showPlayingField();
		}		  
	}
	@FXML
	private void handleOldGames() {
		mainApp.showOldGames();
	}
	@FXML
	private void returnToStart(){
		// System.out.println("Zum Start");
	//	mainApp.getPrimaryStage().getScene();mainapp set root empty
	}

}