package de.dhbw.die5gewinnt;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import de.dhbw.die5gewinnt.view.*;

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
					
			PlayingFieldController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
	      return primaryStage;
	}
	
	public static void main(String[] args) {
	      launch(args);
	}
	
}