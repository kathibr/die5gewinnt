package de.dhbw.die5gewinnt;

import java.io.IOException;

import org.jdom2.JDOMException;

import de.dhbw.die5gewinnt.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
		  
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

		    // Give the controller access to the main app
		    RootLayoutController controller = loader.getController();
		    controller.setMainApp(this);

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
			
			PlayingFieldController controller = loader.getController();
			controller.setMainApp(this);
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void showOldGames() {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/OldGames.fxml"));
			AnchorPane oldGamesPage = (AnchorPane) loader.load();
			rootLayout.setCenter(oldGamesPage);
					
			OldGamesController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean showGameNameDialog(){
		try{
			
			// Load the fxml file and create a new stage for the popup
		    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/GameNameDialog.fxml"));
		    AnchorPane page = (AnchorPane) loader.load();
		    Stage dialogStage = new Stage();
		    dialogStage.setTitle("Neues Spiel");
		    dialogStage.initModality(Modality.WINDOW_MODAL);
		    dialogStage.initOwner(primaryStage);
		    Scene scene = new Scene(page);
		    dialogStage.setScene(scene);

		    
		    GameNameDialogController controller = loader.getController();
		    controller.setDialogStage(dialogStage);
		    //controller.setPerson(person);

		    // Show the dialog and wait until the user closes it
		    dialogStage.showAndWait();
	    
		    return controller.isOkClicked();
			
		}catch(IOException e){
			e.printStackTrace();
		    return false;
			
		}
	}
	
	public Stage getPrimaryStage() {
	      return primaryStage;
	}
	
	
	public static void main(String[] args) throws JDOMException, IOException, InterruptedException {	
		Controller.getController();
		//Controller unbedingt in einen eigenen Thread, damit Controller und Oberfläche parallel laufen!
		launch(args);
	  }
}
