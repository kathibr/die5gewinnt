package de.dhbw.die5gewinnt.model;

public class Move {

	private int row;
	private int column;
	private String player;
	
	public Move() {
		this.setRow(0);
		this.setColumn(0);
		this.setPlayer("X"); // or "O"
	}
	
	/* GETTER-Methods */
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public String getPlayer() {
		return this.player;
	}
	
	/* SETTER-Methods */
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setPlayer(String player) {
		this.player = player;
	}
	
}