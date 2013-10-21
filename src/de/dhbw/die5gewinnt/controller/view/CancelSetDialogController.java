package de.dhbw.die5gewinnt.controller.view;

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
	private Button btEditSet;
	@FXML
	private Button btSave;
	
	@FXML
	private ChoiceBox<String> cbWinner;
	
	@FXML
	private Label lbWinner;
	@FXML
	private Label lbResult;
	@FXML
	private Label lbColon;
	@FXML
	private TextField tfResultX;
	@FXML
	private TextField tfResultO;
	

	@FXML
	private void initialize() {
		//TODO: muss dynamisch zugewiesen werden
		cbWinner.getItems().addAll("playerX");
		cbWinner.getItems().addAll("playerO");
		cbWinner.setValue("playerX");
		
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
		
		btEditSet.getStyleClass().remove("button-selected");		
		okClicked = true;
		dialogStage.close();
	}
	
	@FXML
	private void editSet() {
		//TODO: satz ist gültig und muss editiert werden
		btEditSet.getStyleClass().add("button-selected");
		lbWinner.setVisible(true);
		lbResult.setVisible(true);
		lbColon.setVisible(true);
		cbWinner.setVisible(true);
		tfResultX.setVisible(true);		
		tfResultO.setVisible(true);
		btSave.setVisible(true);		
		
	}
	
	@FXML
	private void saveSet(){
		//TODO: alle eigenen und gelesenen züge bis zum abbruch werden automatisch gespeichert
		//TODO: sieger und punktezahl werden überschrieben
		//TODO: alle einträge werden überprüft
		okClicked = true;
		dialogStage.close();
	}
}
