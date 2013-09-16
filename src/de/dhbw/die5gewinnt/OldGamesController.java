package de.dhbw.die5gewinnt;

import de.dhbw.die5gewinnt.controller.db.DBSelects;
import de.dhbw.die5gewinnt.model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class OldGamesController {

	private MainApp mainApp;
	private Game[] oldGames;
	
	@FXML
	private ListView<String> listOfGames;
	private ObservableList<String> oList;
	
	public OldGamesController() {
		oList = FXCollections.observableArrayList();
	}
	
	public void setMainApp(MainApp mainApp) {
	      this.mainApp = mainApp;      
	}

	@FXML
	private void initialize() {
		loadGameData();
	}
	
	private void loadGameData() {
		oldGames = DBSelects.selectGames(true);
	    for(int i = 0; i < oldGames.length; i++){
	    	oList.add(oldGames[i].getName());
	    }
	    listOfGames.setItems(oList);		
	}
	
//	public ObservableList<String> getGameData() {
//	      return oList;
//	}
	
}