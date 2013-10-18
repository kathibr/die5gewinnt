package de.dhbw.die5gewinnt.controller.algorithm;

import java.util.ArrayList;
import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.model.Game;
import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.Set;

public class MastermindAlgorithm{

	public int nextColumn = -1;
//	private Move[][] field = new Move[7][6];
	private Set set;
//	private int[] columnHeight = new int[7];
	private final String X="X";
	private final String O="O";
	private int result, blockEnemy;
	private Game game;
	private final int NONEMISSING = 8;
	private final int ONEMISSING = 0;
	private final int TWOMISSING = 1;
	private final int THREEMISSING = 2;
	private final int DONTTHROW = -1000;

	private Move[][] possibleCombinations = new Move[4][69];
	private ArrayList<Integer> combinations = new ArrayList<Integer>(69);
	private ArrayList<Integer> threeInARow = new ArrayList<Integer>();
	private ArrayList<Integer> twoInARow = new ArrayList<Integer>();
	private ArrayList<Integer> oneInARow = new ArrayList<Integer>();
	private int[][] positions = new int[8][69];
	private int[] evaluateColumns = new int[7];
	private ArrayList<Integer> toBeRemoved = new ArrayList<Integer>(69);
	
	public MastermindAlgorithm(Set set){
		game = Controller.getController().getModelController().getGame();		
		this.set = set;
		AlgorithmFiller filler = new AlgorithmFiller(set);
		combinations = filler.fillList();
		possibleCombinations = filler.getPossibleCombinations();
		positions = filler.getPositions();
	}
	
	public int calcNextMove(){
		refreshEvaluation();
		analyzeField();
		result = -1;
		checkThreeInARow();
		if(result == -1){
			checkTwoInARow();
			analyzeResults();
			if(result == -1){
				checkOneInARow();
				analyzeResults();
			}
		}
		if(result == -1) result = 3;
		return result;
	}
	
	public void refreshArrays(){
		oneInARow.clear();
		twoInARow.clear();
		threeInARow.clear();
	}
	
	public void analyzeField(){
		for(int i = 0; i < combinations.size(); i++){
			int numberOfO = 0, numberOfX = 0;
			for(int x = 0; x < 4; x++){
					if(possibleCombinations[x][combinations.get(i)] != null){
						if(possibleCombinations[x][combinations.get(i)].getPlayer() == X){
							if(numberOfO > 0){
								toBeRemoved.add(i);
								break;
							}
							else numberOfX++;
						}
						else if(possibleCombinations[x][combinations.get(i)].getPlayer() == O){
							if(numberOfX > 0){
								toBeRemoved.add(i);
								break;
							}
							else numberOfO++;
						}
					}
			}
			storeCombinations(combinations.get(i), numberOfO, numberOfX);
		}
		removeFromPossibilitys();
	}
	
	public void removeFromPossibilitys(){
		for(int i = 0; i < toBeRemoved.size();i++){
			combinations.remove(toBeRemoved.get(i));
		}
		toBeRemoved.clear();
	}
	
	public void storeCombinations(int combi, int numberOfO,int numberOfX){
		if(numberOfO == 3 || numberOfX == 3) threeInARow.add(combi);
		if(numberOfO == 2 && game.getPlayer() == O)twoInARow.add(combi);
		if(numberOfX == 2 && game.getPlayer() == X){twoInARow.add(combi);}
		if(numberOfO == 1 && game.getPlayer() == O)oneInARow.add(combi);
		if(numberOfX == 1 && game.getPlayer() == X){oneInARow.add(combi);}
	}
	
	/*
	 * This Method checks whether a specific stone can be thrown
	 * You have to pass the values of the column and the height the targeted stone is at
	 * The Method returns the number of empty fields BELOW the targeted stone
	 * Example: I want to throw at (5|4)
	 * 			The height of column 5 is 2
	 * 			4-2=2 the Method will return 1, because one stone has to be dropped before we can set the stone we want.
	 */
	public int missingHeightForThrow(int column, int height){
		int missingHeight = 0;
		int[] columnHeight = set.getColumnHeight();
		missingHeight = height - columnHeight[column];
		return missingHeight;
	}
	
