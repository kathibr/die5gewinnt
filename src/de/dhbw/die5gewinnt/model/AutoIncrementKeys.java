package de.dhbw.die5gewinnt.model;

public class AutoIncrementKeys {

	private static int gameId, setId, moveId;
	
	private AutoIncrementKeys() {
		gameId = setId = moveId = 0;
	}
	
	/* GETTER-Methods */
	public static int getLastGameId() {
		return gameId;
	}
	
	public static int getNextGameId() {
		return gameId + 1;
	}
	
	public static int getNextGameIdAndIncrement() {
		AutoIncrementKeys.incrementGameId();
		return gameId;
	}
	
	public static int getLastSetId() {
		return setId;
	}
	
	public static int getNextSetId() {
		return setId + 1;
	}
	
	public static int getNextSetIdAndIncrement() {
		AutoIncrementKeys.incrementSetId();
		return setId;
	}
	
	public static int getLastMoveId() {
		return moveId;
	}
	
	public static int getNextMoveId() {
		return moveId + 1;
	}
	
	public static int getNextMoveIdAndIncrement() {
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