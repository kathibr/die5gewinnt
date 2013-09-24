package de.dhbw.die5gewinnt.controller.algorithm;

import de.dhbw.die5gewinnt.model.Move;

public class MastermindAlgorithm implements Algorithm, Runnable {

	public int nextColumn = -1;
	private Move[][] possibleCombinations;
	private Move[][] field;
	private int zeile,spalte;
	
	public MastermindAlgorithm(de.dhbw.die5gewinnt.model.Set set){
		field = set.getField();
		possibleCombinations = new Move[69][4];
	}
	
	/* ALGORITHM-Interface */
	@Override
	public void calcNextColumn() {
		for(int i = 0; i<50000000; i++) {
			for(int j = 0; j<5000000; j++) {
			}
		}
		System.out.println("MastermindAlgorithm: 8");
		nextColumn = 8;
	}
	
	public void fillHorizontal(){
		int grenze = 2;
		int start = 5;
		for(int i = 3; i >= 0; i--){
			for(int x = 6; x >= 0; x--){
				spalte = 0;
				for(int y = start; y >= grenze; y--){
					possibleCombinations[zeile][spalte] = field[x][y];
					spalte++;
					System.out.println(x+y);
				}
				grenze--;
				start--;
				zeile++;
			}
		}
	}
	
	/* RUNNABLE-Interface */
	@Override
	public void run() {
		calcNextColumn();
	}

}