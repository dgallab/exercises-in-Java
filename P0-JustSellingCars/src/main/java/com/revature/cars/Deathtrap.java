package com.revature.cars;

import java.util.ArrayList;

public class Deathtrap {
	
	public static ArrayList<Deathtrap> lot = new ArrayList<Deathtrap>(); //dynamic array which stores car_ids
	public static enum Type {war_rig, rust_buggy, transport, motorbike};
	
	String car_id; 		 //custom name
	Type type;			  //rig, buggy, transport, motorbike
	boolean spikywheels;  //true or false
	int durability_score; //[kitten 0 - 100 death star]
	int speed;
	double trade_value;     //constrained to be positive
	boolean inLot;			//true for unowned cars, false for owned/destroyed cars
	
	public Deathtrap(String car_id, Type type, boolean spikywheels, int durability_score, int speed, double trade_value, boolean inLot) {
		
		this.car_id = car_id;
		this.type = type; 
		this.spikywheels=spikywheels;
		this.durability_score = durability_score;
		this.speed = speed;
		this.trade_value = trade_value;
		this.inLot=inLot;
		lot.add(this);
	}
	
	public static void removeDeathtrap(Deathtrap c) {
		c.inLot = false;
	}

}
