package de.dhbw.die5gewinnt.controller.db;

import java.sql.SQLException;
import java.sql.Statement;

import de.dhbw.die5gewinnt.model.AutoIncrementKeys;
import de.dhbw.die5gewinnt.model.Game;
import de.dhbw.die5gewinnt.model.Move;
import de.dhbw.die5gewinnt.model.Set;

public class DBUpdates extends DBQuery {
	
	private DBUpdates() {
		super();
	}
	
	public static void updateGame(Game game) {
		if(game.getClass().getName().equals("de.dhbw.die5gewinnt.model.Game")) {
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "UPDATE Games SET name = , sets = , score = , winner = , player =  WHERE id = "+game.getId();
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void updateSet(Set set) {
		if(set.getClass().getName().equals("de.dhbw.die5gewinnt.model.Set")) {
		}
	}
	
	public static void updateMove(Move move) {
		if(move.getClass().getName().equals("de.dhbw.die5gewinnt.model.Move")) {
			try {
				Statement stmt = getDBConnection().createStatement();	
				String sql = "UPDATE Moves SET row = "+move.getRow()+", column = "+move.getColumn()+", player = \'"+move.getPlayer()+"\', setId = "+move.getSetId()+" WHERE id = "+move.getId();
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}