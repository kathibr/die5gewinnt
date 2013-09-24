package de.dhbw.die5gewinnt.controller.communication;


public class CommunicationController {
	
	private String path, serverFile, agentFile, serverFilePath, agentFilePath;
	
	
	public CommunicationController() {
		setPath("");
		setServerFile("");
		setAgentFile("");
	}
	
	public CommunicationController(String path, String serverFile, String agentFile) {
		setPath(path);
		setServerFile(serverFile);
		setAgentFile(agentFile);
	}
	
	/* GETTER-Methods */
	private String getPath() {
		return this.path;
	}
	
	private String getServerFile() {
		return this.serverFile;
	}

	private String getAgentFile() {
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
		if(this.getServerFile() != null && this.getAgentFile() != null) {
			this.setServerFilePath();
			this.setAgentFilePath();
		}
	}

	public void setServerFile(String serverFile) {
		this.serverFile = serverFile;
		if(this.getPath() != null) {
			this.setServerFilePath();
		}
	}
	
	public void setAgentFile(String agentFile) {
		this.agentFile = agentFile;
		if(this.getPath() != null) {
			this.setAgentFilePath();
		}
	}

	private void setServerFilePath() {
		if(this.getServerFile().equals(""))
			System.err.println("--- There is no serverfile information!");
		else
			this.serverFilePath = this.getPath().concat(this.getServerFile());
	}
	
	private void setAgentFilePath() {
		if(this.getAgentFile().equals(""))
			System.err.println("--- There is no agentfile information!");
		else
			this.agentFilePath = this.getPath().concat(this.getAgentFile());
	}
}