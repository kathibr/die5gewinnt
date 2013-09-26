package de.dhbw.die5gewinnt;

import java.io.IOException;

import org.jdom2.JDOMException;

import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.controller.db.DBConnector;
import de.dhbw.die5gewinnt.controller.view.GameNameDialogController;
import de.dhbw.die5gewinnt.controller.view.OldGamesController;
import de.dhbw.die5gewinnt.controller.view.PlayingFieldController;
import de.dhbw.die5gewinnt.controller.view.RootLayoutController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private Controller controller;
		  
	public MainApp() {}
	
	@Override
	public void start(Stage primaryStage) {
			
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("die5gewinnt");
		
		  try {
		    // Load the root layout from the fxml file
		    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/RootLayout.fxml"));
		    rootLayout = (BorderPane) loader.load();
		    Scene scene = new Scene(rootLayout);
		    
		    primaryStage.setScene(scene);
		    primaryStage.setMinHeight(700);
		    primaryStage.setMinWidth(800);
		 //   primaryStage.initStyle(StageStyle.UNDECORATED);

		    // Give the controller access to the main app
		    RootLayoutController rootLayoutController = loader.getController();
		    rootLayoutController.setMainApp(this);

		    controller = Controller.getController();
		    
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			      public void handle(WindowEvent e){
			    	  DBConnector.closeDBConnection();			       
			      }
			});
		    primaryStage.show();
		    
		  } catch (IOException e) {
		    // Exception gets thrown if the fxml file could not be loaded
		    e.printStackTrace();
		  }
	}
	
	public void showPlayingField() {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/PlayingField.fxml"));
			AnchorPane playingFieldPage = (AnchorPane) loader.load();
			rootLayout.setCenter(playingFieldPage);
			
			
			PlayingFieldController playingFieldController = loader.getController();
			playingFieldController.setMainApp(this);
			controller.setPlayingFieldController(playingFieldController);	
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showOldGames() {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/OldGames.fxml"));
			AnchorPane oldGamesPage = (AnchorPane) loader.load();
			rootLayout.setCenter(oldGamesPage);
					
			OldGamesController oldGamesController = loader.getController();
			oldGamesController.setMainApp(this);		
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean showGameNameDialog() {
		try {	
			// Load the fxml file and create a new stage for the popup
		    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/GameNameDialog.fxml"));
		    AnchorPane page = (AnchorPane) loader.load();
		    Stage dialogStage = new Stage();
		    dialogStage.setTitle("Neues Spiel");
		    dialogStage.initModality(Modality.WINDOW_MODAL);
		    dialogStage.initOwner(primaryStage);
		    Scene scene = new Scene(page);
		    dialogStage.setScene(scene);

		    
		    GameNameDialogController gameNameDialogController = loader.getController();
		    gameNameDialogController.setDialogStage(dialogStage);
		    //controller.setPerson(person);

		    // Show the dialog and wait until the user closes it
		    dialogStage.showAndWait();
	    
		    return gameNameDialogController.isOkClicked();	
		} catch(IOException e) {
			e.printStackTrace();
		    return false;
			
		}
	}
	public void returnToStart(){
		rootLayout.setCenter(null);
	}
	
	public Stage getPrimaryStage() {
	      return primaryStage;
	}
	
	public static void main(String[] args) throws JDOMException, IOException, InterruptedException {	
		launch(args);
	  }
}