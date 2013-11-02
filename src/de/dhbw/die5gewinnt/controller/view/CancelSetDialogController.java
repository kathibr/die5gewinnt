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
		cbWinner.getItems().addAll(Controller.getController().getModelController().getGame().getOpponentName());
		cbWinner.getItems().addAll("die5gewinnt");
		cbWinner.setValue(Controller.getController().getModelController().getGame().getOpponentName());
	
		try {
			if (Controller.getController().getModelController().getWinner().equals("Spieler "+Controller.getController().getModelController().getGame().getPlayer())) {
				
				cbWinner.setValue("die5gewinnt");
			}
		} catch (Exception e) {
			System.out.println(e);
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
		//TODO:satz wird verworfen > anzeige des aktuellen satzes zurücksetzen
		dialogStage.close();
	}

	
	@FXML
	private void saveSet(){
		Controller.getController().getPlayingFieldController().setWinner(cbWinner.getValue());
		okClicked = true;
		dialogStage.close();
	}
		
}
