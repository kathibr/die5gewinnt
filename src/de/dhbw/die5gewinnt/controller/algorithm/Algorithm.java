package de.dhbw.die5gewinnt.controller.algorithm;

public interface Algorithm {
	
	public int[][] field = new int[7][6];
	public int[] columnHeight = new int[7];
	
	public int calcNextColumn();
	
}