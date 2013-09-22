package de.dhbw.die5gewinnt.controller.algorithm;

public class RandomAlgorithm implements Algorithm, Runnable {

	public int nextColumn = -1;
	
	/* ALGORITHM-Interface */
	@Override
	public int calcNextColumn() {
		try {
			
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int random = (int) (Math.random() * 6);
		System.out.println("RandomAlgorithm: "+random);
		nextColumn = random;
		return nextColumn;
	}
	
	/* RUNNABLE-Interface */
	@Override
	public void run() {
		calcNextColumn();
	}

}