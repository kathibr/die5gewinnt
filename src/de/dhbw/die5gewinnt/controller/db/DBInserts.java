package de.dhbw.die5gewinnt.controller.db;

import java.sql.Statement;
import java.sql.SQLException;

import de.dhbw.die5gewinnt.model.*;

public class DBInserts extends DBQuery {
	
	/* Konstruktor */
	private DBInserts() {
		super();
	}
	
	/* INSERT-Query for Model Game */
	/* Methode speichert ein bestimmtes Spiel in der Datenbank ab */
	public static Game insertGame(Game game) {
		if(game.getClass().getName().equals("de.dhbw.die5gewinnt.model.Game")) {
			game.setId(AutoIncrementKeys.getNextGameIdAndIncrement());
			game.setName(DataManipulation.splitName(game.getName()));
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "INSERT INTO Games VALUES("+game.getId()+", \'"+game.getName()+"\', \'"+DataManipulation.getGameScoreForDB(game.getScore())+"\', \'"+DataManipulation.getStringFromBoolean(game.getWinner())+"\', \'"+game.getPlayer()+"\', \'"+game.getOpponentName()+"\')";
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				System.err.println("--- SQL INSERT into Game failed!");
				e.printStackTrace();
			}
		}
		return game;
	}
	
	/* INSERT-Query for Model Set */
	/* Methode speichert einen bestimmten Satz in der Datenbank ab */
	public static Set insertSet(Set set) {
		if(set.getClass().getName().equals("de.dhbw.die5gewinnt.model.Set")) {
			set.setId(AutoIncrementKeys.getNextSetIdAndIncrement());
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "INSERT INTO Sets VALUES("+set.getId()+", \'"+DataManipulation.getSetFieldForDB(set.getField())+"\', \'"+DataManipulation.getSetColumnHeightForDB(set.getColumnHeight())+"\', \'"+DataManipulation.getStringFromBoolean(set.getFirstMove())+"\', \'"+set.getStatus()+"\')";
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				System.err.println("--- SQL INSERT into Set failed!");
				e.printStackTrace();
			}
		}
		return set;
	}
	
	/* INSERT-Query for Model Move */
	/* Methode speichert einen bestimmten Zug in der Datenbank ab */
	public static Move insertMove(Move move, int gameId, int setId) {
		if(move.getClass().getName().equals("de.dhbw.die5gewinnt.model.Move")) {
			move.setId(AutoIncrementKeys.getNextMoveIdAndIncrement());
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "INSERT INTO Moves VALUES("+move.getId()+", "+move.getRow()+", "+move.getColumn()+", \'"+move.getPlayer()+"\')";
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				System.err.println("--- SQL INSERT into Move failed!");
				e.printStackTrace();
			}
		}
		insertGameToSetToMove(gameId, setId, move.getId());
		return move;
	}
	
	/* INSERT-Query for GameToSetToMove */
	/* Methode speichert die Beziehung zwischen einem Spiel, einem Satz und einem Zug in der Datenbank ab */
	private static boolean insertGameToSetToMove(int gameId, int setId, int moveId) {
		try {
			Statement stmt = getDBConnection().createStatement();	
			String sql = "INSERT INTO GameToSetToMove VALUES("+gameId+", "+setId+", "+moveId+")";
			stmt.executeQuery(sql);
			stmt.close();
		} catch (SQLException e) {
			System.err.println("--- SQL INSERT into GameToSetToMove failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}