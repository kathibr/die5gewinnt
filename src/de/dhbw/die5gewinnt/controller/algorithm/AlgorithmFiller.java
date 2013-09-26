package de.dhbw.die5gewinnt.controller.algorithm;

import java.util.ArrayList;

import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.Set;

public class AlgorithmFiller {
	public Move[][] field = new Move[7][6];
	private Move[][] possibleCombinations = new Move[4][69];
	private int row,collumn,start,threshold;
	private String testcomment;
	private ArrayList<Move[]> combinations = new ArrayList<Move[]>(69);
	
	public AlgorithmFiller(Set set){
		field = set.getField();
	}

	public ArrayList<Move[]> fillList(){
		fillVertical();
		fillHorizontal();
		fillDiagonal();
		fillArrayList(possibleCombinations);
		return combinations;
	}
	
	public void fillVertical(){
		System.out.println("FillVertical");
		threshold = 2;
		start = 5;
		for(int i = 3; i > 0; i--){
			for(int x = 6; x >= 0; x--){
				collumn = 0;
				testcomment = "";
				for(int y = start; y >= threshold; y--){
					possibleCombinations[collumn][row] = field[x][y];
					collumn++;
					testcomment = testcomment +" "+ x+""+y;
				}
				System.out.println(testcomment + " Zeile:"+row);
				row++;
			}
			threshold--;
			start--;
		}
	}
	
	public void fillHorizontal(){
		System.out.println("fillHorizontal");
		threshold = 3;
		start = 6;
		for(int i = 4; i > 0; i--){
			for(int y = 5; y >= 0 ; y--){
				collumn = 0;
				testcomment = "";
				for(int x = start; x >= threshold; x--){
					possibleCombinations[collumn][row] = field[x][y];
					collumn++;
					testcomment = testcomment +" "+ x+""+y;
				}
				System.out.println(testcomment + " Zeile:"+row);
				row++;
			}
			threshold--;
			start--;
		}
	}
	
	public void fillDiagonal(){
		System.out.println("FillDiagonal");
		for(int y = 5; y >= 3; y--){
			for(int x = 6; x >= 3; x--){
				collumn = 0;
				testcomment = "";
				for(int i = 4; i > 0; i--){
					possibleCombinations[collumn][row] = field[x][y];
					collumn++;
					testcomment = testcomment +" "+ x+""+y;
					x--;
					y--;
				}
				x = x + 4;
				y = y + 4;
				System.out.println(testcomment + " Zeile:"+row);
				row++;
			}
		}
		System.out.println("FillDiagonal2");
		for(int y = 5; y >= 3; y--){
			for(int x = 0; x <= 3; x++){
				collumn = 0;
				testcomment = "";
				for(int i = 4; i > 0; i--){
					possibleCombinations[collumn][row] = field[x][y];
					collumn++;
					testcomment = testcomment +" "+ x+""+y;
					x++;
					y--;
				}
				x = x - 4;
				y = y + 4;
				System.out.println(testcomment + " Zeile:"+row);
				row++;
			}
		}
	}
	
	public void fillArrayList(Move[][] array){
		for(int i = 0; i < 69; i++){
			Move[] row = new Move[4];
			for(int y = 0; y < 4; y++){
				row[y]=array[y][i];
			}
			combinations.add(i,row);
		}
	}
}
