package de.dhbw.die5gewinnt.controller.algorithm;

import java.util.ArrayList;

import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.Set;

public class AlgorithmFiller {
	private Move[][] field = new Move[7][6];
	private Move[][] possibleCombinations = new Move[4][69];
	private int row,collumn,start,threshold;
	private ArrayList<Integer> combinations = new ArrayList<Integer>(69);
	private int[][] positions = new int[8][69];
	
	public AlgorithmFiller(Set set){
		field = set.getField();
	}
	
	public int[][] getPositions(){
//		readPositions();
		return positions;
	}
	
	public Move[][] getPossibleCombinations(){
		return possibleCombinations;
	}

	public ArrayList<Integer> fillList(){
		fillVertical();
		fillHorizontal();
		fillDiagonal();
		fillCombinations();
		return combinations;
	}
	
	public void fillCombinations(){
		for(int i = 0; i < 69; i++){
			combinations.add(i);
		}
	}
	
	public void fillVertical(){
		threshold = 2;
		start = 5;
		for(int i = 3; i > 0; i--){
			for(int x = 6; x >= 0; x--){
				collumn = 0;
				for(int y = start; y >= threshold; y--){
					possibleCombinations[collumn][row] = field[x][y];
					positions[2 * collumn + 1][row] = y;
					positions[2 * collumn][row] = x;
					collumn++;
				}
				row++;
			}
			threshold--;
			start--;
		}
	}
	
	public void fillHorizontal(){
		threshold = 3;
		start = 6;
		for(int i = 4; i > 0; i--){
			for(int y = 5; y >= 0 ; y--){
				collumn = 0;
				for(int x = start; x >= threshold; x--){
					possibleCombinations[collumn][row] = field[x][y];
					positions[2 * collumn + 1][row] = y;
					positions[2 * collumn][row] = x;
					collumn++;
				}
				row++;
			}
			threshold--;
			start--;
		}
	}
	
	public void fillDiagonal(){
		for(int y = 5; y >= 3; y--){
			for(int x = 6; x >= 3; x--){
				collumn = 0;
				for(int i = 4; i > 0; i--){
					possibleCombinations[collumn][row] = field[x][y];
					positions[2 * collumn + 1][row] = y;
					positions[2 * collumn][row] = x;
					collumn++;
					x--;
					y--;
				}
				x = x + 4;
				y = y + 4;
				row++;
			}
		}
		for(int y = 5; y >= 3; y--){
			for(int x = 0; x <= 3; x++){
				collumn = 0;
				for(int i = 4; i > 0; i--){
					possibleCombinations[collumn][row] = field[x][y];
					positions[2 * collumn + 1][row] = y;
					positions[2 * collumn][row] = x;
					collumn++;
					x++;
					y--;
				}
				x = x - 4;
				y = y + 4;
				row++;
			}
		}
	}
	
	public void readPositions(){
		String text = "";
		for(int i = 0; i < 69; i++){
			text = "Zeile "+i+": ";
			for(int x = 0; x < 8; x++){
				text += " "+positions[x][i];
			}
			System.out.println(text);
		}
	}
}
