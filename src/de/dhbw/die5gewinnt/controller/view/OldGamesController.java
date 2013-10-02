package de.dhbw.die5gewinnt.controller.view;


import de.dhbw.die5gewinnt.MainApp;
import de.dhbw.die5gewinnt.controller.db.DBSelects;
import de.dhbw.die5gewinnt.model.Game;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
	private Label gameName;
	@FXML
	private Label playerXName;
	@FXML
	private Label playerOName;
	
	

	public OldGamesController() {
		oList = FXCollections.observableArrayList();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		loadGameData();     
	}

	@FXML
	private void initialize() {
		gameNameColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("name"));
		
		showGameDetails(null);
		
		gameTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Game>(){

		
			@Override
			public void changed(ObservableValue<? extends Game> observable,
					Game oldValue, Game newValue) {
				showGameDetails(newValue);				
			}	
			
		});;
		
		
		
	     
	      
		
		
	}

	private void showGameDetails(Game game){
		if(game==null){
			gameName.setText("");
			playerOName.setText("");
			playerXName.setText("");
		}else{
			gameName.setText(game.getName());
		//	playerOName.setText(game.getPlayer());
//			playerXName.setText(value);
		}
		
	}
	private void loadGameData() {
	
		oldGames = DBSelects.selectGames(true);
		for(int i = 0; i < oldGames.length; i++){
			oList.add(oldGames[i]);
		}
		gameTable.setItems(oList);
		
	}

}