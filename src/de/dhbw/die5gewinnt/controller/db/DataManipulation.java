package de.dhbw.die5gewinnt.controller.db;

import de.dhbw.die5gewinnt.model.AutoIncrementKeys;
import de.dhbw.die5gewinnt.model.Move;

public class DataManipulation {

	/* Konstruktor */
	private DataManipulation() {}
	
	/* Generic Data Manipulation */
	/* Methode erzeugt aus einem Boolean einen String */
	public static String getStringFromBoolean(boolean input) {
		if(input)
			return "true";
		else
			return "false";	
	}
	
	/* Methode erzeugt aus einem String einen Boolean */
	public static boolean getBooleanFromString(String input) {
		if(input.equals("true"))
			return true;
		else
			return false;		
	}
	
	/* Data Manipulation for Model Game */
	/* Methode kürzt einen String auf einen String mit max. 13 Zeichen */
	public static String splitName(String name) {
		if(name.length() > 13)
			return name.substring(0, 13);
		else
			return name;
	}
	
	/* Methode erzeugt einen String aus zwei Integer-Werten aus einem definierten Array */
	public static String getGameScoreForDB(int[] score) {
		return score[0]+","+score[1];
	}
	
	/* Methode erzeugt einen Integer-Array aus einem String */
	public static int[] getGameScoreForJava(String score) {
		int[] returnScore = new int[3];
		String[] stringScore = score.split(",");
		returnScore[0] = Integer.parseInt(stringScore[0]);
		returnScore[1] = Integer.parseInt(stringScore[1]);
		return returnScore;
	}
	
	/* Data Manipulation for Model Set */
	/* Methode erzeugt einen String aus Move-Werten aus einem definierten Array */
	public static String getSetFieldForDB(Move[][] field) {
		String returnField = "";
		for(int i = 0; i < 7; i++)
			for(int j = 0; j < 6; j++) {
				if(field[i][j] == null)
					returnField += "0,";
				else
					returnField += field[i][j].getId()+",";
			}
		return returnField.substring(0, returnField.length() - 1);
	}
	
	/* Methode erzeugt einen Move-Array aus einem String */
	public static Move[][] getSetFieldForJava(int setId, String field) {
		Move[][] returnField = new Move[7][6];
		Move[] moves = DBSelects.selectMoves(setId);
		Move[] listOfMoves = new Move[AutoIncrementKeys.getLastMoveId() + 1];
		for(int i = 0; i < moves.length; i++)
			listOfMoves[moves[i].getId()] = moves[i];
		String[] stringField = field.split(",");
		int i = 0;
			for(int j = 0; j < 7; j++)
				for(int k = 0; k < 6; k++)
				returnField[j][k] = listOfMoves[Integer.parseInt(stringField[i++])];
		return returnField;
	}
	
	/* Methode erzeugt einen String aus Integer-Werten aus einem definierten Array */
	public static String getSetColumnHeightForDB(int[] columnHeight) {
		String returnColumnHeight = "";
		for(int i = 0; i < columnHeight.length; i++)
			returnColumnHeight += columnHeight[i]+",";
		return returnColumnHeight.substring(0, returnColumnHeight.length() - 1);
	}
	
	/* Methode erzeugt einen Integer-Array aus einem String */
	public static int[] getSetColumnHeightForJava(String columnHeight) {
		int[] returnColumnHeight = new int[7];
		String[] stringColumnHeight = columnHeight.split(",");
		int i = 0;
			for(int j = 0; j < 7; j++)
				returnColumnHeight[j] = Integer.parseInt(stringColumnHeight[i++]);
		return returnColumnHeight;
	}
	
}