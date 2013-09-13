package de.dhbw.die5gewinnt.model;

public class AutoIncrementKeys {

	private static int gameId, setId, moveId;
	
	private AutoIncrementKeys() {
		gameId = setId = moveId = 0;
	}
	
	/* GETTER-Methods */
	public static int getGameId() {
		return gameId;
	}
	
	public static int getGameIdAndIncrement() {
		AutoIncrementKeys.incrementGameId();
		return gameId;
	}
	
	public static int getSetId() {
		return setId;
	}
	
	public static int getSetIdAndIncrement() {
		AutoIncrementKeys.incrementSetId();
		return setId;
	}
	
	public static int getMoveId() {
		return moveId;
	}
	
	public static int getMoveIdAndIncrement() {
		AutoIncrementKeys.incrementMoveId();
		return moveId;
	}
	
	/* SETTER-Methods */
	public static void setGameId(int gameId) {
		AutoIncrementKeys.gameId = gameId;
	}
	
	private static void incrementGameId() {
		AutoIncrementKeys.gameId++;
	}
	
	public static void setSetId(int setId) {
		AutoIncrementKeys.setId = setId;
	}
	
	private static void incrementSetId() {
		AutoIncrementKeys.setId++;
	}
	
	public static void setMoveId(int moveId) {
		AutoIncrementKeys.moveId = moveId;
	}
	
	private static void incrementMoveId() {
		AutoIncrementKeys.moveId++;
	}
	
}