	public void checkThreeInARow(){
		if(threeInARow.size() == 0) return;
		blockEnemy = -1;
		for(int i = 0; i < threeInARow.size(); i++){
			for(int x = 0; x < 4; x++){
				if(possibleCombinations[x][threeInARow.get(i)] == null){
					if(x != 0){
						int missingHeight = missingHeightForThrow(positions[2 * x][threeInARow.get(i)], positions[2 * x + 1][threeInARow.get(i)]);
						if(possibleCombinations[0][threeInARow.get(i)].getPlayer() == game.getPlayer()){
							switch(missingHeight){
								case 0: result = positions[2 * x][i];
										return;
								case 1: evaluateColumns[positions[2 * x][threeInARow.get(i)]] = evaluateColumns[positions[2 * x][threeInARow.get(i)]] + ONEMISSING * 2;
										break;
								case 2: evaluateColumns[positions[2 * x][threeInARow.get(i)]] = evaluateColumns[positions[2 * x][threeInARow.get(i)]] + TWOMISSING * 2;
										break;
								case 3: evaluateColumns[positions[2 * x][threeInARow.get(i)]] = evaluateColumns[positions[2 * x][threeInARow.get(i)]] + THREEMISSING * 2;
										break;
								default: break;
								}
							}
						else{
							switch(missingHeight){
								case 0: blockEnemy = positions[2 * x][threeInARow.get(i)];
										break;
								case 1: evaluateColumns[positions[2 * x][threeInARow.get(i)]] = evaluateColumns[positions[2 * x][threeInARow.get(i)]] + DONTTHROW;
										break;
								default: break;
								}
						}
					}else{
						int missingHeight = missingHeightForThrow(positions[2 * x][threeInARow.get(i)], positions[2 * x + 1][threeInARow.get(i)]);
						if(possibleCombinations[1][threeInARow.get(i)].getPlayer() == game.getPlayer()){
							System.out.println("Missing Height: "+missingHeight);
							switch(missingHeight){
								case 0: result = positions[2 * x][threeInARow.get(i)];

										return;
								case 1: evaluateColumns[positions[2 * x][threeInARow.get(i)]] = evaluateColumns[positions[2 * x][threeInARow.get(i)]] + ONEMISSING * 2;
										break;
								case 2: evaluateColumns[positions[2 * x][threeInARow.get(i)]] = evaluateColumns[positions[2 * x][threeInARow.get(i)]] + TWOMISSING * 2;
										break;
								case 3: evaluateColumns[positions[2 * x][threeInARow.get(i)]] = evaluateColumns[positions[2 * x][threeInARow.get(i)]] + THREEMISSING * 2;
										break;
								default: break;
								}
							}
						else{
							switch(missingHeight){
								case 0: blockEnemy = positions[2 * x][threeInARow.get(i)];
										break;
								case 1: evaluateColumns[positions[2 * x][threeInARow.get(i)]] = evaluateColumns[positions[2 * x][threeInARow.get(i)]] +  DONTTHROW;
										break;
								default: break;
								}
						}
					}
				}
			}
		}
		if(blockEnemy != -1){
			result = blockEnemy;
			return;
		}
	}
	
	public void checkTwoInARow(){
		for(int i = 0; i < twoInARow.size(); i++){
			for(int x = 0; x < 4; x++){
				if(possibleCombinations[x][twoInARow.get(i)] == null){
					int missingHeight = missingHeightForThrow(positions[2 * x][twoInARow.get(i)], positions[2 * x + 1][twoInARow.get(i)]);
					switch(missingHeight){
					case 0: evaluateColumns[positions[2 * x][twoInARow.get(i)]] = evaluateColumns[positions[2 * x][twoInARow.get(i)]] + NONEMISSING * 3;
					break;
					case 1: evaluateColumns[positions[2 * x][twoInARow.get(i)]] = evaluateColumns[positions[2 * x][twoInARow.get(i)]] + ONEMISSING * 3;
					break;
					case 2: evaluateColumns[positions[2 * x][twoInARow.get(i)]] = evaluateColumns[positions[2 * x][twoInARow.get(i)]] + TWOMISSING * 3;
					break;
					}
				}
			}
		}
	}
	
	public void checkOneInARow(){
		if(oneInARow.size() == 0) return;
		for(int i = 0; i < oneInARow.size(); i++){
			for(int x = 0; x<= 3; x++){
				if(possibleCombinations[x][oneInARow.get(i)] == null){
					int missingHeight = missingHeightForThrow(positions[2 * x][oneInARow.get(i)], positions[2 * x + 1][oneInARow.get(i)]);
					switch(missingHeight){
					case 0: evaluateColumns[positions[2 * x][oneInARow.get(i)]] = evaluateColumns[positions[2 * x][oneInARow.get(i)]] + NONEMISSING;
					return;
					case 1: evaluateColumns[positions[2 * x][oneInARow.get(i)]] = evaluateColumns[positions[2 * x][oneInARow.get(i)]] + ONEMISSING;
					break;
					case 2: evaluateColumns[positions[2 * x][oneInARow.get(i)]] = evaluateColumns[positions[2 * x][oneInARow.get(i)]] + TWOMISSING;
					break;
					}
				}
			}
		}
	}
	
	public void analyzeResults(){
		String evaluate = "";
		int highest = 0;
		int position = -1;
		for(int i = 0; i < evaluateColumns.length; i++){
			evaluate = evaluate +""+ evaluateColumns[i];
			if(highest < evaluateColumns[i]){
				highest = evaluateColumns[i];
				position = i;
			}
		}
		if(position != -1) result = position;
	}
	
	public void refreshEvaluation(){
		for(int i = 0; i < evaluateColumns.length; i++){
			evaluateColumns[i] = 0;
		}
	}
}