package de.dhbw.die5gewinnt.controller.algorithm;

import java.util.ArrayList;
import de.dhbw.die5gewinnt.controller.Controller;
import de.dhbw.die5gewinnt.model.Game;
import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.Set;

public class MastermindAlgorithm implements Runnable {

	public int nextColumn = -1;
//	private Move[][] field = new Move[7][6];
	private Set set;
//	private int[] columnHeight = new int[7];
	private final String X="X";
	private final String O="O";
	private int result, blockEnemy;
	private Game game;
	private final int NONEMISSING = 4;
	private final int ONEMISSING = -4;
	private final int TWOMISSING = 1;
	private final int THREEMISSING = 2;
	private final int DONTTHROW = -1000;

	private Move[][] possibleCombinations = new Move[4][69];
	private ArrayList<Integer> combinations = new ArrayList<Integer>(69);
	private ArrayList<Integer> threeInARow = new ArrayList<Integer>();
	private ArrayList<Integer> twoInARow = new ArrayList<Integer>();
	private ArrayList<Integer> oneInARow = new ArrayList<Integer>();
	private int[][] positions = new int[8][69];
	private int[] evaluateColumns = new int[6];
	
	public MastermindAlgorithm(Set set){
		game = Controller.getController().getModelController().getGame();			//ZU TESTZWECKEN AUSKOMMENTIERT!
//		game = new Game();															// Test
//		game.setPlayer(X);															// Test
		this.set = set;
		AlgorithmFiller filler = new AlgorithmFiller(set);
		combinations = filler.fillList();
		possibleCombinations = filler.getPossibleCombinations();
		positions = filler.getPositions();
		analyzeField();
	}
	
	public int calcNextMove(){
		result = -1;
		checkThreeInARow();
		checkTwoInARow();
		analyzeResults();
		if(result == -1){
			checkOneInARow();
			analyzeResults();
		}
		return result;
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
		result = -1;
		if(result == -1) result = (int) (Math.random() * 6);
		return result;
	}
	
	public void analyzeField(){
		for(int i = 0; i < combinations.size(); i++){
			int numberOfO = 0, numberOfX = 0;
			for(int x = 0; x < 4; x++){
					if(possibleCombinations[x][combinations.get(i)] != null){
						if(possibleCombinations[x][combinations.get(i)].getPlayer() == X){
							if(numberOfO > 0){
								combinations.remove(i);
								break;
							}
							else numberOfX++;
						}
						else if(possibleCombinations[x][combinations.get(i)].getPlayer() == O){
							if(numberOfX > 0){
								combinations.remove(i);
								break;
							}
							else numberOfO++;
						}
					}
			}
			storeCombinations(combinations.get(i), numberOfO, numberOfX);
		}
	}
	
	public void storeCombinations(int combi, int numberOfO,int numberOfX){
		if(numberOfO == 3 || numberOfX == 3){ threeInARow.add(combi);
		System.out.println("Three in a row!");}
		if(numberOfO == 2 && game.getPlayer() == O){ twoInARow.add(combi);
		System.out.println("Two in a row!");}
		if(numberOfX == 2 && game.getPlayer() == X){ twoInARow.add(combi);
		System.out.println("Two in a row!");}
		if(numberOfO == 1 || numberOfX == 1){ oneInARow.add(combi);
		System.out.println("One in a row!");}
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
		missingHeight = height - columnHeight[column] - 1;
		return missingHeight;
	}
	
	public void checkThreeInARow(){
		System.out.println("Check three in a row");
		if(threeInARow.size() == 0){ System.out.println("ThreeInARow is empty"); return;}
		blockEnemy = -1;
		for(int i = 0; i <= threeInARow.size(); i++){
			for(int x = 0; x<= 4; x++){
				if(possibleCombinations[threeInARow.get(i)][x] == null){
					if(x != 0){
						int missingHeight = missingHeightForThrow(positions[i][2 * x], positions[i][2 * x + 1]);
						if(possibleCombinations[i][0].getPlayer() == game.getPlayer()){
							switch(missingHeight){
							case 0: result = positions[i][2 * x];
									System.out.println("win");
									return;
							case 1: evaluateColumns[positions[i][2 * x]] += ONEMISSING * 2;
									break;
							case 2: evaluateColumns[positions[i][2 * x]] += TWOMISSING * 2;
									break;
							case 3: evaluateColumns[positions[i][2 * x]] += THREEMISSING * 2;
									break;
							default: break;
							}
							}
						else{
							switch(missingHeight){
							case 0: blockEnemy = positions[i][2 * x];
									break;
							case 1: evaluateColumns[positions[i][2 * x]] += DONTTHROW;
									break;
							default: break;
							}
						}
					}
				}
			}
		}
		if(blockEnemy != -1){
			System.out.println("Block enemy");
			result = blockEnemy;
			return;
		}
	}
	
	public void checkTwoInARow(){
		System.out.println("Check two in a row"+twoInARow.size());
		for(int i = 0; i < twoInARow.size(); i++){
			for(int x = 0; x<= 3; x++){
				if(possibleCombinations[x][twoInARow.get(i)] == null){
					int missingHeight = missingHeightForThrow(positions[i][2 * x], positions[i][2 * x + 1]);
					System.out.println("Two in a row possible combinations "+missingHeight);
					switch(missingHeight){
					case 0: evaluateColumns[positions[i][2 * x]] += NONEMISSING;
					break;
					case 1: evaluateColumns[positions[i][2 * x]] += ONEMISSING;
					break;
					case 2: evaluateColumns[positions[i][2 * x]] += TWOMISSING;
					break;
					}
				}
			}
		}
	}
	
	public void checkOneInARow(){
		System.out.println("Check on in a row");
		for(int i = 0; i <= oneInARow.size(); i++){
			for(int x = 0; x<= 3; x++){
				if(possibleCombinations[x][oneInARow.get(i)] == null){
					int missingHeight = missingHeightForThrow(positions[i][2 * x], positions[i][2 * x + 1]);
					switch(missingHeight){
					case 0: evaluateColumns[positions[i][2 * x]] += NONEMISSING;
					return;
					case 1: evaluateColumns[positions[i][2 * x]] += ONEMISSING;
					break;
					case 2: evaluateColumns[positions[i][2 * x]] += TWOMISSING;
					break;
					}
				}
			}
		}
	}
	
	public void analyzeResults(){
		System.out.println("Analyze Results");
		String evaluate = "";
		int highest = 0;
		for(int i = 0; i < evaluateColumns.length; i++){
			evaluate = evaluate + evaluateColumns[i];
			if(highest < evaluateColumns[i]) highest = evaluateColumns[i];
		}
		System.out.println(evaluate);
		System.out.println("highest: "+highest);
		if(highest > 0) result = highest;
	}
	
	/* RUNNABLE-Interface */
	@Override
	public void run() {
		calcNextColumn();
	}

}