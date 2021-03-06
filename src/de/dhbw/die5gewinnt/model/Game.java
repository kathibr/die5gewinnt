package de.dhbw.die5gewinnt.model;

public class Game {
	
	private int id;
	private String name;
	private Set[] sets;
	private int[] score;
	private boolean winner;
	private String player;
	private String opponentName;
	
	public Game() {
		this.setId(0);
		this.setName("");
		this.setSets(new Set[3]);
		this.setScore(new int[2]);
		this.setWinner(true);
		this.setPlayer("");
		this.setOpponentName("");
	}
	
	public Game(String name, Set[] sets, int[] score, boolean winner, String player, String opponentName) {
		this.setId(0);
		this.setName(name);
		this.setSets(sets);	
		this.setScore(score);
		this.setWinner(winner);
		this.setPlayer(player);
		this.setOpponentName(opponentName);
	}
	
	/* GETTER-Methods */
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Set[] getSets() {
		return this.sets;
	}
	
	public int[] getScore() {
		return this.score;
	}
	
	public boolean getWinner() {
		return this.winner;
	}
	
	public String getPlayer() {
		return this.player;
	}
	
	public String getOpponentName() {
		return this.opponentName;
	}
	
	/* SETTER-Methods */
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSets(Set[] sets) {
		this.sets = sets;
	}
	
	public void setScore(int[] score) {
//		System.out.println("Game - Score: "+ score[0]+" - " +score[1]);
		this.score = score;
	}
	
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	
	public void setPlayer(String player) {
		this.player = player;
	}
	
	public void setOpponentName(String opponentName) {
		this.opponentName = opponentName;
	}

}