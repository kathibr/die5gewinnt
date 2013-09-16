package de.dhbw.die5gewinnt.controller.db;

public class DataManipulation {

	private DataManipulation() {}
	
	/* Data Manipulation for Model Game */
	public static String getGameScoreForDB(int[] score) {
		return score[0]+","+score[1];
	}
	
	public static int[] getGameScoreForJava(String score) {
		int[] returnScore = new int[3];
		String[] stringScore = score.split(",");
		returnScore[0] = Integer.parseInt(stringScore[0]);
		returnScore[1] = Integer.parseInt(stringScore[1]);
		return returnScore;
	}
	
	public static String getGameWinnerForDB(boolean winner) {
		if(winner)
			return "true";
		else
			return "false";
	}
	
	public static boolean getGameWinnerForJava(String winner) {
		if(winner.equals("true"))
			return true;
		else
			return false;
	}
	
	/* Data Manipulation for Model Set */
	public static String getSetFieldForDB(int[][] field) {
		String returnField = "";
		for(int i = 0; i < 7; i++)
			for(int j = 0; j < 6; j++)
				returnField += field[i][j]+",";
		return returnField.substring(0, returnField.length() - 1);
	}
	
	public static int[][] getSetFieldForJava(String field) {
		int[][] returnField = new int[7][6];
		String[] stringField = field.split(",");
		int i = 0;
			for(int j = 0; j < 7; j++)
				for(int k = 0; k < 6; k++)
				returnField[j][k] = Integer.parseInt(stringField[i++]);
		return returnField;
	}
	
	public static String getSetColumnHeightForDB(int[] columnHeight) {
		String returnColumnHeight = "";
		for(int i = 0; i < columnHeight.length; i++)
			returnColumnHeight += columnHeight[i]+",";
		return returnColumnHeight.substring(0, returnColumnHeight.length() - 1);
	}
	
	public static int[] getSetColumnHeightForJava(String columnHeight) {
		int[] returnColumnHeight = new int[7];
		String[] stringColumnHeight = columnHeight.split(",");
		int i = 0;
			for(int j = 0; j < 7; j++)
				returnColumnHeight[j] = Integer.parseInt(stringColumnHeight[i++]);
		return returnColumnHeight;
	}
	
}