package de.dhbw.die5gewinnt.controller.algorithm;

public class AlgorithmManager {

	private final long MAXIMUM_RESPONSE_TIME = 2000;
	private final long DIRECTORY_REFRESH_TIME = 300;
	
	private static AlgorithmManager algorithmManager;
	private MastermindAlgorithm mastermindAlgorithm;
	private RandomAlgorithm randomAlgorithm;
	private long milliSeconds; 
	
	private AlgorithmManager() {
		mastermindAlgorithm = new MastermindAlgorithm();
		randomAlgorithm = new RandomAlgorithm();
	}
	
	/* GETTER-Methods */
	public static AlgorithmManager getAlgorithmManager() {
		if(algorithmManager == null)
			algorithmManager = new AlgorithmManager();
		return algorithmManager;
	}
	
	public int getNextColumn(long currentMilliSeconds) {
		long limitMilliSeconds = currentMilliSeconds + (long) ((MAXIMUM_RESPONSE_TIME - DIRECTORY_REFRESH_TIME) * 0.75);
		
			System.out.println("currentMilliSeconds: "+currentMilliSeconds);
			System.out.println("+ MAXIMUM_RESPONSE_TIME: "+(currentMilliSeconds + MAXIMUM_RESPONSE_TIME));
			System.out.println("- DIRECTORY_REFRESH_TIME: "+(currentMilliSeconds + (MAXIMUM_RESPONSE_TIME - DIRECTORY_REFRESH_TIME)));
			System.out.println("- 25% Buffer: "+(long) (currentMilliSeconds + ((MAXIMUM_RESPONSE_TIME - DIRECTORY_REFRESH_TIME) * 0.9 )));
			System.out.println("= limitMilliSeconds: "+limitMilliSeconds);
		
		AlgorithmManager.getAlgorithmManager().setMilliSeconds(currentMilliSeconds);
		
		new Thread(mastermindAlgorithm).start();
		new Thread(randomAlgorithm).start();
		while(System.currentTimeMillis() <= limitMilliSeconds) {
			if(mastermindAlgorithm.nextColumn != -1) {
				long newMilliSeconds = System.currentTimeMillis();
				System.out.println("Result: "+newMilliSeconds);
				System.out.println("Delta: "+(limitMilliSeconds-newMilliSeconds));
				return mastermindAlgorithm.nextColumn;
			}
		}
		long newMilliSeconds = System.currentTimeMillis();
		System.out.println("Result: "+newMilliSeconds);
		System.out.println("Delta: "+(limitMilliSeconds-newMilliSeconds));
		return randomAlgorithm.nextColumn;
	}
	
	private long getMilliSeconds() {
		return this.milliSeconds;
	}
	
	/* SETTER-Methods */
	private void setMilliSeconds(long milliSeconds) {
		this.milliSeconds = milliSeconds;
	}
}