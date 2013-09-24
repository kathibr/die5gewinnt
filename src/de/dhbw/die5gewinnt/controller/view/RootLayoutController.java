package de.dhbw.die5gewinnt.controller.view;

import de.dhbw.die5gewinnt.MainApp;
import de.dhbw.die5gewinnt.controller.db.DBConnector;
import javafx.fxml.FXML;

public class RootLayoutController {
	
	private MainApp mainApp;
	
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
      private void handleExit() {
    	  // Close the DBConnection
    	  DBConnector.closeDBConnection();
    	  // Close the primary stage
    	  mainApp.getPrimaryStage().close();
      }
	  
}