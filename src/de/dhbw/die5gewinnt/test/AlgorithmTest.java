package de.dhbw.die5gewinnt.test;


import de.dhbw.die5gewinnt.controller.algorithm.MastermindAlgorithm;
import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.Set;

public class AlgorithmTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Move[][] field = new Move[7][6];
		
		Move[][] field2 = new Move[7][6];
		field2[1][0] = new Move(0, 1, "X");
		field2[2][0] = new Move(0, 2, "X");

//		for(int i = 0; i < 7; i++) {
//			for(int j = 0; j < 6; j++)
//				field[i][j] = new Move();
//		}
		Set set = new Set();
		set.setField(field2);
		MastermindAlgorithm algorithm = new MastermindAlgorithm(set);
		int result = algorithm.calcNextMove();
		System.out.println(result);
	}

}
