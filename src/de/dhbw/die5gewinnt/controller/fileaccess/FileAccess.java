package de.dhbw.die5gewinnt.controller.fileaccess;

public class FileAccess {
	
	private String path, file, filePath;
	
	protected FileAccess() {
		this.setPath("");
		this.setFile("");	
	}

	protected FileAccess(String path, String file) {
		this.setPath(path);
		this.setFile(file);
	}
	
	/* GETTER-Methods */
	public String getPath() {
		return this.path;
	}
	
	public String getFile() {
		return this.file;
	}
	
	public String getFilePath() {
		return this.filePath;
	}
	
	/* SETTER-Methods */
	public void setPath(String path) {
		this.path = path;
		if(this.getFile() != null) {
			this.setFilePath();
		}
	}
	
	public void setFile(String file) {
		this.file = file;
		if(this.getPath() != null) {
			this.setFilePath();
		}
	}
	
	private void setFilePath() {
		if(this.getFile().equals(""))
			System.err.println("--- There is no serverfile information!");
		else
			this.filePath = this.getPath().concat(this.getFile());
	}
	
}