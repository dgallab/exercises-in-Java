package com.revature.varargs;

public class FunWithVarargs {
	/*Variable Length Arguments!
	 * Method that will take a variable number of arguments
	 * Automates and hides the process of creating and saving the values in an array
	 * prior to invoking the method
	 * Vararg MUST be the last argument
	 * Can be of any type
	 */
	
	public static void vaTest(int  ... v) {
		System.out.print("Number of Args is: "+ v.length+ " Contents: ");
		for(int x:v) {
			System.out.print(x+" ");
		}
	}
	
	public static void main(String ... args) {
		vaTest(10);
		System.out.println();
		vaTest(1,4,2,65,7,34,7,3,78,9,4,3,65,8,4,4,6,5,34,6,8);
		System.out.println();
		vaTest();
	}

}
