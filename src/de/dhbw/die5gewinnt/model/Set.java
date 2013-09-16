package de.dhbw.die5gewinnt.model;

public class Set {
	
	private int id;
	private Move[] moves;
	private int[][] field;
	private int[] columnHeight;
	
	public Set() {
		this.setId(0);
		this.setMoves(new Move[42]);
		this.setField(new int[7][6]);
		this.setColumnHeight(new int[7]);
	}
	
	public Set(Move[] moves, int[][] field, int[] columnHeight) {
		this.setId(0);
		this.setMoves(moves);
		this.setField(field);
		this.setColumnHeight(columnHeight);		
	}
	
	/* GETTER-Methods */
	public int getId() {
		return this.id;
	}	
	
	public Move[] getMoves() {
		return this.moves;
	}
	
	public int[][] getField() {
		return this.field;
	}
	
	public int[] getColumnHeight() {
		return this.columnHeight;
	}
	
	/* SETTER-Methods */
	public void setId(int id) {
		this.id = id;
	}
	
	public void setMoves(Move[] moves) {
		this.moves = moves;
	}
	
	public void setField(int[][] field) {
		this.field = field;
	}
	
	public void setColumnHeight(int[] columnHeight) {
		this.columnHeight = columnHeight;
	}

}