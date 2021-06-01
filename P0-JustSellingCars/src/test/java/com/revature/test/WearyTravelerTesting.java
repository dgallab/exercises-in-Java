package com.revature.test;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.revature.cars.WearyTraveler;

public class WearyTravelerTesting {

	//a simple test
	@Test
	public void test()   {
		WearyTraveler wt = new WearyTraveler();
	
		try {
			Boolean b = wt.login("Furiosa"); 
			//Furiosa' password is absFurious6 - if anything else the test will fail
			Assert.assertEquals(b,true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
