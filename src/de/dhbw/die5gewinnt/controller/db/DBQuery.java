package de.dhbw.die5gewinnt.controller.db;

import java.sql.Connection;

public class DBQuery {

	protected DBQuery() {}
	
	protected static Connection getDBConnection() {
		return DBConnector.getDBConnection();
	}
	
}