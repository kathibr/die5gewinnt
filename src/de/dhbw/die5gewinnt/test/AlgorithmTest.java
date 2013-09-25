package de.dhbw.die5gewinnt.test;

import de.dhbw.die5gewinnt.controller.algorithm.MastermindAlgorithm;
import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.Set;

public class AlgorithmTest {

	public static void main(String[] args) {
		Move[][] field = new Move[7][6];
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++)
				field[i][j] = new Move();
		}
		Set set = new Set();
		set.setField(field);
		MastermindAlgorithm algorithm = new MastermindAlgorithm(set);

	}

}
