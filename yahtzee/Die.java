
package yahtzee;

import java.util.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
/**

* @author Daniel Gallab
* @version 1.1
* @since 1.0
* <h1>The Die class</h1> 
* has the attributes a regular die would. It can
* be rolled to get a random number between 1 and 6 (or between 1 and F)
* where F is changed through the settings. The die class also contains 
* the code which reads numbers from a file to determine the settings, and
* asks a user if they want to change the settings.

 */

public class Die extends JFrame1 {

/**
* F, DIP, and T, mean faces of each die, dice in play, and turns (rerolls)
* and are the 3 settings which can be changed by the user
*/	
public static int F;
public static int DIP;
public static int T=1;
private int faceup;
/**
 * constructor asks user to change config and contains faceup variable
 * <p>
 * if user decides to change configuration, the new settings are stored in a 
 * separate file for later
 * <p>
 */
public Die(){
	faceup=1;
	getconf();
	System.out.print(DIP);
	System.out.print(F);
	
}
	void changeConfig(){
	try{
		File f=new File("C://Users//dgall//workspace//yahtzee//src//yahtzee//yahtzeeConfig.txt");
		BufferedReader br=new BufferedReader(new FileReader(f));
		DIP=Integer.parseInt(br.readLine());
		F=Integer.parseInt(br.readLine());
		T=Integer.parseInt(br.readLine());
		System.out.println("You are playing with "+DIP+" "+F+"-sided dice." +'\n');
		System.out.println("You have "+T+" rolls per hand." +'\n');
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter 'yes' if you would like to change the configuration.");
		String input = scanner.nextLine();
		if(input.equals("yes")){
			System.out.print("Enter the number of dice in play");
			DIP = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter the number of faces on each die");
			F = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter the number of rolls per hand");
			T = Integer.parseInt(scanner.nextLine());
			FileOutputStream stream= new FileOutputStream(f,false);
			String o=Integer.toString(DIP)+'\n'+Integer.toString(F)+'\n'+Integer.toString(T);
			byte[]newconf=o.getBytes();
			stream.write(newconf);
			br.close();
			stream.close();
		}
		
	}
	catch(IOException w){
		System.out.println("IOException");
			}

}
	
/**
 * @returns faceup, the die value on top
 */	
public int getFaceup(){
	return faceup;
	
}
/**
 * rolls the die, getting another value between 1 and F
 * <p>
 */
public void roll(){
	Random roller=new Random();
	faceup=roller.nextInt(F)+1;
}

void getconf(){
	DIP=x;
	F=y;
	T=2;
}

}







