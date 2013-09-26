package de.dhbw.die5gewinnt.controller.algorithm;

import java.util.ArrayList;

import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.Set;

public class MastermindAlgorithm implements Runnable {

	public int nextColumn = -1;
	private Move[][] field = new Move[7][6];
	private int[] columnHeight = new int[7];
	private final String X="X";
	private final String O="O";

	private ArrayList<Move[]> possibleCombinations = new ArrayList<Move[]>(69);
	private ArrayList<Move[]> threeInARow = new ArrayList<Move[]>();
	private ArrayList<Move[]> twoInARow = new ArrayList<Move[]>();
	private ArrayList<Move[]> oneInARow = new ArrayList<Move[]>();
	
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
	
	public void analyzeField(){
		int numberOfO = 0, numberOfX = 0;
		boolean deleted;
		for(int i = 0; i < possibleCombinations.size(); i++){
			Move[] row = possibleCombinations.get(i);
			deleted = false;
			for(int x = 0; x < 4; x++){
				if(!deleted){
					if(row[x].getPlayer() == X){
						if(numberOfO > 0){
							possibleCombinations.remove(i);
							deleted = true;
						}
						else numberOfX++;
					}
					else if(row[x].getPlayer() == O){
						if(numberOfX > 0){
							possibleCombinations.remove(i);
							deleted = true;
						}
						else numberOfO++;
					}
				}
			}
			storeCombinations(row, numberOfO, numberOfX);
		}
	}
	
	public void storeCombinations(Move[] row, int numberOfO,int numberOfX){
		if(numberOfO == 3 || numberOfX == 3) threeInARow.add(row);
		if(numberOfO == 2 || numberOfX == 2) twoInARow.add(row);
		if(numberOfO == 1 || numberOfX == 1) oneInARow.add(row);
	}
	
	/* RUNNABLE-Interface */
	@Override
	public void run() {
		calcNextColumn();
	}

}