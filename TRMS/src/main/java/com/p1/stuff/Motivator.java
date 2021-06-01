package com.p1.stuff;

import java.sql.SQLException;
import java.util.Scanner;

public class Motivator {

	public static void main(String[] args) throws SQLException {
		
		boolean online = true;
		
		while(online) {
		
		System.out.println("Welcome to the System:" + '\n'+'\n'
				+ "EmployeeLogin [0]" + '\n' 
				+ "ApproverLogin [1]" + '\n'
				+ "Exit [-1]");	
		Scanner sc= new Scanner(System.in);
		int role = sc.nextInt();
		
		switch (role) {
		
		//exit system
		case -1:
		online = false;	
		break;
		//employee
		case 0:
			Employee.options();
			break;
		//department supervisor	
		case 1:
			Approver.options();
			break;
		}
	}
	}
}
