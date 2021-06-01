



package yahtzee;

import java.util.*;
import java.util.Arrays;
/**

* @author Daniel Gallab
* @version 1.1
* @since 1.0
* <h2>The Hand class</h2> 
* The Hand class calls functions of the Die class a number of times related
* to how many dice are in play. A user playing yahtzee has the option to roll 2 more
* times before they score, unless this is changed in the settings

 */


public class Hand extends Die {
	public int[] hand=new int[DIP];
	int x=T;
	/**
	 * constructor calls functions from the die class (aggregation)
	 * to make a new hand
	 * <p>
	 * it keeps track of how many rolls are left to determine if the user
	 * can roll a new hand
	 * <p>
	 */
	 Hand(){
		 gethand();
	 }
	 void gethand(){
		for(int i=0;i<DIP;i++){
				roll();
				hand[i]=getFaceup();
		}
		peekhand();
		Scanner scanner=new Scanner(System.in);
		if(x==1){
			peekhand();
			return;
		}
		System.out.print("Do you want to reroll?"); //enter yes to reroll
		String input = scanner.nextLine();
		if(input.equals("yes")){
			x=x-1;
			getnewhand();
		}
		else{
			x=1;
			peekhand();
		}
	}
	 	/**
		 * user is asked to input a string representing which dice they want to reroll
		 * or can revoke their decision to reroll to get score
		 * <p>
		 */
	private void getnewhand(){
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter y to keep, n to reroll, in order of dice rolled");
		String input = scanner.nextLine();
		char[] change= input.toCharArray();
		for(int i=0;i<input.length();i++)
			if(change[i]=='n'){
				roll();
				hand[i]=getFaceup();
			}	
		peekhand();
		Scanner scanner1=new Scanner(System.in);
		if(x!=1){
			System.out.print("Do you want to reroll?");
			String input1=scanner1.nextLine();
			if(input1.equals("yes")){
				x=x-1;
				getnewhand();
			}
			else{
				x=1;
				peekhand();
			}
		}
		
	}
	/**
	 * sorts hand in numerical order using a bubble sort
	 */
	private void sorthand(){//sorts hand
		   boolean swap;
		   int temp;
		   do
		   {
		      swap = false;
		      for (int i = 0; i < (DIP - 1); i++)
		      {
		         if (hand[i] > hand[i + 1])
		         {
		            temp = hand[i];
		            hand[i] = hand[i + 1];
		            hand[i + 1] = temp;
		            swap = true;
		         }
		      }
		   } while (swap);
		}
	/**
	 * displays hand
	 */	
	private void peekhand(){//displays contents of hand
		String a="You rolled ";
		if(x==1){
			sorthand();
			a="Here is your sorted hand: ";
		}
		System.out.print(a +Arrays.toString(hand)+'\n');
		
	}
	
	void determinepicture(){
	}

	}
