package de.dhbw.die5gewinnt.controller.algorithm;

import java.util.ArrayList;
import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.model.Game;
import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.Set;

public class MastermindAlgorithm{

	private Set set;
	private final String X="X";
	private final String O="O";
	private int result;
	private Game game;
	private final int NONEMISSING = 3;
	private final int ONEMISSING = 2;
	private final int TWOMISSING = 1;
	private final int THREEMISSING = 1;
	private final int DONTTHROW = -1000;

	private Move[][] possibleCombinations = new Move[4][69];
	private ArrayList<Integer> combinations = new ArrayList<Integer>(69);
	private ArrayList<Integer> threeInARow = new ArrayList<Integer>();
	private ArrayList<Integer> enemyThreeInARow = new ArrayList<Integer>();
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
	
	/*
	 * This Method is called by the game logic to calculate the next move
	 * It calls the different methods, needed to calculate the next move
	 */
	public int calcNextMove(){
		refreshArrays();
		refreshEvaluation();
		analyzeField();
		result = -1;
		checkMyThreeInARow();
		if(result == -1){
			checkEnemyThreeInARow();
				if(result == -1){
					checkTwoInARow();
					checkOneInARow();
					analyzeResults();
			}
			if(result == -1) result = 3;
		}
		return result;
	}
	
	/*
	 * This Method clears all arrays from the last calculation
	 */
	public void refreshArrays(){
		oneInARow.clear();
		twoInARow.clear();
		threeInARow.clear();
		enemyThreeInARow.clear();
	}
	
