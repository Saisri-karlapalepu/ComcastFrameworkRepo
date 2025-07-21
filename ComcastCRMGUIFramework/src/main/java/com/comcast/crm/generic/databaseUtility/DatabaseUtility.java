package com.comcast.crm.generic.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	Connection conn;
	public void getDbConnection() throws SQLException {
		try {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		
		conn=DriverManager.getConnection("jdbc://mysql://localhost:3006/world","root","Lakshmi@$#11");
		
		}catch(Exception e) {
		}
	}
    public void closeDbconnection() throws SQLException {
    	conn.close();
    }
    public ResultSet executeSelectQuery(String query) throws SQLException {
    	ResultSet result=null;
    	try {
    	Statement stat=conn.createStatement();
    	result=stat.executeQuery(query);
    	}catch(Exception e) {}
    	return result;
    }
    public int executeNonSelectQuery(String query) {
    	int result=0;
    	try {
        	Statement stat=conn.createStatement();
        	result=stat.executeUpdate(query);
        	}catch(Exception e) {}
        	return result;
    }
}
