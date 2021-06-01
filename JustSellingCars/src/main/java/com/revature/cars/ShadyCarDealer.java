package com.revature.cars;

import java.util.logging.Logger;

import java.util.logging.Level;
import java.util.logging.Logger;
 
 



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.util.ConnFactory;
import com.revature.util.Log2File;

public class ShadyCarDealer  { 		//employee
	
public static boolean loggedin = true;	//can take actions until employee exits

public static ConnFactory cf=ConnFactory.getInst();
	
public void options() throws SQLException {
	
		Scanner es =new Scanner(System.in);
		while(loggedin) {
		System.out.println("You have gained new abilities:" + '\n'+'\n'
		+ "Consider an offer [0]" + '\n' 
		+ "Build deathtraps [1]" + '\n'
		+ "Dismantle deathtraps [2]" + '\n'
		+ "View all payment information [3]" + '\n'
		+ "Exit [-1]");
						
		int c = es.nextInt();
		
		switch(c) {
		
		case 0:
			accept_reject();
			break;
		case 1:
			buildDeathtrap();
			break;
		case 2:
			dismantleDeathtrap();
			break;
		case 3:
			viewAllPayments();
			break;
		case -1:
			loggedin= false;
			break;
		
		}
		
	}
		es.close();
}


public boolean accept_reject() throws SQLException {
	
	Connection conn=cf.getConn();
	Statement stmt=conn.createStatement();
	
	ResultSet rs=stmt.executeQuery("SELECT * FROM OFFERS" );
		
	ResultSetMetaData rsmd = rs.getMetaData();
	int cn =rsmd.getColumnCount();
	while(rs.next()) {
		
		for (int i =1; i<=cn;i++) {
			String columnValue = rs.getString(i);
			System.out.println(rsmd.getColumnName(i)+ ": "+ columnValue );
		}System.out.println("");
	}	
	
	

	Scanner es = new Scanner(System.in);
	System.out.println("choose a username");
	String username = es.nextLine();
	System.out.println("choose a target car");
	String target_car = es.nextLine();
	
	
	
	ResultSet rs0 = stmt.executeQuery("SELECT COUNT (*) FROM OFFERS WHERE BUYER = '" + username +"' and TARGET_CAR = '"+ target_car+"'");
	rs0.next();
	if(rs0.getInt("COUNT(*)") == 0 ) {
		System.out.println("offer does not exist");
		return false;
	}
	
	System.out.println("Accept (a) or Reject (r)");
	String c = es.nextLine();
	
	if (c.equals("a")) {
		
		
		
		PreparedStatement ps = conn.prepareStatement("UPDATE CARS SET INLOT = 'f' WHERE CAR_ID = ?");
		ps.setString(1, target_car);
		ps.executeUpdate();
		
		System.out.println(target_car + " has been bestowed to " + username + '\n');
		
		
		rs = stmt.executeQuery("SELECT INITIAL_PAYMENT AS y, LOAN_DURATION AS z, TRADE_VALUE AS x FROM OFFERS INNER JOIN CARS ON CAR_ID = TARGET_CAR WHERE TARGET_CAR = '"+ target_car +"' and BUYER = '"+ username+"'");
		rs.next();
	
		float y = rs.getFloat(1);
		int z = rs.getInt(2);
		float x = rs.getFloat(3);
		
		stmt.executeQuery("DELETE FROM OFFERS WHERE TARGET_CAR = '" + target_car +"'");	
		stmt.executeQuery("INSERT INTO OWNEDCARS (CAR_ID, OWNERR, REMAINING_PAYMENT, LOAN_DURATION) VALUES ('" + target_car +"', '" + username+"',"+ (x-y) +","+ z+')');
		
		Log2File.logThis("info", "offer has been accepted ");	
		return true;
		}
	
	else {
		stmt.executeQuery("DELETE FROM OFFERS WHERE TARGET_CAR ='" + target_car +"' and BUYER = '"+ username+"'");
		return false;
	}
		
}

//creates a vehicle and adds it to database
//and to the ArrayList
public Deathtrap buildDeathtrap() throws SQLException {
	String sp = "f";
	
	//vehicle type
	Scanner es = new Scanner(System.in);
	System.out.println("Vehicle type? [0-3]-> [war_rig, rust_buggy, transport, motorbike]");
	int vt = Integer.parseInt(es.nextLine());
	Deathtrap.Type type = Deathtrap.Type.values()[vt % 4];
	
	//spikes
	boolean spikywheels=false;
	System.out.println("spikes on wheels? [y/n]");
	String mes="";
	String response = es.nextLine();
	if(response.equals("y")) {
		spikywheels = true;
		sp="t";
		mes = "and spiked wheels ";
	}
	
	//durability_score
	System.out.println("durability? [0 - 99]");
	int durability_score = (Integer.parseInt(es.nextLine()) % 99);
	
	//speed_score
	System.out.println("speed? [0 - 99]");
	int speed = (Integer.parseInt(es.nextLine()) % 99);
	
	//trade value
	System.out.println("trade value?");
	double trade_value = Integer.parseInt(es.nextLine());
	
	//ensures # is positive
	trade_value = trade_value*trade_value;
	
	trade_value = Math.sqrt(trade_value);
	
	System.out.println("ID this vehicle");
	String car_id = es.nextLine();
	
	//constrains car_id to be unique
	Connection conn=cf.getConn();
	Statement stmt=conn.createStatement();
	
	ResultSet r0 = stmt.executeQuery("SELECT COUNT (*) FROM CARS WHERE CAR_ID  = '" + car_id+ "'");
	r0.next();
	if(r0.getInt("COUNT(*)") > 0 ) {
		System.out.println("ID is taken. Try again ");
		return buildDeathtrap();
	}
	
	
	Deathtrap c = new Deathtrap(car_id, type, spikywheels, durability_score, speed, trade_value, true);

	ResultSet rs=stmt.executeQuery("INSERT INTO CARS (CAR_ID, TYPEE, SPIKY_WHEELS, DURABILITY,SPEED,TRADE_VALUE, INLOT) VALUES ('"+ car_id + "','" + type+ "', '" + sp +"','" + durability_score +"','"+ speed + "','" + trade_value + "','" + "t')");
	
	System.out.println(car_id  + " of class " + type + " with "+ durability_score + " durability " + "and "+ speed + " speed "+ mes  +"has been added to lot "+ "(trade value "+ trade_value + ")."+"\n");
	
	Log2File.logThis("info", car_id+ " has been added ");	
	return c;
	}



//deletes a car from database if it is in the lot 
//and from arrayList
public void dismantleDeathtrap() throws SQLException {
	Scanner es = new Scanner(System.in);
	System.out.println("Select a vehicle in the lot to destroy. All pending offers and potential evidence will be destroyed with it.");
	String car_id = es.nextLine();
	
	Connection conn=cf.getConn();
	Statement stmt=conn.createStatement();
	ResultSet rs = stmt.executeQuery("DELETE FROM OFFERS WHERE TARGET_CAR = '"+ car_id +"'");
	ResultSet rs1 = stmt.executeQuery("DELETE FROM CARS WHERE CAR_ID = '"+ car_id +"' and INLOT = 't'");

	}

//searches through owned cars for payment details
public void viewAllPayments() throws SQLException {
	
	Connection conn=cf.getConn();
	Statement stmt=conn.createStatement();
	
	ResultSet rs=stmt.executeQuery("SELECT CAR_ID, OWNERR,LOAN_DURATION, REMAINING_PAYMENT/LOAN_DURATION AS MONTHLY_RATE, REMAINING_PAYMENT AS TOTAL_REMAINING FROM OWNEDCARS" );
		
	ResultSetMetaData rsmd = rs.getMetaData();
	int cn =rsmd.getColumnCount();
	while(rs.next()) {
		
		for (int i =1; i<=cn;i++) {
			String columnValue = rs.getString(i);
			System.out.println(rsmd.getColumnName(i)+ ": "+ columnValue );
		} 
		System.out.println("");
	}	
	
	}
}
