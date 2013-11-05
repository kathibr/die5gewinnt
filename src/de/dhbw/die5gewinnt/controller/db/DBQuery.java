package de.dhbw.die5gewinnt.controller.db;

import java.sql.Connection;

public class DBQuery {

	/* Konstruktor */
	protected DBQuery() {}
	
	/* Methode gibt die vorhandene DB-Verbindung zur HSQLDB zurück */
	protected static Connection getDBConnection() {
		return DBConnector.getDBConnection();
	}
	
}