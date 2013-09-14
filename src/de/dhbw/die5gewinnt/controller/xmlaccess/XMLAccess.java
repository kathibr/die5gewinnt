package de.dhbw.die5gewinnt.controller.xmlaccess;

import org.jdom2.Element;

public class XMLAccess {
	
	private String path, file, filePath;
	protected Element root;
	
	protected XMLAccess() {
		this.setPath("C:/Users/D056939/Desktop/die5gewinnt/");
		this.setFile("server2spielero.xml");	
	}

	protected XMLAccess(String path, String file) {
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