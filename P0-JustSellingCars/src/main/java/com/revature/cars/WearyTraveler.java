package com.revature.cars;

import java.util.ArrayList;
import java.util.Scanner;
import com.revature.util.ConnFactory;
import com.revature.util.Log2File;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class WearyTraveler {
	
	public static ConnFactory cf=ConnFactory.getInst();
	
	public static ArrayList<String[]> mob = new ArrayList<String[]>(); //holds customer data
	
	public static boolean loggedin = true; //can take actions until user exits
	
//either a new user or customer	
public void options() throws SQLException {
	
	Scanner cs =new Scanner(System.in);
	System.out.println("Identification, please");
	String un = cs.nextLine();
	
	if (login(un)) {
		
	
		while(loggedin) {
			
		 System.out.println("You have gained new abilities:" + '\n'+'\n'
		 + "Make an offer [0]" + '\n' 
		 + "View your deathtraps/finances [1]" + '\n'
		 + "Browse lot [2]" + '\n' 
		 + "Exit [-1]");
		
		 int c = cs.nextInt();
		 switch(c) {
			case 0:
				makeOffer(un);
				break;
			case 1:
				viewOwned(un);
				break;
			case 2:
				browseLot();
				break;
			case -1:
				loggedin= false;
				break;	
		 	}
		}
	}
}

//for new users - confirms username is unique
//adds a username and password to the USERSS table
//and to mob
public void registration() throws SQLException {
	
	String[] user_info= new String[2];
	Scanner cs = new Scanner(System.in);
	
	System.out.println("Enter a username");
	user_info[0] = cs.nextLine();
	
	Connection conn=cf.getConn();
	Statement stmt=conn.createStatement();
	
	ResultSet rs=stmt.executeQuery("SELECT COUNT (*) FROM USERSS WHERE USERNAME = '" + user_info[0] + "'" );
	rs.next();
    if(rs.getInt("COUNT(*)")!=0){
    	System.out.println("username already taken");
    	registration();
    }
    else {
	
	System.out.println("Enter a password");
	user_info[1] = cs.nextLine();
	stmt.executeQuery("INSERT INTO USERSS (USERNAME, PASSWORDD) VALUES ('" + user_info[0] + "'" + ", '"+ user_info[1]+"')");
	
	System.out.println("account creation successful");
	mob.add(user_info);
	Log2File.logThis("info", "new user + "+ user_info[0]+ " has been added. ");	
	}
}


//for returning users - confirms username exists
//and confirms password is true

//if the former is false, redirects to registration
//if the latter is false, redirects to options
public boolean login(String username) throws SQLException {
	
	
	Scanner cs = new Scanner(System.in);
	
	Connection conn=cf.getConn();
	Statement stmt=conn.createStatement();
	
	ResultSet rs=stmt.executeQuery("SELECT COUNT (*) FROM USERSS WHERE USERNAME = '" + username + "'" );
	rs.next();
	
	 if(rs.getInt("COUNT(*)") == 0 ){	
		System.out.println("username does not exist. would you like to register [y/n]?");
		String c = cs.nextLine();
		if (c.equals("y")) {
			registration();
			return true; 
		}
		else 
			cs.close();
			 return false;	
	}
	else { 
		System.out.println("Enter your password");
		String password = cs.nextLine();
		
		ResultSet rs1=stmt.executeQuery("SELECT PASSWORDD FROM USERSS WHERE USERNAME = '" + username + "'" );
		rs1.next();
		
		if(password.equals(rs1.getString(1)))  //confirm password
			return true;

		else {
			System.out.println("incorrect password");
			options();
			return false;
		}
	}
}
//displays CARS table for all cars that are in Lot
public void browseLot() throws SQLException {
	Connection conn=cf.getConn();
	Statement stmt=conn.createStatement();
	
	ResultSet rs=stmt.executeQuery("SELECT * FROM CARS WHERE INLOT = 't'");	
	ResultSetMetaData rsmd = rs.getMetaData();
	
	int cn =rsmd.getColumnCount();
	//System.out.println(cn);
	while(rs.next()) {
		
		for (int i =1; i<=cn;i++) {
			String columnValue = rs.getString(i);
			System.out.println(rsmd.getColumnName(i)+ ": "+ columnValue );
		}System.out.println("");
	}	
}

//displays OWNEDCARS table for cars whose OWNER 
//matches the username
public void viewOwned(String username) throws SQLException {
	Connection conn=cf.getConn();
	Statement stmt=conn.createStatement();
	
	ResultSet rs=stmt.executeQuery("SELECT * FROM OWNEDCARS NATURAL JOIN CARS WHERE OWNERR = '" + username + "'" );
		
	ResultSetMetaData rsmd = rs.getMetaData();
	int cn =rsmd.getColumnCount();
	while(rs.next()) {
		
		for (int i =1; i<=cn;i++) {
			String columnValue = rs.getString(i);
			System.out.println(rsmd.getColumnName(i)+ ": "+ columnValue );
		}System.out.println("");
	}	
	
}



//displays the remaining payments from the OWNER table
//for all that person's owned cars
//a BUYER chooses TARGET_CAR, INITIAL_AMOUNT, LOAN DURATION
//if a USER selected a TARGET_CAR that he already has an offer on
//then only the old payment information is modified to reflect the new bid
public void makeOffer(String username) throws SQLException {
	
	Connection conn=cf.getConn();
	Statement stmt=conn.createStatement();
	
	Scanner cs = new Scanner(System.in);
	
	System.out.println("Name a vehicle");
	String target_car = cs.nextLine();
	
	ResultSet rs4=stmt.executeQuery("SELECT COUNT (*) FROM CARS WHERE CAR_ID  = '" + target_car + "' and INLOT = 't' " );
	rs4.next();
	if(rs4.getInt("COUNT(*)") == 0 ) {
		System.out.println("car not found");
		return;
	}
	System.out.println("Initial payment?");
	double initial_payment = Integer.parseInt(cs.nextLine());
	System.out.println("Loan duration (in months)?");
	int loan_duration = Integer.parseInt(cs.nextLine());
	
	ResultSet rs1=stmt.executeQuery("SELECT COUNT (*) FROM OFFERS WHERE TARGET_CAR  = '" + target_car + "' and BUYER= '"+ username + "'" );
	rs1.next();
	if(rs1.getInt(1) == 0) {
		ResultSet rs2 = stmt.executeQuery("INSERT INTO OFFERS (BUYER, TARGET_CAR, INITIAL_PAYMENT, LOAN_DURATION) VALUES ('" + username +"', '" + target_car +"', " + initial_payment + ", " + loan_duration +")");
		System.out.println("new bid has been submitted");
	}
	
	else {
		 ResultSet rs3 = stmt.executeQuery("UPDATE OFFERS SET INITIAL_PAYMENT = " + initial_payment +", LOAN_DURATION = " + loan_duration +" WHERE BUYER= '" + username + "' and TARGET_CAR = '"+target_car+ "'");
		 System.out.println("previous bid has been updated");
		}
	}

}


