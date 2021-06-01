package com.revature.ol;

public class Calculator {
	
	static int a;
	static double b;
	
	public static void main(String[] args) {
		a=5;
		b=a;	
		b=3.9;
		a=(int)b;
		Double d=5.2;
		Integer e= 8;
		//System.out.println(a);
		//System.out.println(b);
		/*add(add(5,4),6);
		add(1.3, 4.5);
		add(4,5,6);
		System.out.println(add(1,2,3,4));*/
		System.out.println(add(d.intValue(),(int)5.1,3,e));
		
	}
	//Parametric polymorhism
	static int add(int a, int b) {
		System.out.println("This is 1");
		System.out.println(a+b);
		return a+b;
	}

	static double add(double a, double b) {
		System.out.println("This is 2");
		return a+b;
	}
	static int add(int a, int b, int c) {
		System.out.println("This is 3");
		return a+b+c;
		
	}
	static int add(int ...a) {
		System.out.println("This is 4");
		int total=0;
		for(int i:a) {
			total=total+i;
		}
		return total;
	}
}
