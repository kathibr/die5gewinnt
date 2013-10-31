package de.dhbw.die5gewinnt.controller.communication;

import de.dhbw.die5gewinnt.model.ServerFile;


public class CommunicationController {
	
	private String path, serverFileName, agentFile, serverFilePath, agentFilePath;
	private ServerFile serverFile;
	private boolean gameOver = false;
	
	public CommunicationController() {
		setPath("");
		setServerFileName("");
		setAgentFileName("");
	}
	
	public CommunicationController(String path, String serverFile, String agentFile) {
		setPath(path);
		setServerFileName(serverFile);
		setAgentFileName(agentFile);
	}
	
	/* GETTER-Methods */
	private String getPath() {
		return this.path;
	}
	
	private String getServerFileName() {
		return this.serverFileName;
	}

	private String getAgentFileName() {
		return this.agentFile;
	}
	
	public String getServerFilePath() {
		return this.serverFilePath;
	}
	
	public String getAgentFilePath() {
		return this.agentFilePath;
	}

	/* SETTER-Methods */
	public void setPath(String path) {
		this.path = path;
		if(this.getServerFileName() != null && this.getAgentFileName() != null) {
			this.setServerFilePath();
			this.setAgentFilePath();
		}
	}

	public void setServerFileName(String serverFileName) {
		this.serverFileName = serverFileName;
		if(this.getPath() != null) {
			this.setServerFilePath();
		}
	}
	
	public void setAgentFileName(String agentFile) {
		this.agentFile = agentFile;
		if(this.getPath() != null) {
			this.setAgentFilePath();
		}
	}

	private void setServerFilePath() {
		if(this.getServerFileName().equals(""))
			System.err.println("--- There is no serverfile information!");
		else
			this.serverFilePath = this.getPath().concat(this.getServerFileName());
	}
	
	private void setAgentFilePath() {
		if(this.getAgentFileName().equals(""))
			System.err.println("--- There is no agentfile information!");
		else
			this.agentFilePath = this.getPath().concat(this.getAgentFileName());
	}

	public ServerFile getServerFile() {
		return serverFile;
	}

	public void setServerFile(ServerFile serverfile) {
		serverFile = serverfile;
	}
	public void setGameOver(boolean gameOver){
//		System.out.println("Update GameOver");
		this.gameOver = gameOver;
	}
	public boolean getGameOver(){
		return gameOver;
	}
	
}