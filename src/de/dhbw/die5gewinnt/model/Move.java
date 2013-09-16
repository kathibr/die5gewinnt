package de.dhbw.die5gewinnt.model;

public class Move {

	private int id;
	private int row;
	private int column;
	private String player;
	
	public Move() {
		this.setId(0);
		this.setRow(0);
		this.setColumn(0);
		this.setPlayer(null);
	}

	public Move(int row, int column, String player) {
		this.setId(0);
		this.setRow(row);
		this.setColumn(column);
		this.setPlayer(player);
	}
	
	/* GETTER-Methods */
	public int getId() {
		return this.id;
	}
	
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
	public void setId(int id) {
		this.id = id;
	}
	
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