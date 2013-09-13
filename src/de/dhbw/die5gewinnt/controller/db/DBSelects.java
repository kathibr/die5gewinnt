package de.dhbw.die5gewinnt.controller.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.dhbw.die5gewinnt.model.*;

public class DBSelects extends DBQuery {

	private DBSelects() {
		super();
	}
	
	public static void initAutoIncrementKeys() {
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "SELECT TOP 1 * FROM GameToSetToMove ORDER BY gameId DESC";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				AutoIncrementKeys.setGameId(Integer.parseInt(rs.getString(1)));
				AutoIncrementKeys.setSetId(Integer.parseInt(rs.getString(2)));
				AutoIncrementKeys.setMoveId(Integer.parseInt(rs.getString(3)));
			}
			rs.close();	
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* SELECT-Queries for Game */
	public static Game selectGame(int id) {
		Game game = new Game();
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "SELECT * FROM Games WHERE id = "+id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				game.setId(Integer.parseInt(rs.getString(1)));
	    		game.setName(rs.getString(2));
	    		game.setSets(selectSets(id));
	    		game.setScore(DataManipulation.getGameScoreForJava(rs.getString(3)));
	    		game.setWinner(DataManipulation.getGameWinnerForJava(rs.getString(4)));
	    		game.setPlayer(rs.getString(5));
	    	}
	    	rs.close();
	    	stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return game;		
	}
	
//	public static Game[] selectGames() {
//		ArrayList<Game> games = new ArrayList<Game>();
//		# ToDo #
//		return games.toArray(new Game[games.size()]);
//	}
	
	/* SELECT-Queries for Set */
	public static Set selectSet(int id) {
		Set set = new Set();
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "SELECT * FROM Sets WHERE id = "+id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				set.setId(Integer.parseInt(rs.getString(1)));
				set.setMoves(selectMoves(id));
				set.setField(DataManipulation.getSetFieldForJava(rs.getString(2)));
				set.setColumnHeight(DataManipulation.getSetColumnHeightForJava(rs.getString(3)));
	    	}
	    	rs.close();
	    	stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return set;		
	}
	
	public static Set[] selectSets(int gameId) {
		ArrayList<Set> sets = new ArrayList<Set>();
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "SELECT * FROM Sets INNER JOIN GameToSetToMove ON Sets.id = GameToSetToMove.setId WHERE GameToSetToMove.gameId = "+gameId;
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) {
				int setId = Integer.parseInt(rs.getString(1));
				sets.add(new Set(setId, selectMoves(setId), DataManipulation.getSetFieldForJava(rs.getString(2)), DataManipulation.getSetColumnHeightForJava(rs.getString(3))));
			}
	    	rs.close();
	    	stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return sets.toArray(new Set[sets.size()]);		
	}

	/* SELECT-Queries for Move */
	public static Move selectMove(int id) {
		Move move = new Move();
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "SELECT * FROM Moves WHERE id = "+id;
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) {
	    		move.setId(Integer.parseInt(rs.getString(1)));
	    		move.setRow(Integer.parseInt(rs.getString(2)));
	    		move.setColumn(Integer.parseInt(rs.getString(3)));
	    		move.setPlayer(rs.getString(4));
	    		move.setSetId(Integer.parseInt(rs.getString(5)));
	    	}
	    	rs.close();
	    	stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return move;
	}
	
	public static Move[] selectMoves(int setId) {
		ArrayList<Move> moves = new ArrayList<Move>();
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "SELECT * FROM Moves INNER JOIN GameToSetToMove ON Moves.id = GameToSetToMove.moveId WHERE GameToSetToMove.setId = "+setId;
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) {
	    		moves.add(new Move(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3)), rs.getString(4), Integer.parseInt(rs.getString(5))));
	    	}
	    	rs.close();
	    	stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return moves.toArray(new Move[moves.size()]);
	}
	
}