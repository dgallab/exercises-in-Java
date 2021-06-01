import java.util.Scanner;

import java.util.Arrays;

public class OverloadingPractice {

	public static Scanner scanI = new Scanner(System.in);
	
	public static Scanner scanS = new Scanner(System.in);
	
	static int[] someArray= new int[4]; 
	
	static int z;
	
	static boolean[] LastArrayB= new boolean[4]; 
	
	static String[] LastArrayS = new String[4];
	
	static int[] LastArrayI= new int[4];
	
	
 public static void main(String[] args) {
			
			for(int i=0;i<4;i++) {				
				System.out.println("Choose an integer value for position " + i +"." );
				String v = scanS.nextLine();
				//here I am using a method from the Integer wrapper class
				someArray[i] = Integer.parseInt(v);
			}
			
			System.out.println("Choose an integer value");
			z = Integer.parseInt(scanI.nextLine());
						

			
			//instantiations of alterArray
			// if not all methods are run, constructor will load in default values
			//for its arrays
			LastArrayB = alterArray(someArray, z);
			LastArrayS = alterArray(someArray, 'h');
			LastArrayI = alterArray(someArray, true);
					
			System.out.println(Arrays.toString(LastArrayB));
			System.out.println(Arrays.toString(LastArrayS));
			System.out.println(Arrays.toString(LastArrayI));
			
			
		}
		
		//this method returns an array of strings, with each element equal to the string
		//each element in the string is a number of chars
		static String[] alterArray(int[] someArray, char c) {
			String[] newArray = new String[4] ;
			
			for (int i=0;i<4;i++) {
				//utilizing String wrapper class
				String r = String.valueOf(c);
				
				//this for loop constructs each string
				for(int j=0;j<someArray[i]-1;j++) {
					r=r+c;	
				}
				newArray[i]=r;
			}
			return newArray;
		}
		
		
		//this method returns an array of booleans determining whether the array element
		//matches the integer
		static boolean[] alterArray(int[] someArray, int x) {
			boolean[] newArray = new boolean[4];
			int i = 0;
			while(i<4) {
				newArray[i]= x == someArray[i];
				i++;
			}
			return newArray;	
		}
		
		
		//this method returns an array with each element squared
		//if b is true, and each element 0 if b is false
		static int[] alterArray(int[] someArray, boolean b) {
			
			int[] newArray= new int[4]; 
			
			int x=0;
			if (b) {
				for (int i: someArray) {					
					newArray[x]=i*i;
					x++;
				}	
			} else {
				int i=0;
				while(i<4) {
					newArray[i]=0;
					i++;
				}
			}
			return newArray;
	}
}