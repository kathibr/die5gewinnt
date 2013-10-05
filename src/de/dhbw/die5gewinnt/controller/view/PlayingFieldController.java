package de.dhbw.die5gewinnt.controller.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import de.dhbw.die5gewinnt.MainApp;
import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.controller.algorithm.MastermindAlgorithm;
import de.dhbw.die5gewinnt.controller.algorithm.RandomAlgorithm;
import de.dhbw.die5gewinnt.controller.communication.XMLReader;
import de.dhbw.die5gewinnt.controller.logic.ModelController;
import de.dhbw.die5gewinnt.model.ServerFile;
import de.dhbw.die5gewinnt.model.Set;

public class PlayingFieldController {
	
	private ModelController modelController;

	@SuppressWarnings("unused")
	private MainApp mainApp;

	private Circle circleArray[][];

	@FXML 
	private Label gameNameLabel;

	@FXML
	private Button btStartSet;
	@FXML
	private Button btNextSet;
	@FXML
	private Button btEndSet;
	@FXML
	private Button btEndGame;
	
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
		btNextSet.setDisable(true);
		btEndSet.setDisable(true);
		
		modelController = Controller.getController().getModelController();
		gameNameLabel.setText(modelController.getGame().getName());
//		System.out.println("Spielname: "+Controller.getModelController().getGame().getName());
//		System.out.println("Spieler: "+Controller.getModelController().getGame().getPlayer());
//		System.out.println("Serverfile: "+Controller.getCommunicationController().getServerFilePath());
//		System.out.println("Agentfile: "+Controller.getCommunicationController().getAgentFilePath());

		circleArray = new Circle[20][20];
		circleArray[0][0]= circle00;
		circleArray[0][1]= circle01;
		circleArray[0][2]= circle02;
		circleArray[0][3]= circle03;
		circleArray[0][4]= circle04;
		circleArray[0][5]= circle05;

		circleArray[1][0]= circle10;
		circleArray[1][1]= circle11;
		circleArray[1][2]= circle12;
		circleArray[1][3]= circle13;
		circleArray[1][4]= circle14;
		circleArray[1][5]= circle15;

		circleArray[2][0]= circle20;
		circleArray[2][1]= circle21;
		circleArray[2][2]= circle22;
		circleArray[2][3]= circle23;
		circleArray[2][4]= circle24;
		circleArray[2][5]= circle25;

		circleArray[3][0]= circle30;
		circleArray[3][1]= circle31;
		circleArray[3][2]= circle32;
		circleArray[3][3]= circle33;
		circleArray[3][4]= circle34;
		circleArray[3][5]= circle35;

		circleArray[4][0]= circle40;
		circleArray[4][1]= circle41;
		circleArray[4][2]= circle42;
		circleArray[4][3]= circle43;
		circleArray[4][4]= circle44;
		circleArray[4][5]= circle45;

		circleArray[5][0]= circle50;
		circleArray[5][1]= circle51;
		circleArray[5][2]= circle52;
		circleArray[5][3]= circle53;
		circleArray[5][4]= circle54;
		circleArray[5][5]= circle55;

		circleArray[6][0]= circle60;
		circleArray[6][1]= circle61;
		circleArray[6][2]= circle62;
		circleArray[6][3]= circle63;
		circleArray[6][4]= circle64;
		circleArray[6][5]= circle65;

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/*
	public static PlayingFieldController getPlayingFieldController(){
		return playingFieldController;

	}
	 */

	public void showMove(int column, int row, int color)
	{

		if(color==1)
		{
			circleArray[column][row].getStyleClass().remove("emptyCircle");
			circleArray[column][row].getStyleClass().add("yellowCircle");
		}
		if(color==2)
		{
			circleArray[column][row].getStyleClass().remove("emptyCircle");
			circleArray[column][row].getStyleClass().add("redCircle");
		}

	}
	
	public void updateDisplay(int setId, int[] score){
		btNextSet.setDisable(false);
		
	}

	//@FXML
	//private void handleClickMe(){


	//	modelController.startGame(modelController.getGame().getName(), modelController.getGame().getPlayer());

		//		
		//		int color = 1;
		//		int column = 0;
		//		int row = 0;
		//		
		//		for(column=0;column<7;column++)
		//		{
		//			
		//			for(row=0;row<6;row++)
		//			{
		//				showMove(column, row, field[row][column]);
		//				
		//				
		//			}
		//			
		//						
		//		}


	//}
	
	@FXML
	private void handleStartSet(){	
		btEndSet.setDisable(false);
		Controller.getController().start();
	}
	@FXML
	private void handleNextSet(){
		System.out.println("next set");
	}
	@FXML
	private void handleEndSet(){
		System.out.println("end set");
		btEndSet.setDisable(true);
		btNextSet.setDisable(false);
		//ausgrauen von StartSet_Button
	}
	@FXML
	private void handleEndGame(){
		System.out.println("end game");
	}
	
	@FXML
	public void circleAction(){

	}
	
	

}
