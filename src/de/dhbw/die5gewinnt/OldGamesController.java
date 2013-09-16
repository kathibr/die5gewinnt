package de.dhbw.die5gewinnt;

import de.dhbw.die5gewinnt.controller.db.DBSelects;
import de.dhbw.die5gewinnt.model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class OldGamesController {

	private MainApp mainApp;

	private Game[] games;
	@FXML
	private ListView<String> gameList;
	private ObservableList<String> oList = FXCollections.observableArrayList();
	
	
	
	public OldGamesController() {
	}

	@FXML
	private void initialize() {
		games = DBSelects.selectGames();
	    for(int i = 0; i < games.length; i++){
	    	oList.add(games[i].getName());
	    }
	    gameList.setItems(oList);
	}
	public ObservableList<String> getGameData() {
	      return oList;
	  }
	public void setMainApp(MainApp mainApp) {
	      this.mainApp = mainApp;
	      
	}
	
}