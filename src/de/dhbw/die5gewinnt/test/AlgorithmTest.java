package de.dhbw.die5gewinnt.test;

import de.dhbw.die5gewinnt.controller.algorithm.MastermindAlgorithm;
import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.Set;

public class AlgorithmTest {

	public static void main(String[] args) {
		Move[][] field = new Move[7][6];
		field[1][0] = new Move(0, 1, "X");
		field[2][0] = new Move(0, 2, "X");
		field[3][0] = new Move(0, 3, "O");
		field[4][0] = new Move(0, 4, "X");
		field[5][0] = new Move(0, 5, "O");
		field[6][0] = new Move(0, 6, "X");
		field[2][1] = new Move(1, 2, "O");
		field[3][1] = new Move(1, 3, "X");
		field[4][1] = new Move(1, 4, "X");
		field[2][2] = new Move(2, 2, "X");
		field[3][2] = new Move(2, 3, "O");
		field[4][2] = new Move(2, 4, "O");
		field[3][3] = new Move(3, 3, "O");
		field[4][3] = new Move(3, 4, "O");
		
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
	}

}
