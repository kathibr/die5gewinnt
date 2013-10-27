package de.dhbw.die5gewinnt.controller.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import de.dhbw.die5gewinnt.controller.Controller;

public class GameNameDialogController {

	@FXML
	private TextField gameName, filePath, opponentName;
	@FXML
	private RadioButton playerX, playerO;
	@FXML
	private Label errorMessageLabel;
	
	private Stage dialogStage;
	private boolean okClicked = false;

	private final String PLAYER_X = "X";
	private final String PLAYER_O = "O";
	private final String SERVER_FILE_X = "server2spielerx.xml";
	private final String SERVER_FILE_O = "server2spielero.xml";
	private final String AGENT_FILE_X = "spielerx2server.txt";
	private final String AGENT_FILE_O = "spielero2server.txt";
	private final String EMPTY_MESSAGE = "";
	private final String INVALID_NAME = "Kein zugelassener Spielname!\n";
//	private final String CORRECT_INVALID_FIELDS = "Bitte unzulässige Felder korrigieren!";
//	private final String INVALID_FIELD = "Unzulässiges Feld";

	private String fullFilePath;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void findPath() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Wähle einen Pfad aus");

		File file = directoryChooser.showDialog(dialogStage);

		if (file != null) {
			filePath.setText(file.getPath());
		}
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {
			// Create Game
			String player = PLAYER_X;
			if (playerO.isSelected())
				player = PLAYER_O;
			Controller
					.getController()
					.getModelController()
					.newGame(gameName.getText(), player, opponentName.getText());
			// Create CommunicationCenter
			String serverFile = SERVER_FILE_X;
			String agentFile = AGENT_FILE_X;
			if (playerO.isSelected()) {
				serverFile = SERVER_FILE_O;
				agentFile = AGENT_FILE_O;
			}
			fullFilePath = filePath.getText() + "\\";
			Controller.getController().setCommunicationController(fullFilePath,
					serverFile, agentFile);
			okClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private boolean isInputValid() {
		String errorMessage = EMPTY_MESSAGE;

		if (gameName.getText() == null || gameName.getText().length() == 0)
			errorMessage += INVALID_NAME;

		if (errorMessage.length() == 0)
			return true;
		else {
			errorMessageLabel.setText(errorMessage);
			// Dialogs.showErrorDialog(dialogStage, errorMessage,
			// CORRECT_INVALID_FIELDS, INVALID_FIELD);
			return false;
		}
	}

}