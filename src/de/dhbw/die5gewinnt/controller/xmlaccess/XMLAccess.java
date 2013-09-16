package de.dhbw.die5gewinnt.controller.xmlaccess;

public class XMLAccess {
	
	private static XMLAccess xmlAccess;
	private String path, file, filePath;
	
	private XMLAccess() {
		this.path = "";
		this.file = "";
	}
	
	private XMLAccess(String path, String file) {
		setPath(path);
		setFile(file);
	}
	
	/* GETTER-Methods */
	public static XMLAccess getXMLAccess() {
		if(xmlAccess == null)
			xmlAccess = new XMLAccess();
		return xmlAccess;
	}
	
	public static XMLAccess getXMLAccess(String path, String file) {
		if(xmlAccess == null)
			xmlAccess = new XMLAccess(path, file);
		return xmlAccess;
	}
	
	private String getPath() {
		return this.path;
	}
	
	private String getFile() {
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