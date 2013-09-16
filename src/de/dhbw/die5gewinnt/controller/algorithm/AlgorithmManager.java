package de.dhbw.die5gewinnt.controller.algorithm;

public class AlgorithmManager {

	private static AlgorithmManager algorithmManager;
	
	private AlgorithmManager() {}
	
	/* GETTER-Methods */
	public static AlgorithmManager getAlgorithmManager() {
		if(algorithmManager == null)
			algorithmManager = new AlgorithmManager();
		return algorithmManager;
	}
	
	/* SETTER-Methods */
	
	
}