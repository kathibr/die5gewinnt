package de.projekt.viergewinnt;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import de.projekt.viergewinnt.view.*;

public class MainApp extends Application {
	//kathi4
	//Arne
	//blabla
	private Stage primaryStage;
	private BorderPane rootLayout;
	  
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
	    this.primaryStage.setTitle("die5gewinnt");
	
	
	try {
        // Load the root layout from the fxml file
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    } catch (IOException e) {
        // Exception gets thrown if the fxml file could not be loaded
        e.printStackTrace();
    }
    
    showSampleStage();
	
	}
	
	public Stage getPrimaryStage() {
	      return primaryStage;
	  }
	
	public void showSampleStage(){
		try {
	          // Load the fxml file and set into the center of the main layout
	          FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/SampleStage.fxml"));
	          AnchorPane overviewPage = (AnchorPane) loader.load();
	          rootLayout.setCenter(overviewPage);
	          
	      } catch (IOException e) {
	          // Exception gets thrown if the fxml file could not be loaded
	          e.printStackTrace();
	      }
	}
	
	public static void main(String[] args) {
	      launch(args);
	  }
}