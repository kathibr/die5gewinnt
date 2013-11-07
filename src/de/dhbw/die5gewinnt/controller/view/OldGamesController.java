package de.dhbw.die5gewinnt.controller.view;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import de.dhbw.die5gewinnt.MainApp;
import de.dhbw.die5gewinnt.controller.db.DBSelects;
import de.dhbw.die5gewinnt.model.Game;
import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.Set;

public class OldGamesController {

	@SuppressWarnings("unused")
	private MainApp mainApp;
	private Game[] oldGames;
	private Game game;

	@FXML
	private TableView<Game> gameTable;
	@FXML
	private TableColumn<Game, String> gameNameColumn;
	@FXML
	private ObservableList<Game> oList;
	@FXML
	private Label gameName, playerXName, playerOName, playerXScore, playerOScore;
	@FXML
	private Label set1, set2, set3;
	@FXML
	private Button btSetOne, btSetTwo, btSetThree;
	@FXML
	private Label lbSetOneX, lbSetOneO, lbSetTwoX, lbSetTwoO, lbSetThreeX, lbSetThreeO;
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

	private final String NOMEN_NESCIO = "N.N.";
	private final int YELLOW = 1;
	private final int RED = 2;
	private final String PROPERTY_NAME = "name";
	private final String SHOW = "Zeige";
	private final String DIE5GEWINNT = "die5gewinnt";
	private final String LOSE ="Lose";
	private final String WIN="Win";
	private final String REMIS="Remis";
	private final String PLAYER_X ="X";
	private final String CSS_YELLOW ="yellowCircle";
	private final String CSS_RED="redCircle";
	private final String CSS_EMPTY="emptyCircle";
	private final String CSS_BOLD = "boldFont";
	private final String CSS_NORMAL = "normalFont";
	private final String ERROR_NO_MOVE ="-- No Move to show!";

	public OldGamesController() {
		oList = FXCollections.observableArrayList();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		loadGameData();
	}

	@FXML
	private void initialize() {
		gameNameColumn.setCellValueFactory(new PropertyValueFactory<Game, String>(PROPERTY_NAME));

		showGameDetails(null);

		gameTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Game>() {

			@Override
			public void changed(
					ObservableValue<? extends Game> observable,
					Game oldValue, Game newValue) {
				// Initialize the Set Labels
				set1.getStyleClass().add(CSS_NORMAL);
				set1.getStyleClass().remove(CSS_BOLD);
				set2.getStyleClass().add(CSS_NORMAL);
				set2.getStyleClass().remove(CSS_BOLD);
				set3.getStyleClass().add(CSS_NORMAL);
				set3.getStyleClass().remove(CSS_BOLD);
				
				// Initialize the Show-Buttons for the three Sets
				btSetOne.setText(SHOW);
				btSetOne.setDisable(false);

				btSetTwo.setText(SHOW);
				btSetTwo.setDisable(false);

				btSetThree.setText(SHOW);
				btSetThree.setDisable(false);
				
				clearPlayingField();
				showGameDetails(newValue);
			}

		});

		Platform.runLater(new Runnable() {
			public void run() {
				gameTable.getSelectionModel().selectFirst();
			}
		});

		circleArray = new Circle[7][6];
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

	private void loadGameData() {
		oldGames = DBSelects.selectGames(true);
		for (int i = 0; i < oldGames.length; i++) {
			oList.add(oldGames[i]);
		}
		gameTable.setItems(oList);
	}

	private void showGameDetails(Game game) {
		this.game = game;

		if(game == null) {
			gameName.setText(NOMEN_NESCIO);
			playerXName.setText(NOMEN_NESCIO);
			playerOName.setText(NOMEN_NESCIO);	
		} else {
			Set sets[] = game.getSets();
			if(sets.length < 3) {
				btSetThree.setDisable(true);
			}
			if(sets.length < 2) {
				btSetTwo.setDisable(true);
			}
			if(sets.length < 1) {
				btSetOne.setDisable(true);
			}

			Label[] xLabels = new Label[3];
			xLabels[0] = lbSetOneX;
			xLabels[1] = lbSetTwoX;
			xLabels[2] = lbSetThreeX;
			Label[] oLabels = new Label[3];
			oLabels[0] = lbSetOneO;
			oLabels[1] = lbSetTwoO;
			oLabels[2] = lbSetThreeO;
			gameName.setText(game.getName());
			if(game.getPlayer().equals(PLAYER_X)) {			
				playerXName.setText(DIE5GEWINNT);
				playerOName.setText(game.getOpponentName());

				int[] score = game.getScore();
				playerXScore.setText(new Integer(score[0]).toString());
				playerOScore.setText(new Integer(score[1]).toString());

				for(int i = 0; i < sets.length; i++) {
					if(sets[i].getStatus() == 0) { // We/X lose
						xLabels[i].setText(LOSE);
						oLabels[i].setText(WIN);						
					} else if(sets[i].getStatus() == 1) { // We/X win
						xLabels[i].setText(WIN);
						oLabels[i].setText(LOSE);						
					} else if(sets[i].getStatus() == 2) { // Remis
						xLabels[i].setText(REMIS);
						oLabels[i].setText(REMIS);					
					}
				}
			} else {
				playerOName.setText(DIE5GEWINNT);
				playerXName.setText(game.getOpponentName());

				int[] score = game.getScore();
				playerXScore.setText(new Integer(score[1]).toString());
				playerOScore.setText(new Integer(score[0]).toString());

				for(int i = 0; i < sets.length; i++) {
					if(sets[i].getStatus() == 0) { // We/O lose
						xLabels[i].setText(WIN);
						oLabels[i].setText(LOSE);						
					} else if(sets[i].getStatus() == 1) { // We/O win
						xLabels[i].setText(LOSE);
						oLabels[i].setText(WIN);						
					} else if(sets[i].getStatus() == 2) { // Remis
						xLabels[i].setText(REMIS);
						oLabels[i].setText(REMIS);					
					}
				}
			}
		}
	}

