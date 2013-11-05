package de.dhbw.die5gewinnt.controller.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.dhbw.die5gewinnt.model.*;

public class DBSelects extends DBQuery {

	/* Konstruktor */
	private DBSelects() {
		super();
	}
	
	/* Methode generiert die nächsten primären Schlüssel der Model */
	public static void initAutoIncrementKeys() {
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "SELECT TOP 1 id FROM Games ORDER BY id DESC";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				AutoIncrementKeys.setGameId(Integer.parseInt(rs.getString(1)));
			}
			rs.close();
			sql = "SELECT TOP 1 id FROM Sets ORDER BY id DESC";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				AutoIncrementKeys.setSetId(Integer.parseInt(rs.getString(1)));
			}
			rs.close();
			sql = "SELECT TOP 1 id FROM Moves ORDER BY id DESC";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				AutoIncrementKeys.setMoveId(Integer.parseInt(rs.getString(1)));
			}
			rs.close();	
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* SELECT-Queries for Model Game */
	/* Methode gibt ein bestimmtes Spiel aus der Datenbank zurück */
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
	    		game.setWinner(DataManipulation.getBooleanFromString(rs.getString(4)));
	    		game.setPlayer(rs.getString(5));
	    		game.setOpponentName(rs.getString(6));
	    	}
	    	rs.close();
	    	stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return game;		
	}
	
	/* Methode gibt alle Spiele (evtl. inkl. Sätze) aus der Datenbank zurück */
	public static Game[] selectGames(boolean withLoadingSets) {
		ArrayList<Game> oldGames = new ArrayList<Game>();
		Game game = null;
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "SELECT * FROM Games";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				game = new Game();
					game.setId(Integer.parseInt(rs.getString(1)));
					game.setName(rs.getString(2));
					if(withLoadingSets)
						game.setSets(selectSets(Integer.parseInt(rs.getString(1))));
					game.setScore(DataManipulation.getGameScoreForJava(rs.getString(3)));
					game.setWinner(DataManipulation.getBooleanFromString(rs.getString(4)));
					game.setPlayer(rs.getString(5));
					game.setOpponentName(rs.getString(6));
				oldGames.add(game);
	    	}
	    	rs.close();
	    	stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return oldGames.toArray(new Game[oldGames.size()]);	
	}
	
	/* Methode gibt die ID eines Spiels anhand eines Satzes zurück */
	public static int selectGameIdFromSetId(int setId) {
		int gameId = 0;
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "SELECT gameId FROM GameToSetToMove WHERE setId = "+setId;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				gameId = Integer.parseInt(rs.getString(1));
	    	}
	    	rs.close();
	    	stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return gameId;			
	}
	
	/* Methode gibt die ID eines Spiels anhand eines Zuges zurück */
	public static int selectGameIdFromMoveId(int moveId) {
		int gameId = 0;
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "SELECT gameId FROM GameToSetToMove WHERE moveId = "+moveId;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				gameId = Integer.parseInt(rs.getString(1));
	    	}
	    	rs.close();
	    	stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return gameId;	
	}
	
	/* SELECT-Queries for Model Set */
	/* Methode gibt einen bestimmten Satz aus der Datenbank zurück */
	public static Set selectSet(int id) {
		Set set = new Set();
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "SELECT * FROM Sets WHERE id = "+id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int setId = (Integer.parseInt(rs.getString(1))); 
				set.setId(setId);
				set.setMoves(selectMoves(id));
				set.setField(DataManipulation.getSetFieldForJava(setId, rs.getString(2)));
				set.setColumnHeight(DataManipulation.getSetColumnHeightForJava(rs.getString(3)));
				set.setFirstMove(DataManipulation.getBooleanFromString(rs.getString(4)));
				set.setStatus(Integer.parseInt(rs.getString(5)));
	    	}
	    	rs.close();
	    	stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return set;		
	}
	
	/* Methode gibt alle Sätze eines bestimmten Spiels aus der Datenbank zurück */
	public static Set[] selectSets(int gameId) {
		ArrayList<Set> sets = new ArrayList<Set>();
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "SELECT DISTINCT id, field, columnheight, firstmove, status FROM Sets INNER JOIN GameToSetToMove ON Sets.id = GameToSetToMove.setId WHERE GameToSetToMove.gameId = "+gameId;
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) {
				int setId = Integer.parseInt(rs.getString(1));
				Set set = new Set(selectMoves(setId), DataManipulation.getSetFieldForJava(setId, rs.getString(2)), DataManipulation.getSetColumnHeightForJava(rs.getString(3)), DataManipulation.getBooleanFromString(rs.getString(4)), Integer.parseInt(rs.getString(5)));
				sets.add(set);
			}
	    	rs.close();
	    	stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return sets.toArray(new Set[sets.size()]);		
	}
	
	/* Methode gibt die ID eines Satzes anhand eines Zuges zurück */
	public static int selectSetIdFromMoveId(int moveId) {
		int setId = 0;
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "SELECT setId FROM GameToSetToMove WHERE moveId = "+moveId;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				setId = Integer.parseInt(rs.getString(1));
	    	}
	    	rs.close();
	    	stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return setId;			
	}

	/* SELECT-Queries for Model Move */
	/* Methode gibt einen bestimmten Zug aus der Datenbank zurück */
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
	    	}
	    	rs.close();
	    	stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return move;
	}
	
	/* Methode gibt alle Züge eines bestimmten Satzes aus der Datenbank zurück */
	public static Move[] selectMoves(int setId) {
		ArrayList<Move> moves = new ArrayList<Move>();
		try {
			Statement stmt = getDBConnection().createStatement();
			String sql = "SELECT * FROM Moves INNER JOIN GameToSetToMove ON Moves.id = GameToSetToMove.moveId WHERE GameToSetToMove.setId = "+setId;
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) {
	    		moves.add(new Move(Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3)), rs.getString(4)));
	    	}
	    	rs.close();
	    	stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return moves.toArray(new Move[moves.size()]);
	}
	
}