package de.dhbw.die5gewinnt.controller.algorithm;

import de.dhbw.die5gewinnt.model.Move;

public class MastermindAlgorithm implements Runnable {

	public int nextColumn = -1;
	private Move[][] possibleCombinations;
	public Move[][] field = new Move[7][6];
	public int[] columnHeight = new int[7];
	private int row,collumn,start,threshold;
	private String testcomment;
	
	public MastermindAlgorithm(de.dhbw.die5gewinnt.model.Set set){
		field = set.getField();
		possibleCombinations = new Move[5][69];
		fillField();
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
	
	public void fillField(){
		fillVertical();
		fillHorizontal();
		fillDiagonal();
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