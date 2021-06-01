package com.p1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnFactory{
	
	private static ConnFactory cf = new ConnFactory();
	
	private ConnFactory() {
		super();
		
	}
	
	public static synchronized ConnFactory getInst() {
		if (cf == null) {
			cf = new ConnFactory();
			
		}
		return cf;
	}
	
	public Connection getConn() {
		
		
		String url="jdbc:oracle:thin:@myfirstdb.c0hned2o7uzc.us-east-2.rds.amazonaws.com:1521:ORCL";
		String username="MasterWarriorDan";
		String password="Sadpower0";
		
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,username,password);
		  
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}	
}
	