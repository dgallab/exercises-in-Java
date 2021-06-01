package yahtzee;

import java.util.Scanner;
/**

* @author Daniel Gallab
* @version 1.1
* @since 1.0
* <h4>The Scorelineclass</h4> 
* The Scoreline class checks the boolean values to determine what
* lower scorecard to display to the user as well as running the USscoring function
* to get scores for the upper scorecard
 */


public class Scoreline extends Scoring {
	


public static boolean playagain=true;
/**
 *constructor calls the functions of uscoreDisplay and lscoreDisplay
 *(upper scorecard and lower scorecard)
 *<p>
 */	
	
	 Scoreline(){
		uscoreDisplay();
		lscoreDisplay();
	 }
	 /**
	  *see USscoring(i)
	  */	 	
	 
	private void uscoreDisplay(){ //upper scorecard
		int t;
		System.out.print("Upper Scorecard:"+ '\n');
		for(int i=1;i<=F;i++){
			t=USscoring(i);
		System.out.print("Score "+ t +" on the "+ i + " line."+'\n');
		}
	}
	 /**
	  *evaluates boolean values to determine what the lower section scoring looks like
	  *<p>
	  */	 	
	private void lscoreDisplay(){
		int x=0;
		System.out.print("Lower Scorecard:"+ '\n');
		if(a)
			x=1;
		else
			x=0;
		System.out.print("Score "+ x*g +" on the 3 of a Kind line."+'\n');
		if(b)
			x=1;
		else
			x=0;
		System.out.print("Score "+ x*g +" on 4 of a Kind line."+'\n');
		if(c)
			x=1;
		else
			x=0;
		System.out.print("Score "+ x*25 +" on the Full House line."+'\n');
		if(d)
			x=1;
		else
			x=0;
		System.out.print("Score "+ x*30 +" on the Small Straight line."+'\n');
		if(e)
			x=1;
		else
			x=0;
		System.out.print("Score "+ x*40 +" on the Large Straight line."+'\n');
		if(f)
			x=1;
		else
			x=0;
		System.out.print("Score "+ x*50 +" on the Yahtzee line."+'\n');
		System.out.print("Score "+ g +" on the Chance line."+'\n');	
	}	
	
		
		
			}
				
	
	

	


