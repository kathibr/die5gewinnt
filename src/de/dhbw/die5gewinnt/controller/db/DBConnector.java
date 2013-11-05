package de.dhbw.die5gewinnt.controller.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
	private static Connection dbConnection;
	
	/* Konstruktor */
    private DBConnector() {          
	    dbConnection = null;
    }
    
    /* Zugriffs-Methode für das Singleton-Pattern */
	protected static Connection getDBConnection() {
    	if(dbConnection == null)
    		openDBConnection();
    	return dbConnection;
    }
    
	/* Methode erstellt eine DB-Verbindung zur HSQLDB */
	private static void openDBConnection() {
	    try {
	    	dbConnection = DriverManager.getConnection("jdbc:hsqldb:file:db/; shutdown=true", "die5gewinnt", "lauterbach" );
	    } catch(Exception e) {
	    	System.err.println("--- Open connection failed!");
	    	e.printStackTrace();
	    }		
	}
	
	/* Methode schließt die vorhandene DB-Verbindung zur HSQLDB */
    public static void closeDBConnection() {
    	if(dbConnection != null) {
    		try { 
    			dbConnection.close(); 
            } catch (SQLException e) { 
            	System.err.println("--- Close connection failed!");
                e.printStackTrace(); 
            }
    	}
    	dbConnection = null;
    }
    
}