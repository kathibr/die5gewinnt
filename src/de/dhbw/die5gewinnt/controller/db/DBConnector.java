package de.dhbw.die5gewinnt.controller.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnector {
	
	private static DBConnector dbConnector;
	private static Connection dbConnection;
	
    private DBConnector() {          
    	dbConnector = null;
	    dbConnection = null; 
	    
	    try {
	    	dbConnection = DriverManager.getConnection("jdbc:hsqldb:file:db/; shutdown=true", "die5gewinnt", "lauterbach" );
	    } catch(Exception e) {
	    	System.err.println("Connection failed!");
	    }
    }
    
    @SuppressWarnings("static-access")
	public static Connection getDBConnection() {
    	if(dbConnector == null)
    		dbConnector = new DBConnector();
    	return dbConnector.dbConnection;
    }
    
    public static void closeDBConnection() {
    	if(dbConnector != null) {
    		try { 
    			dbConnection.close(); 
            } catch (SQLException e) { 
                e.printStackTrace(); 
            }
    	}
    	dbConnector = null;
    	dbConnection = null;
    }
    
}