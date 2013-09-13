package de.dhbw.die5gewinnt.controller.db;

import java.sql.Statement;
import java.sql.SQLException;

import de.dhbw.die5gewinnt.model.*;

public class DBInserts extends DBQuery {
	
	private DBInserts() {
		super();
	}
	
	/* INSERT-Query for Game */
	public static Game insertGame(Game game) {
		if(game.getClass().getName().equals("de.dhbw.die5gewinnt.model.Game")) {
			game.setId(AutoIncrementKeys.getGameIdAndIncrement());
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "INSERT INTO Games VALUES("+game.getId()+", \'"+game.getName()+"\', \'"+DataManipulation.getGameScoreForDB(game.getScore())+"\', \'"+DataManipulation.getGameWinnerForDB(game.getWinner())+"\', \'"+game.getPlayer()+"\')";
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return game;
		}
		return null;
	}
	
	/* INSERT-Query for Set */
	public static Set insertSet(Set set) {
		if(set.getClass().getName().equals("de.dhbw.die5gewinnt.model.Set")) {
			set.setId(AutoIncrementKeys.getSetIdAndIncrement());
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "INSERT INTO Sets VALUES("+set.getId()+", \'"+DataManipulation.getSetFieldForDB(set.getField())+"\', \'"+DataManipulation.getSetColumnHeightForDB(set.getColumnHeight())+"\')";
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return set;
		}
		return null;
	}
	
	/* INSERT-Query for Move */
	public static Move insertMove(Move move) {
		if(move.getClass().getName().equals("de.dhbw.die5gewinnt.model.Move")) {
			move.setId(AutoIncrementKeys.getMoveIdAndIncrement());
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "INSERT INTO Moves VALUES("+move.getId()+", "+move.getRow()+", "+move.getColumn()+", \'"+move.getPlayer()+"\', "+move.getSetId()+")";
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return move;
		}
		return null;
	}

}