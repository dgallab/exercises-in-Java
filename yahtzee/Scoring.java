

package yahtzee;
/**
* @author Daniel Gallab
* @version 1.1
* @since 1.0
* <h3>The Scoring class</h3> 
* The Scoring class looks at the hand to determine all possible scoring
* possibilities, then stores those possibilities as boolean values
* each boolean value represents a section in the scoring line, except 
* for the upper scorecard, which is calculated differently
* In assignment 2 and 3, some functions change in order to
* adapt to various dice and die face combinations
 */

public class Scoring extends Hand {
	 /**
	  *boolean values correspond to different lower scoring options 
	  *<p>
	  */	 	
	
			boolean a = maxofakind()>=3;//3 of a kind *
			boolean b = maxofakind()>=4;//4 of a kind *
			boolean c = fullHouse();// full house
			boolean d = smallorlargestraight()>=3;//small straight
			boolean e = smallorlargestraight()>=4;//large straight
			boolean f = maxofakind()>=5; //yahtzee
			int g = totalallDice();
			
			/**
			 * @returns the highest frequency of a die value
			 */	
			private int maxofakind(){
				 int m = 0;
				 int c;
				 for (int dieValue = 1; dieValue <=6; dieValue++)
				    {
				        c = 0;
				        for (int diePosition = 0; diePosition < DIP; diePosition++){
				            if (hand[diePosition] == dieValue)
				                c++;
				        }
				        if (c > m)
				            m = c;
				    }
				    return m;
			}
			/**
			 * @returns the length of largest numerically ordered sub-array/array
			 */	
			private int smallorlargestraight(){
				int x=0;
				int y=x;
				for(int i=0;i<DIP-1;i++){
					if (hand[i]==hand[i+1]-1)
						x++;
					else if ((hand[i]!=hand[i+1]) && (x>y)){
						y=x;
						x=0;
					}
				}
				if(x>y)
					return x;
				else
					return y;
			}
			/**
			 * successfully accomplished with one for loop, the new fullHouse 
			 * function works with different numbers of dice in play
			 * @returns True if the hand contains at least 2 identical elements and at least
			 * 3 other identical elements, False otherwise
			 */	
			private boolean fullHouse(){
				int u=0;//2ofakind
				int v=0;//3ofakind

				for(int i=0;i<DIP-2;i++){
					if (hand[i]==hand[i+1]){
						u++;
					}
					if(hand[i]==hand[i+2]){
						u--;
						v++;
					}
				}
				if(v+u>1)
					return true;
				else 
					return false;
				}
				
			/**
			 * @returns total   Sum of every die value in the hand
			 */	
			
			public int totalallDice(){//used for different score sections
				int total=0;
				for(int i=0;i<DIP;i++){
					total=total+hand[i];
					}
				return total;

		}
			/**
			 * this function is ran F number of times in the Scoreline class
			 * <p>
			 * @param i   Takes an integer 
			 * @returns total   The sum of die values equal to i
			 */	
			public  int USscoring(int i){
				int total=0;
				for(int x=0;x<DIP;x++){
					if(hand[x]==i)
						total=total+i;
				}
					return total;
				
				}
	}


