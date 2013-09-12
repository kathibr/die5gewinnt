package de.dhbw.die5gewinnt;

import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;

public class RootLayoutController {
	
	private MainApp mainApp;
	
	  public void setMainApp(MainApp mainApp) {
	      this.mainApp = mainApp;
	  }
	  
	  @FXML
      private void handleNewGame() {
        mainApp.showPlayingField();
      }
	  @FXML
      private void handleOldGames() {
		 mainApp.showOldGames();
      }
	  
      @FXML
      private void handleExit() {
          System.exit(0);
      }
	  
}
