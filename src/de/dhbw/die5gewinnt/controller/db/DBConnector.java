package de.dhbw.die5gewinnt.controller.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
	private static Connection dbConnection;
	
    private DBConnector() {          
	    dbConnection = null;
    }
    
	protected static Connection getDBConnection() {
    	if(dbConnection == null)
    		openDBConnection();
    	return dbConnection;
    }
    
	private static void openDBConnection() {
	    try {
	    	dbConnection = DriverManager.getConnection("jdbc:hsqldb:file:db/; shutdown=true", "die5gewinnt", "lauterbach" );
	    } catch(Exception e) {
	    	System.err.println("Connection failed!");
	    }		
	}
	
    protected static void closeDBConnection() {
    	if(dbConnection != null) {
    		try { 
    			dbConnection.close(); 
            } catch (SQLException e) { 
                e.printStackTrace(); 
            }
    	}
    	dbConnection = null;
    }
    
}