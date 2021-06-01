package com.revature.cars;


import java.sql.SQLException;
import java.util.Scanner;

public class Motivator {


	public static void main(String[] args) throws SQLException {
		
		Scanner s = new Scanner(System.in);
		System.out.print("Greetings, traveler - what do you seek? [0->employee, 1->returning customer 2->new customer]");
		int c = s.nextInt();
		
		//employee
		if (c==0) {
			System.out.println("Daniel stop, you know I work here." + '\n');
			ShadyCarDealer scd = new ShadyCarDealer();
			scd.options();
			
			
			
		}
		//customer
		else if (c==1){
			System.out.println("I am back for the most wicked of rides." + '\n');
			WearyTraveler wt = new WearyTraveler();
			wt.options();
			
			
		
		}
		//user
		else {
			System.out.println("I just want a car. Why are you whispering?" + '\n');
			WearyTraveler wt = new WearyTraveler();
			wt.registration();	
			
			//wt.options();
		}
		s.close();
	}
	
}
