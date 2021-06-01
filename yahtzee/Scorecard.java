package yahtzee;

import java.util.Arrays;

import java.util.Scanner;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;

public class Scorecard extends Scoreline {
	/**

	* @author Daniel Gallab
	* @version 1.1
	* @since 1.0
	* <h1>The ScoreCard class</h1> 
	* asks the user where he/she wants to score, and writes a file
	* showing the scorecard after this decision
	 */
	
	
	//f is for number of faces, which determines the size of the 
	//upper scorecard, plus 7(the size of the lower scorecard
	
	/**
	 * <p>
	  calls choosecard() and writeontofile()
	  */	 	
	Scorecard() {
	}
	/**
	 * <p>
	  *writes the results onto a file
	  */	 	
	
	/**
	 * <p>
	 asks user whether he/she wants to score on the upper or lower card
	  */	 	
	 int choosecard(){
		Scanner scanner=new Scanner(System.in);
		System.out.print("Score on the upper or lower scorecard? (Enter u for upper, l for lower)");
		String input = scanner.nextLine();
		if(input.equals("u"))
			return 0;
		if(input.equals("l"))
			return 1;
		return 0;
	}
	/**
	 * <p>
	 calculates total
	  */	 	
	 int calculate_total(int[] ca) {
	
		int b = 0;
		int total = 0;
		for(int x=0;x<F+8;x++){
			if (ca[x]!= -1){
				total=total+ca[x];
				if (x<F)
					b=b+ca[x];
			}
		}
			
		
		if (b>62){
			System.out.print("Bonus:  "+"yes");
			return total+50;
		}
		System.out.print("Bonus:  "+"no"+ '\n');
		return total;
		
	}
	/**
	 * <p>
	 asks user to select an option from the upper scorecard
	  */	 	
	 int[] Uchooseline(int[] ca){
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter a number between 1 and F, designating your score option (1 to add all ones, etc)");
		String input = scanner.nextLine();
		int x = Integer.parseInt(input);
		int t = USscoring(x);
		if (ca[x-1] != -1){
			System.out.print("Already chosen- choose another score option");
			return Uchooseline(ca);
		}
		else
			ca[x-1]=t;
		return ca;
	}
	/**
	 * <p>
	 asks user to select an option from the lower scorecard
	  */	 	
	 int[] Lchooseline(int[] ca){
		
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter 3K, 4K, FH, SS, LS, Y, C, designating your scoring option");
		String input = scanner.nextLine();
		if(input.equals("3K")){
			if (ca[F]!=-1){
				System.out.print("Already chosen-choose another option" + '\n');
				return Lchooseline(ca);
			}
			if(a)
				ca[F]=totalallDice();
			else
				ca[F]=0;
		}
		
		if(input.equals("4K")){
			if (ca[F+1]!=-1){
				System.out.print("Already chosen-choose another option" + '\n');
				return Lchooseline(ca);
			}
			if(b)
				ca[F+1]=totalallDice();
			else
				ca[F+1]=0;
		}
		
		if(input.equals("FH")){
			if (ca[F+2]!=-1){
				System.out.print("Already chosen-choose another option" + '\n');
				return Lchooseline(ca);
			}
			if(c)
				ca[F+2]=25;
			else
				ca[F+2]=0;
		}
		if(input.equals("SS")){
			if (ca[F+3]!=-1){
				System.out.print("Already chosen-choose another option" + '\n');
				return Lchooseline(ca);
			}
			if(d)
				ca[F+3]=30;
			else
				ca[F+3]=0;
		}
		if(input.equals("LS")){
			if (ca[F+4]!=-1){
				System.out.print("Already chosen-choose another option" + '\n');
				return Lchooseline(ca);
			}
			if(e)
				ca[F+4]=40;
			else
				ca[F+4]=0;
		}
		if(input.equals("Y")){
			if (ca[F+5]!=-1){
				System.out.print("Already chosen-choose another option" + '\n');
				return Lchooseline(ca);
			}
			if(f)
				ca[F+5]=50;
			else
				ca[F+5]=0;
		}
		if(input.equals("C")){
			if (ca[F+6]!=-1){
				System.out.print("Already chosen-choose another option" + '\n');
				return Lchooseline(ca);
			}
			ca[F+6]=g;
		}
		return ca;
	}
	 
	 public  int USscoring(int i){
			int total=0;
			for(int x=0;x<DIP;x++){
				if(hand[x]==i)
					total=total+i;
			}
				return total;
			
			}
	 /**
	  *main
	  */	 	
	public void displaysc(int[] cardarray){
		for(int x=0;x<F;x++)
	    	System.out.print((x+1)+"'s:  "+cardarray[x]+'\n');
	    System.out.print("3 of a kind:  "+cardarray[F]+'\n');
	    System.out.print("4 of a kind:  "+cardarray[F+1]+'\n');
	    System.out.print("Full House:  "+cardarray[F+2]+'\n');
	    System.out.print("Small Straight:  "+cardarray[F+3]+'\n');
	    System.out.print("Large Straight:  "+cardarray[F+4]+'\n');
	    System.out.print("Yahtzee:  "+cardarray[F+5]+'\n');
	    System.out.print("Chance:  "+cardarray[F+6]+'\n'+'\n');
	    
	    int t = calculate_total(cardarray);
	    System.out.print("Total:  "+t);
		
	}
	public static void main(String[] args){//the main function
		//ButtonFrame b = new ButtonFrame();
	   //EventQueue.invokeLater(runnable);
		
		Scanner scanner=new Scanner(System.in);
		Die d = new Die();
		
		d.changeConfig();
		
		int[] cardarray= new int[F+8];
		Arrays.fill(cardarray, -1);
		while(playagain){
			
			Scorecard sc = new Scorecard();
			if (sc.choosecard()==0)
				 sc.Uchooseline(cardarray);
			else
				 sc.Lchooseline(cardarray);
			System.out.print("Enter 'yes' to roll again or 'p' to look at current scorecard");
			String input = scanner.nextLine();
			if (input.equals("p")){
				sc.displaysc(cardarray);
				System.out.print('\n'+"Do you want to roll again?");
				input = scanner.nextLine();
			}
			if(!(input.equals("yes"))){
				playagain=false;
			    //System.out.print(Arrays.toString(cardarray)+'\n');
			    sc.displaysc(cardarray);   
			}
			    
			
		}
		
		}
	}
	
