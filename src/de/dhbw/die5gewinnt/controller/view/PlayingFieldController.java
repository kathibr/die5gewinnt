package de.dhbw.die5gewinnt.controller.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import de.dhbw.die5gewinnt.MainApp;
import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.controller.logic.ModelController;

public class PlayingFieldController {
	
	private ModelController modelController;
	private MainApp mainApp;

	@FXML
	private Rectangle playerXColor, playerOColor;
	@FXML 
	private Label gameNameLabel, scorePlayerO, currentSet;
@FXML
private Label playerNameO;
@FXML
private Label playerNameX;
@FXML
private Label scorePlayerX;
	@FXML	
	private Button btStartSet, btEndSet, btEndGame;	

	@FXML
	private Circle circle05, circle04, circle03, circle02, circle01, circle00;
	@FXML
	private Circle circle15, circle14, circle13, circle12, circle11, circle10;
	@FXML
	private Circle circle25, circle24, circle23, circle22, circle21, circle20;
	@FXML
	private Circle circle35, circle34, circle33, circle32, circle31, circle30;
	@FXML
	private Circle circle45, circle44, circle43, circle42, circle41, circle40;
	@FXML
	private Circle circle55, circle54, circle53, circle52, circle51, circle50;
	@FXML
	private Circle circle65, circle64, circle63, circle62, circle61, circle60;
	
	private Circle circleArray[][];
	
	private int i;
	private String stringSetId;
	private int[] score;


	public PlayingFieldController() {
	}

	@FXML
	private void initialize() {
		btEndSet.setDisable(true);
		
		modelController = Controller.getController().getModelController();
		gameNameLabel.setText(modelController.getGame().getName());
		if(modelController.getGame().getPlayer().equals("X")){
				playerNameX.setText("die5gewinnt");
				playerNameO.setText(modelController.getGame().getOpponentName());
				playerXColor.getStyleClass().removeAll("emptyCircle","redCircle","yellowCircle");
				playerXColor.getStyleClass().add("yellowCircle");
				playerOColor.getStyleClass().removeAll("emptyCircle","redCircle","yellowCircle");
				playerOColor.getStyleClass().add("redCircle");
				
				
		} else{
				playerNameO.setText("die5gewinnt");
				playerNameX.setText(modelController.getGame().getOpponentName());
				playerOColor.getStyleClass().removeAll("emptyCircle","redCircle","yellowCircle");
				playerOColor.getStyleClass().add("yellowCircle");
				playerXColor.getStyleClass().removeAll("emptyCircle","redCircle","yellowCircle");
				playerXColor.getStyleClass().add("redCircle");
		}
	
		

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
		
		i = 0;

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}


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
	
	public void clearPlayingField(){
		System.out.println("Clear PlayingField");
		currentSet.setText(stringSetId);
		
		for(int column = 0; column<7; column++){
			
			for(int row = 0; row<6; row++){

				try {
					circleArray[column][row].getStyleClass().remove("yellowCircle");
					circleArray[column][row].getStyleClass().add("emptyCircle");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				try {
					circleArray[column][row].getStyleClass().remove("redCircle");
					circleArray[column][row].getStyleClass().add("emptyCircle");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
			}
		}
		
	}

	
	@FXML
	private void handleStartSet(){	
		btEndSet.setDisable(false);
		btStartSet.setDisable(true);

		switch (i) {
		case 0:
			Controller.getController().start();
			break;
		case 3:
			break;
		default:

			scorePlayerO.setText(new Integer(score[0]).toString());
			System.out.println("Score0: "+score[0]+"/ Score1: "+score[1]);
			scorePlayerX.setText(new Integer(score[1]).toString());
			clearPlayingField();
			Controller.getController().resume();
		}
		i++;
		
	}
	
	@FXML
	public void handleEndSet(){
		
		btEndSet.setDisable(true);
		btStartSet.setDisable(false);
		
		System.out.println("end set");

		Controller.getController().getModelController().endSet();
		Controller.getController().suspend();
	}
	@FXML
	private void handleEndGame(){
		System.out.println("end game");
		try {
			Controller.getController().stop();
			
		} catch (Exception e) {
			System.out.println("Controller schon gestopt"+e);
		}
		}

	
	@FXML
	public void circleAction(){

	}

	public void updateDisplay(int setId, int[] score) {
		this.stringSetId = String.valueOf(setId);
		this.score=score;
		
		System.out.println("Update Display");
	}
	
	

}
