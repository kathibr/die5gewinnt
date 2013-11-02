package de.dhbw.die5gewinnt.controller.view;

import de.dhbw.die5gewinnt.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CancelSetDialogController {
	
	private Stage dialogStage;
	private boolean okClicked = false;
	
	@FXML
	private Button btRejectSet;	
	@FXML
	private Button btSave;
	
	@FXML
	private ChoiceBox<String> cbWinner;
	
	@FXML
	private Label lbWinner;
	

	@FXML
	private void initialize() {
		//TODO: muss dynamisch zugewiesen werden
		cbWinner.getItems().addAll("playerX");
		cbWinner.getItems().addAll("playerO");
		if (Controller.getController().getModelController().getWinner().equals("Spieler X")) {
			cbWinner.setValue("playerX");
		}
		else{
			cbWinner.setValue("playerO");
		}
				
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void rejectSet() {
		//TODO:satz wird verworfen > anzeige des aktuellen satzes zur�cksetzen
		dialogStage.close();
	}

	
	@FXML
	private void saveSet(){
		//TODO: alle eigenen und gelesenen z�ge bis zum abbruch werden automatisch gespeichert
		//TODO: sieger und punktezahl werden �berschrieben
		//TODO: alle eintr�ge werden �berpr�ft
		Controller.getController().getPlayingFieldController().setWinner(cbWinner.getValue());
//		Controller.getController().getPlayingFieldController().updateDisplay();
//		Controller.getController().getPlayingFieldController().updateScoreFromDisplay();
//		Controller.getController().getModelController().updateSet();
		okClicked = true;
		dialogStage.close();
	}
		
}