	@FXML
	private void clickSetOne() {
		set1.getStyleClass().remove(CSS_NORMAL);
		set1.getStyleClass().add(CSS_BOLD);		
		set2.getStyleClass().add(CSS_NORMAL);
		set2.getStyleClass().remove(CSS_BOLD);
		set3.getStyleClass().add(CSS_NORMAL);
		set3.getStyleClass().remove(CSS_BOLD);	
		clearPlayingField();
		Set[] sets = game.getSets();
		if(sets.length >= 1); {
			Move[] moves = sets[0].getMoves();
			for(int i = 0; i < moves.length; i++) {		
				if(moves[i].getPlayer().equals(game.getPlayer()))
					showMove(moves[i].getColumn(), moves[i].getRow(), RED);
				else
					showMove(moves[i].getColumn(), moves[i].getRow(), YELLOW);
			}
		}
	}

	@FXML
	private void clickSetTwo() {
		set1.getStyleClass().add(CSS_NORMAL);
		set1.getStyleClass().remove(CSS_BOLD);		
		set2.getStyleClass().remove(CSS_NORMAL);
		set2.getStyleClass().add(CSS_BOLD);
		set3.getStyleClass().add(CSS_NORMAL);
		set3.getStyleClass().remove(CSS_BOLD);	
		clearPlayingField();
		Set[] sets = game.getSets();
		if(sets.length >= 2); {
			Move[] moves = sets[1].getMoves();
			for(int i = 0; i < moves.length; i++) {		
				if(moves[i].getPlayer().equals(game.getPlayer()))
					showMove(moves[i].getColumn(), moves[i].getRow(), RED);
				else
					showMove(moves[i].getColumn(), moves[i].getRow(), YELLOW);
			}
		}
	}

	@FXML
	private void clickSetThree() {
		set1.getStyleClass().add(CSS_NORMAL);
		set1.getStyleClass().remove(CSS_BOLD);		
		set2.getStyleClass().add(CSS_NORMAL);
		set2.getStyleClass().remove(CSS_BOLD);	
		set3.getStyleClass().remove(CSS_NORMAL);
		set3.getStyleClass().add(CSS_BOLD);
		clearPlayingField();
		Set[] sets = game.getSets();
		if(sets.length == 3) {
			Move[] moves = sets[2].getMoves();
			for(int i = 0; i < moves.length; i++) {		
				if(moves[i].getPlayer().equals(game.getPlayer()))
					showMove(moves[i].getColumn(), moves[i].getRow(), RED);
				else
					showMove(moves[i].getColumn(), moves[i].getRow(), YELLOW);
			}
		}
	}

	public boolean showMove(int column, int row, int color){
		try {
			if(color == YELLOW) {
				circleArray[column][row].getStyleClass().remove(CSS_EMPTY);
				circleArray[column][row].getStyleClass().add(CSS_YELLOW);
			} if(color == RED) {
				circleArray[column][row].getStyleClass().remove(CSS_EMPTY);
				circleArray[column][row].getStyleClass().add(CSS_RED);
			}
			return true;
		} catch (Exception e) {
			System.err.println(ERROR_NO_MOVE);
			System.err.println(e.getStackTrace());
			return false;
		}
	}

	public void clearPlayingField() {
		for(int column = 0; column < 7; column++) {		
			for(int row = 0; row < 6; row++) {
				try {
					circleArray[column][row].getStyleClass().remove(CSS_YELLOW);
					circleArray[column][row].getStyleClass().add(CSS_EMPTY);
				} catch (Exception e) {
					//					System.err.println("-- .yellowCircle not found!");
				}
				try {
					circleArray[column][row].getStyleClass().remove(CSS_RED);
					circleArray[column][row].getStyleClass().add(CSS_EMPTY);
				} catch (Exception e) {
					//					System.err.println("-- .redCircle not found!");
				}
			}
		}	
	}

}
