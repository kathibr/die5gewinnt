package de.dhbw.die5gewinnt.controller.db;

import java.sql.SQLException;
import java.sql.Statement;

import de.dhbw.die5gewinnt.model.*;

public class DBUpdates extends DBQuery {
	
	private DBUpdates() {
		super();
	}
	
	/* UPDATE-Query for Model Game */
	public static void updateGame(Game game) {
		if(game.getClass().getName().equals("de.dhbw.die5gewinnt.model.Game")) {
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "UPDATE Games SET name = \'"+game.getName()+"\', score = \'"+DataManipulation.getGameScoreForDB(game.getScore())+"\', winner = \'"+DataManipulation.getGameWinnerForDB(game.getWinner())+"\', player = \'"+game.getPlayer()+"\', opponent = \'"+game.getOpponentName()+"\' WHERE id = "+game.getId();
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* UPDATE-Query for Model Set */
	public static void updateSet(Set set) {
		if(set.getClass().getName().equals("de.dhbw.die5gewinnt.model.Set")) {
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "UPDATE Sets SET field = \'"+DataManipulation.getSetFieldForDB(set.getField())+"\', columnheight = \'"+DataManipulation.getSetColumnHeightForDB(set.getColumnHeight())+"\' WHERE = "+set.getId();
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* UPDATE-Query for Model Move */
	public static void updateMove(Move move) {
		if(move.getClass().getName().equals("de.dhbw.die5gewinnt.model.Move")) {
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "UPDATE Moves SET row = "+move.getRow()+", column = "+move.getColumn()+", player = \'"+move.getPlayer()+"\' WHERE id = "+move.getId();
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}