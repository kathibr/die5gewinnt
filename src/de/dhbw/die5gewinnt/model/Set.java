package de.dhbw.die5gewinnt.model;

public class Set {
	
	private int id;
	private Move[] moves;
	private Move[][] field;
	private int[] columnHeight;
	private boolean firstMove;
	private int status; // 0 > lose; 1 > win; 3 > remis
	
	public Set() {
		this.setId(0);
		this.setMoves(new Move[42]);
		this.setField(new Move[7][6]);
		this.setColumnHeight(new int[7]);
			for(int i = 0; i<7;i++){
				columnHeight[i]=0;
			}
		this.setFirstMove(false);
		this.setStatus(0);
	}
	
	public Set(Move[] moves, Move[][] field, int[] columnHeight, boolean firstMove, int status) {
		this.setId(0);
		this.setMoves(moves);
		this.setField(field);
		this.setColumnHeight(columnHeight);		
			for(int i = 0; i<7;i++){
				columnHeight[i]=0;
			}
		this.setFirstMove(firstMove);
		this.setStatus(status);
	}
	
	/* GETTER-Methods */
	public int getId() {
		return this.id;
	}	
	
	public Move[] getMoves() {
		return this.moves;
	}
	
	public Move[][] getField() {
		return this.field;
	}
	
	public int[] getColumnHeight() {
		return this.columnHeight;
	}
	
	public boolean getFirstMove() {
		return this.firstMove;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	/* SETTER-Methods */
	public void setId(int id) {
//		System.out.println("Die SetId des Satzes ist " + id);
		this.id = id;
	}
	
	public void setMoves(Move[] moves) {
		this.moves = moves;
	}
	
	public void setField(Move[][] field) {
		this.field = field;
	}
	
	public void setColumnHeight(int[] columnHeight) {
		this.columnHeight = columnHeight;
	}
	
	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

}