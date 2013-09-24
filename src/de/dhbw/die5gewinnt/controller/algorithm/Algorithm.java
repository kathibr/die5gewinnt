package de.dhbw.die5gewinnt.controller.algorithm;

import de.dhbw.die5gewinnt.model.Move;

public interface Algorithm {
	
	public Move[][] field = new Move[7][6];
	public int[] columnHeight = new int[7];
	
	public void calcNextColumn();
	
}