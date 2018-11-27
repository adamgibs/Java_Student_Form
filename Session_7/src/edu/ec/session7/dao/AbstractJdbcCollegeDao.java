package edu.ec.session7.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Abstract class that allows connection the the College DB. Needs to be implemented for all
 * DAOs that connect to this database
 * @author adamg
 *
 */
public abstract class AbstractJdbcCollegeDao {
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/college";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    /**
     * Returns a connection to the database.
     * @return Database connection
     * @throws SQLException
     */
    final protected Connection getConnection() throws SQLException
    {
        try
        {
            Connection myConnection;
            Class.forName(DRIVER_NAME);
            myConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            return myConnection;
        }
        catch(ClassNotFoundException cnfe)
        {
            throw new SQLException(cnfe);
        }
    }
    
    /**
     * Closes each non-null resource.
     * @param conn
     * @param stmt
     * @param rs
     */
    final protected void releaseResources(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception ex) {
               
            }
        }
       
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception ex) {
               
            }
        }
       
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception ex) {
               
            }
        }
    }
}
