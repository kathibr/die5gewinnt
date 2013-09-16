package de.dhbw.die5gewinnt.controller.algorithm;

public class MastermindAlgorithm implements Algorithm, Runnable {

	public int nextColumn = -1;
	
	/* ALGORITHM-Interface */
	@Override
	public void calcNextColumn() {
		for(int i = 0; i<500000000; i++) {
			for(int j = 0; j<500000000; j++) {
			}
		}
		System.out.println("MastermindAlgorithm: 8");
		nextColumn = 8;
	}
	
	/* RUNNABLE-Interface */
	@Override
	public void run() {
		calcNextColumn();
	}

}