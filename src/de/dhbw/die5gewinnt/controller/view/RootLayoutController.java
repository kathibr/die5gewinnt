package de.dhbw.die5gewinnt.controller.view;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import de.dhbw.die5gewinnt.MainApp;

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
		if (okClicked) {
			mainApp.showPlayingField();
		}
	}

	@FXML
	private void handleOldGames() {
		mainApp.showOldGames();
	}

	@FXML
	private void returnToStart() {
		mainApp.returnToStart();
	}

}