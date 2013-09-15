package de.dhbw.die5gewinnt;

import de.dhbw.die5gewinnt.controller.db.DBSelects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class OldGamesController {

	private MainApp mainApp;

	private String[] gameNames;
	@FXML
	private ListView<String> gameList;
	private ObservableList<String> oList = FXCollections.observableArrayList();
	
	
	
	public OldGamesController() {
	}

	@FXML
	private void initialize() {
		gameNames = DBSelects.selectGameNames();
	    for(int i = 0; i < gameNames.length; i++){
	    	oList.add(gameNames[i]);
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