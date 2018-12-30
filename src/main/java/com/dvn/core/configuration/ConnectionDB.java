package com.dvn.core.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private static ConnectionDB own;
	private static Connection conn;
	
	private ConnectionDB() {
		String url = "jdbc:postgresql://localhost/core-dvn";
		String usuario = "postgres";
		String senha = "postgres";
		
		
		try{
			Class.forName("org.postgresql.Driver").getConstructor().newInstance();
			ConnectionDB.conn = DriverManager.getConnection(url+"?user="+usuario+"&password="+senha);
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public static Connection getConn() {
		
		if(ConnectionDB.own == null) {
			ConnectionDB.own = new ConnectionDB();
			return ConnectionDB.conn; 
		}
		return ConnectionDB.conn; 
	}
	
	public static void closeConn() {
		try {
			ConnectionDB.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