	/*
	 * This Method loops through the Arrays to analyse which combinations allready have different stones 
	 * Theese combinations will then be stored in different arrays in the method storeCombinations
	 * All combinations containing one stone of each team will get deleted, because they can't be relevant for a win anymore
	 */
	public void analyzeField(){
		for(int i = 0; i < combinations.size(); i++){
			int numberOfO = 0, numberOfX = 0;
			for(int x = 0; x < 4; x++){
					if(possibleCombinations[x][combinations.get(i)] != null){
						if(possibleCombinations[x][combinations.get(i)].getPlayer() == X){
							if(numberOfO > 0){
								toBeRemoved.add(combinations.get(i));
								break;
							}
							else numberOfX++;
						}
						else if(possibleCombinations[x][combinations.get(i)].getPlayer() == O){
							if(numberOfX > 0){
								toBeRemoved.add(combinations.get(i));
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
	
	/*
	 * This Method deletes all combinations from the indexArray that are in the array toBeRemoved
	 */
	public void removeFromPossibilitys(){
		for(int i = 0; i < toBeRemoved.size();i++){
			combinations.remove(combinations.indexOf(toBeRemoved.get(i)));
		}
		toBeRemoved.clear();
	}
	
	/*
	 * This Method stores the different combinations depending on how many stones are allready set
	 * Combinations with three stones have to be differentiated in combinations with enemy stones or own stones
	 */
	public void storeCombinations(int combi, int numberOfO,int numberOfX){
		if(numberOfO == 3 && game.getPlayer() == O)threeInARow.add(combi);
		if(numberOfO == 3 && game.getPlayer() != O)enemyThreeInARow.add(combi);
		if(numberOfX == 3 && game.getPlayer() == X)threeInARow.add(combi);
		if(numberOfX == 3 && game.getPlayer() != X)enemyThreeInARow.add(combi);
		if(numberOfO == 2 && game.getPlayer() == O)twoInARow.add(combi);
		if(numberOfX == 2 && game.getPlayer() == X)twoInARow.add(combi);
		if(numberOfO == 1 && game.getPlayer() == O)oneInARow.add(combi);
		if(numberOfX == 1 && game.getPlayer() == X)oneInARow.add(combi);
	}
	
	/*
	 * This Method checks whether a specific stone can be thrown and returnes the height which is needed till the stone can be thrown
	 */
	public int missingHeightForThrow(int column, int height){
		int missingHeight = 0;
		int[] columnHeight = set.getColumnHeight();
		missingHeight = height - columnHeight[column];
		return missingHeight;
	}
	
	/*
	 * This Method analyses the combinations containing three of our own stones
	 * Depending on the height thats needed for the stone different values will be added to the array that
	 */
	public void checkMyThreeInARow(){
		if(threeInARow.size() == 0) return;
		for(int i = 0; i < threeInARow.size(); i++){
			for(int x = 0; x < 4; x++){
				if(possibleCombinations[x][threeInARow.get(i)] == null){
					int missingHeight = missingHeightForThrow(positions[2 * x][threeInARow.get(i)], positions[2 * x + 1][threeInARow.get(i)]);
						switch(missingHeight){
							case 0: result = positions[2 * x][threeInARow.get(i)];
									return;
							case 1: evaluateColumns[positions[2 * x][threeInARow.get(i)]] = evaluateColumns[positions[2 * x][threeInARow.get(i)]] + (DONTTHROW/10);
									break;
							case 2: evaluateColumns[positions[2 * x][threeInARow.get(i)]] = evaluateColumns[positions[2 * x][threeInARow.get(i)]] + TWOMISSING * 2;
									break;
							case 3: evaluateColumns[positions[2 * x][threeInARow.get(i)]] = evaluateColumns[positions[2 * x][threeInARow.get(i)]] + THREEMISSING * 2;
									break;
							default: break;
					}
				}
			}
		}
	}
	
	/*
	 * This Method analyses the combinations containing three of enemy stones
	 * Depending on the height thats needed for the stone the enemy will be blocked or the row is blocked for throws
	 */
	public void checkEnemyThreeInARow(){
		if(enemyThreeInARow.size() == 0) return;
		for(int i = 0; i < enemyThreeInARow.size(); i++){
			for(int x = 0; x < 4; x++){
				if(possibleCombinations[x][enemyThreeInARow.get(i)] == null){
					int missingHeight = missingHeightForThrow(positions[2 * x][enemyThreeInARow.get(i)], positions[2 * x + 1][enemyThreeInARow.get(i)]);
						switch(missingHeight){
							case 0: result = positions[2 * x][enemyThreeInARow.get(i)];
									return;
							case 1: evaluateColumns[positions[2 * x][enemyThreeInARow.get(i)]] = evaluateColumns[positions[2 * x][enemyThreeInARow.get(i)]] + DONTTHROW;
									break;
					}
				}
			}
		}
	}
	
	/*
	 * This Method analyses the combinations containing two of our own stones
	 * Depending on the height thats needed for the stone different values will be added to the array that
	 */
	public void checkTwoInARow(){
		for(int i = 0; i < twoInARow.size(); i++){
			for(int x = 0; x < 4; x++){
				if(possibleCombinations[x][twoInARow.get(i)] == null){
					int missingHeight = missingHeightForThrow(positions[2 * x][twoInARow.get(i)], positions[2 * x + 1][twoInARow.get(i)]);
					switch(missingHeight){
					case 0:	evaluateColumns[positions[2 * x][twoInARow.get(i)]] = evaluateColumns[positions[2 * x][twoInARow.get(i)]] + NONEMISSING * 4;
					break;
					case 1: evaluateColumns[positions[2 * x][twoInARow.get(i)]] = evaluateColumns[positions[2 * x][twoInARow.get(i)]] + ONEMISSING * 4;
					break;
					case 2: evaluateColumns[positions[2 * x][twoInARow.get(i)]] = evaluateColumns[positions[2 * x][twoInARow.get(i)]] + TWOMISSING * 4;
					break;
					}
				}
			}
		}
	}
	
	/*
	 * This Method analyses the combinations containing one of our own stone
	 * Depending on the height thats needed for the stone different values will be added to the array that
	 */
	public void checkOneInARow(){
		if(oneInARow.size() == 0) return;
		for(int i = 0; i < oneInARow.size(); i++){
			for(int x = 0; x < 4; x++){
				if(possibleCombinations[x][oneInARow.get(i)] == null){
					int missingHeight = missingHeightForThrow(positions[2 * x][oneInARow.get(i)], positions[2 * x + 1][oneInARow.get(i)]);
					switch(missingHeight){
					case 0: evaluateColumns[positions[2 * x][oneInARow.get(i)]] = evaluateColumns[positions[2 * x][oneInARow.get(i)]] + NONEMISSING;
					break;
					case 1: evaluateColumns[positions[2 * x][oneInARow.get(i)]] = evaluateColumns[positions[2 * x][oneInARow.get(i)]] + ONEMISSING;
					break;
					case 2: evaluateColumns[positions[2 * x][oneInARow.get(i)]] = evaluateColumns[positions[2 * x][oneInARow.get(i)]] + TWOMISSING;
					break;
					}
				}
			}
		}
	}
	
	/*
	 * The Array is checked for the highest value, which is the result of the algorithm
	 */
	public void analyzeResults(){
		int highest = 0;
		int position = -1;
		for(int i = 0; i < evaluateColumns.length; i++){
			if(highest < evaluateColumns[i]){
				highest = evaluateColumns[i];
				position = i;
			}
		}
		if(position != -1) result = position;
	}
	
	/*
	 * The Evaluatione Array is reseted
	 */
	public void refreshEvaluation(){
		for(int i = 0; i < evaluateColumns.length; i++){
			evaluateColumns[i] = 0;
		}
	}
}