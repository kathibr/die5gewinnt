package de.dhbw.die5gewinnt.model;

public class Set {
	
	private Move[] moves;
	private int[][] field;
	private int[] columnHeight;
	
	public Set() {
		this.setMoves(new Move[42]);
		this.setField(new int[7][6]);
		this.setColumnHeight(new int[7]);
	}
	
	/* GETTER-Methods */
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