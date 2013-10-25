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
import de.dhbw.die5gewinnt.MainApp;
import de.dhbw.die5gewinnt.controller.db.DBSelects;
import de.dhbw.die5gewinnt.model.Game;
import de.dhbw.die5gewinnt.model.Set;

public class OldGamesController {

	@SuppressWarnings("unused")
	private MainApp mainApp;
	private Game[] oldGames;

	@FXML
	private TableView<Game> gameTable;
	@FXML
	private TableColumn<Game, String> gameNameColumn;
	@FXML
	private ObservableList<Game> oList;
	@FXML
	private Label gameName, playerXName, playerOName, playerXScore, playerOScore;
	@FXML
	private Button btSetOne;
	@FXML
	private Button btSetTwo;
	@FXML
	private Button btSetThree;
	@FXML
	private Label lbSetOneX, lbSetOneO, lbSetTwoX, lbSetTwoO, lbSetThreeX, lbSetThreeO;
	
	

	public OldGamesController() {
		oList = FXCollections.observableArrayList();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		loadGameData();
	}

	@FXML
	private void initialize() {
		gameNameColumn
				.setCellValueFactory(new PropertyValueFactory<Game, String>(
						"name"));

		showGameDetails(null);

		gameTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Game>() {

					@Override
					public void changed(
							ObservableValue<? extends Game> observable,
							Game oldValue, Game newValue) {
						showGameDetails(newValue);
					}

				});
		;

		Platform.runLater(new Runnable() {
			public void run() {
				gameTable.getSelectionModel().selectFirst();
			}
		});
	}

	private void showGameDetails(Game game) {
		if (game == null) {
			gameName.setText("");
			playerOName.setText("");
			playerXName.setText("");
		} else {

			Set sets[] = game.getSets();
			
			if (game.getPlayer().equals("X")) {
				playerXName.setText("die5gewinnt");
				playerOName.setText(game.getOpponentName());
				int[] score = game.getScore();
				playerXScore.setText(new Integer(score[0]).toString());
				playerOScore.setText(new Integer(score[1]).toString());				
							
				for(int i = 0; i < sets.length; i++) {
					if(sets[i].getStatus() == 0) { // We/X lose
						lbSetOneX.setText("Lose");
						lbSetOneO.setText("Win");						
					} else if(sets[i].getStatus() == 1) { // We/X win
						lbSetOneX.setText("Win");
						lbSetOneO.setText("Lose");						
					} else if(sets[i].getStatus() == 2) { // remis
						lbSetOneX.setText("Remis");
						lbSetOneO.setText("Remis");					
					}
				}
			} else {
				playerOName.setText("die5gewinnt");
				playerXName.setText(game.getOpponentName());
				int[] score = game.getScore();
				playerXScore.setText(new Integer(score[1]).toString());
				playerOScore.setText(new Integer(score[0]).toString());

				for(int i = 0; i < sets.length; i++) {
					if(sets[i].getStatus() == 0) { // We/O lose
						lbSetOneX.setText("Win");
						lbSetOneO.setText("Lose");						
					} else if(sets[i].getStatus() == 1) { // We/O win
						lbSetOneX.setText("Lose");
						lbSetOneO.setText("Win");						
					} else if(sets[i].getStatus() == 2) { // remis
						lbSetOneX.setText("Remis");
						lbSetOneO.setText("Remis");					
					}
				}
			}

			gameName.setText(game.getName());

		}

	}

	private void loadGameData() {

		oldGames = DBSelects.selectGames(true);
		for (int i = 0; i < oldGames.length; i++) {
			oList.add(oldGames[i]);
		}
		gameTable.setItems(oList);

	}
	@FXML
	private void clickSetOne(){
		//Start showing moves
		if(btSetOne.getText().equals("Start")){
			btSetOne.setText("Stop");
			btSetTwo.setText("Start");
			btSetThree.setText("Start");
		} else {
			btSetOne.setText("Start");
		}
	}
	
	
	@FXML
	private void clickSetTwo(){
		if(btSetTwo.getText().equals("Start")){
			btSetTwo.setText("Stop");
			btSetOne.setText("Start");
			btSetThree.setText("Start");
		} else {
			btSetTwo.setText("Start");
		}
	}
	@FXML
	private void clickSetThree(){
		if(btSetThree.getText().equals("Start")){
			btSetThree.setText("Stop");
			btSetOne.setText("Start");
			btSetTwo.setText("Start");
		} else {
			btSetThree.setText("Start");
		}
	}

}