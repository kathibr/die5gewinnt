package de.dhbw.die5gewinnt.controller.db;

import java.sql.SQLException;
import java.sql.Statement;

import de.dhbw.die5gewinnt.model.Move;

public class DBDeletes extends DBQuery {
	
	/* Konstruktor */
	private DBDeletes() {
		super();
	}
	
	/* DELETE-Query for Model Set */
	/* Methode löscht einen bestimmten Satz aus der Datenbank */
	public static boolean deleteSet(int setId) {
		DBDeletes.deleteMovesFromSet(setId);
		DBDeletes.deleteGameToSetToMoveFromSet(setId);
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "DELETE FROM Sets WHERE id = "+setId;
			stmt.executeQuery(sql);
			stmt.close();
			return true;
		} catch (SQLException e) {
			System.err.println("--- SQL DELETE from Set failed!");
			e.printStackTrace();
			return false;
		}
	}
	
	/* DELETE-Query for Model Move */
	/* Methode löscht einen bestimmtenZug aus der Datenbank */
	public static boolean deleteMove(int moveId) {
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "DELETE FROM Moves WHERE id = "+moveId;
			stmt.executeQuery(sql);
			stmt.close();
			return true;
		} catch (SQLException e) {
			System.err.println("--- SQL DELETE from Move failed!");
			e.printStackTrace();
			return false;
		}		
	}
	
	/* Methode löscht alle Züge eines bestimmten Satzes aus der Datenbank */
	public static boolean deleteMovesFromSet(int setId) {
		Move[] moves = DBSelects.selectMoves(setId);
		for(int i = 0; i < moves.length; i++) {
			if(!DBDeletes.deleteMove(moves[i].getId()))
				return false;
		}
		return true;
	}
	
	/* DELETE-Query for Model GameToSetToMove */
	/* Methode löscht alle Beziehungseinträge eines bestimmten Satzes aus der Datenbank */
	public static boolean deleteGameToSetToMoveFromSet(int setId) {
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "DELETE FROM GameToSetToMove WHERE setId = "+setId;
			stmt.executeQuery(sql);
			stmt.close();
			return true;
		} catch (SQLException e) {
			System.err.println("--- SQL DELETE from GameToSetToMove failed!");
			e.printStackTrace();
			return false;
		}
	}

}