package de.dhbw.die5gewinnt.controller.algorithm;
import java.util.Random;

public class Algorithm2 {

	private de.dhbw.die5gewinnt.model.Move[][] possibleCombinations;
	private de.dhbw.die5gewinnt.model.Move[][] field;
	private int result;
	private int zeile,spalte;
	
	public Algorithm2(de.dhbw.die5gewinnt.model.Set set) {
		field = set.getField();
		possibleCombinations = new de.dhbw.die5gewinnt.model.Move[57][4];
	}
	
	public int random(){
		Random randomGenerator = new Random();
		result = randomGenerator.nextInt(6);
		return result;
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
				}
				grenze--;
				start--;
				zeile++;
			}
		}
	}
	
	public void fillVertical(){
		for(int i = 4; i >= 0; i--){
			for(int y = 5; y >= 0; y--){
				spalte = 0;
				for(int x = 6; x >= 0; x--){
					
				}
			}
		}
	}
}