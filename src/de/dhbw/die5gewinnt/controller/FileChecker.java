package de.dhbw.die5gewinnt.controller;

import java.io.File;

public class FileChecker extends Thread {

	private String path, file, filePath;
	private boolean existingServerFile;
	
	public FileChecker() {	
		this.setPath("");
		this.setFile("");
		this.setExistingServerFile(false);
	}
	
	public FileChecker(String path, String file) {
		this.setPath(path);
		this.setFile(file);
		this.setExistingServerFile(false);
	}
	
	public void run() {
		File f = new File(this.getFilePath());
		while(!this.getExistingServerFile()) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(f.exists())
				this.setExistingServerFile(true);
		}
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
	
	public boolean getExistingServerFile() {
		return this.existingServerFile;
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
	
	private void setExistingServerFile(boolean existingServerFile) {
		this.existingServerFile = existingServerFile;
	}
	
}