package de.dhbw.die5gewinnt.controller.db;

import java.sql.SQLException;
import java.sql.Statement;

import de.dhbw.die5gewinnt.model.*;

public class DBUpdates extends DBQuery {
	
	/* Konstruktor */
	private DBUpdates() {
		super();
	}
	
	/* UPDATE-Query for Model Game */
	/* Methode aktualisiert ein bestimmtes Spiel in der Datenbank */
	public static boolean updateGame(Game game) {
		if(game.getClass().getName().equals("de.dhbw.die5gewinnt.model.Game")) {
			game.setName(DataManipulation.splitName(game.getName()));
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "UPDATE Games SET name = \'"+game.getName()+"\', score = \'"+DataManipulation.getGameScoreForDB(game.getScore())+"\', winner = \'"+DataManipulation.getStringFromBoolean(game.getWinner())+"\', player = \'"+game.getPlayer()+"\', opponent = \'"+game.getOpponentName()+"\' WHERE id = "+game.getId();
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}
	
	/* UPDATE-Query for Model Set */
	/* Methode aktualisiert einen bestimmten Satz in der Datenbank */
	public static boolean updateSet(Set set) {
		if(set.getClass().getName().equals("de.dhbw.die5gewinnt.model.Set")) {
			try {
				Statement stmt = getDBConnection().createStatement();
				String sql = "UPDATE Sets SET field = \'"+DataManipulation.getSetFieldForDB(set.getField())+"\', columnheight = \'"+DataManipulation.getSetColumnHeightForDB(set.getColumnHeight())+"\', firstmove = \'"+DataManipulation.getStringFromBoolean(set.getFirstMove())+"\', status = \'"+set.getStatus()+ "\' WHERE id = "+set.getId();
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		return true;
		}
		return false;
	}
	
	/* UPDATE-Query for Model Move */
	/* Methode aktualisiert einen bestimmten Zug in der Datenbank */
	public static boolean updateMove(Move move) {
		if(move.getClass().getName().equals("de.dhbw.die5gewinnt.model.Move")) {
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "UPDATE Moves SET row = "+move.getRow()+", column = "+move.getColumn()+", player = \'"+move.getPlayer()+"\' WHERE id = "+move.getId();
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		return true;
		}
		return false;
	}
	
}