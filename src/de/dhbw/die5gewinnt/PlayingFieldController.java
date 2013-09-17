package de.dhbw.die5gewinnt;

import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.controller.communication.CommunicationCenter;
import de.dhbw.die5gewinnt.controller.logic.ModelController;
import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;

public class PlayingFieldController {

	private MainApp mainApp;
	
	
	@FXML 
	private Label gameNameLabel;
	
	@FXML
	private Circle circle05;
	@FXML
	private Circle circle04;
	@FXML
	private Circle circle03;
	@FXML
	private Circle circle02;
	@FXML
	private Circle circle01;
	@FXML
	private Circle circle00;
	
	@FXML
	private Circle circle15;
	@FXML
	private Circle circle14;	
	@FXML
	private Circle circle13;
	@FXML
	private Circle circle12;	
	@FXML
	private Circle circle11;	
	@FXML
	private Circle circle10;
	
	@FXML
	private Circle circle25;
	@FXML
	private Circle circle24;
	@FXML
	private Circle circle23;
	@FXML
	private Circle circle22;
	@FXML
	private Circle circle21;
	@FXML
	private Circle circle20;
	
	@FXML
	private Circle circle35;
	@FXML
	private Circle circle34;
	@FXML
	private Circle circle33;
	@FXML
	private Circle circle32;
	@FXML
	private Circle circle31;
	@FXML
	private Circle circle30;
	
	@FXML
	private Circle circle45;
	@FXML
	private Circle circle44;
	@FXML
	private Circle circle43;
	@FXML
	private Circle circle42;
	@FXML
	private Circle circle41;
	@FXML
	private Circle circle40;
	
	@FXML
	private Circle circle55;
	@FXML
	private Circle circle54;
	@FXML
	private Circle circle53;
	@FXML
	private Circle circle52;
	@FXML
	private Circle circle51;
	@FXML
	private Circle circle50;
	
	@FXML
	private Circle circle65;
	@FXML
	private Circle circle64;
	@FXML
	private Circle circle63;
	@FXML
	private Circle circle62;
	@FXML
	private Circle circle61;
	@FXML
	private Circle circle60;
	
	
	public PlayingFieldController() {

	}

	@FXML
	private void initialize() {
		gameNameLabel.setText(ModelController.getModelController().getGame().getName());
		System.out.println("Spielname: "+ModelController.getModelController().getGame().getName());
		System.out.println("Spieler: "+ModelController.getModelController().getGame().getPlayer());
		System.out.println("Serverfile: "+CommunicationCenter.getCommunicationCenter().getServerFilePath());
		System.out.println("Agentfile: "+CommunicationCenter.getCommunicationCenter().getAgentFilePath());
		
	}
	
	public void setMainApp(MainApp mainApp) {
	      this.mainApp = mainApp;
	}
	
	@FXML
	public void handleClickMe(){

		circle00.getStyleClass().remove("emptyCircle");
		circle00.getStyleClass().add("yellowCircle");
		circle11.getStyleClass().remove("emptyCircle");
		circle11.getStyleClass().add("yellowCircle");
		circle22.getStyleClass().remove("emptyCircle");
		circle22.getStyleClass().add("yellowCircle");
	
		
		circle10.getStyleClass().remove("emptyCircle");
		circle10.getStyleClass().add("redCircle");
		circle20.getStyleClass().remove("emptyCircle");
		circle20.getStyleClass().add("redCircle");
		circle21.getStyleClass().remove("emptyCircle");
		circle21.getStyleClass().add("redCircle");
	
		
	
	}
	
	@FXML
	public void circleAction(){
		
	}

}
