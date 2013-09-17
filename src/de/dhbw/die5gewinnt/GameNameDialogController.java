package de.dhbw.die5gewinnt;

import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.controller.logic.ModelController;
import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GameNameDialogController {

	@FXML
	private TextField gameName, filePath;
	@FXML
	private RadioButton playerX, playerO;
	private Stage dialogStage;
	
	private boolean okClicked = false;
	
	@FXML
	private void initialize() {}
	
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if(isInputValid()) {
			// Create Game
			String player = "X";
			if(playerO.isSelected())
				player = "O";
			ModelController.getModelController().newGame(gameName.getText(), "X");
			// Create CommunicationCenter
			String serverFile = "server2spielerx.xml";
			String agentFile = "agentx2server.txt";
			if(playerO.isSelected()) {
				serverFile = "server2spielero.xml";
				agentFile = "agento2server.txt";
			}
			Controller.getController().setCommunicationCenter(filePath.getText(), serverFile, agentFile);
			okClicked = true;
		    dialogStage.close();
		}	
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	  
	private boolean isInputValid(){
		String errorMessage = "";		  
	  
		if(gameName.getText() == null || gameName.getText().length() == 0)
			errorMessage += "Kein zugelassener Spielname!\n";
		
		if(errorMessage.length() == 0)
			return true;
	    else {
	    	Dialogs.showErrorDialog(dialogStage, errorMessage, "Bitte unzulässige Felder korrigieren!", "Unzulässiges Feld");
	        return false;
	    }      
	}
 
}