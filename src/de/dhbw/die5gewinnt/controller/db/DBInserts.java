package de.dhbw.die5gewinnt.controller.db;

import java.sql.Statement;
import java.sql.SQLException;

import de.dhbw.die5gewinnt.model.*;

public class DBInserts extends DBQuery {
	
	private DBInserts() {
		super();
	}
	
	/* INSERT-Query for Model Game */
	public static Game insertGame(Game game) {
		if(game.getClass().getName().equals("de.dhbw.die5gewinnt.model.Game")) {
			game.setId(AutoIncrementKeys.getNextGameIdAndIncrement());
			game.setName(DataManipulation.splitName(game.getName()));
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "INSERT INTO Games VALUES("+game.getId()+", \'"+game.getName()+"\', \'"+DataManipulation.getGameScoreForDB(game.getScore())+"\', \'"+DataManipulation.getGameWinnerForDB(game.getWinner())+"\', \'"+game.getPlayer()+"\', \'"+game.getOpponentName()+"\')";
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
	public static Set insertSet(Set set) {
		if(set.getClass().getName().equals("de.dhbw.die5gewinnt.model.Set")) {
			set.setId(AutoIncrementKeys.getNextSetIdAndIncrement());
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "INSERT INTO Sets VALUES("+set.getId()+", \'"+DataManipulation.getSetFieldForDB(set.getField())+"\', \'"+DataManipulation.getSetColumnHeightForDB(set.getColumnHeight())+"\', \'"+DataManipulation.getSetWinnerForDB(set.getWinner())+"\')";
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
	public static Move insertMove(Move move, int gameId, int setId) {
		if(move.getClass().getName().equals("de.dhbw.die5gewinnt.model.Move")) {
			move.setId(AutoIncrementKeys.getNextMoveIdAndIncrement());
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "INSERT INTO Moves VALUES("+move.getId()+", "+move.getRow()+", "+move.getColumn()+", \'"+move.getPlayer()+"\')";
				stmt.executeQuery(sql);
				sql = "INSERT INTO GameToSetToMove VALUES("+gameId+", "+setId+", "+move.getId()+")";
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				System.err.println("--- SQL INSERT into Move, GameToSetToMove failed!");
				e.printStackTrace();
			}
		}
		return move;
	}

}