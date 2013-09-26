package de.dhbw.die5gewinnt.controller.algorithm;

import java.util.ArrayList;

import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.Set;

public class MastermindAlgorithm implements Runnable {

	public int nextColumn = -1;
	public Move[][] field = new Move[7][6];
	public int[] columnHeight = new int[7];

	private ArrayList<Move[]> possibleCombinations = new ArrayList<Move[]>(69);
	
	public MastermindAlgorithm(Set set){
		AlgorithmFiller filler = new AlgorithmFiller(set);
		possibleCombinations = filler.fillList();
	}
	
	/* ALGORITHM-Interface */
	public void calcNextColumn() {
		for(int i = 0; i<50000000; i++) {
			for(int j = 0; j<5000000; j++) {
			}
		}
		System.out.println("MastermindAlgorithm: 8");
		nextColumn = 8;
	}
	
	public int calculateMove(Move move){
		int random = (int) (Math.random() * 6);
		return random;
	}
	
	/* RUNNABLE-Interface */
	@Override
	public void run() {
		calcNextColumn();
	}

}