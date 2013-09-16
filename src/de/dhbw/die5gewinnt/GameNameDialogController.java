package de.dhbw.die5gewinnt;

import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GameNameDialogController {

	@FXML
	private TextField gameName;
	private Stage dialogStage;
	
	private boolean okClicked = false;
	
	  @FXML
	  private void initialize(){
		  
	  }
	
	 public void setDialogStage(Stage dialogStage){
		  this.dialogStage = dialogStage;
	  }
	 public boolean isOkClicked(){
		  return okClicked;
	  }

	 
	 @FXML
	  private void handleOk(){
		     if (isInputValid()) {
		    	
		          okClicked = true;
		          dialogStage.close();

		      }  

	  }
	  @FXML
	  private void handleCancel() {
	      dialogStage.close();
	  }
	  
	  
	  private boolean isInputValid(){
		  String errorMessage="";		  
		  
		  
		  
		  if(gameName.getText() == null || gameName.getText().length() == 0){
			  errorMessage += "No valid game name!\n";
		  
		  }
		  
	      
	      if (errorMessage.length() == 0) {
	          return true;
	      } else {

	          Dialogs.showErrorDialog(dialogStage, errorMessage,
	                  "Please correct invalid fields", "Invalid Fields");
	          return false;
	      }
	      
	      
	  }
	  
	  
}
