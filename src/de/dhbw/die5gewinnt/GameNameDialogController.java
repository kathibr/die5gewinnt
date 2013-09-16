package de.dhbw.die5gewinnt;

import de.dhbw.die5gewinnt.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GameNameDialogController {

	@FXML
	private TextField gameName;

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
			String player = "X";
			if(playerO.isSelected())
				player = "O";
			Controller.getController().getModelController().newGame(gameName.getText(), "X");
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