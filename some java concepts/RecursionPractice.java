import java.util.ArrayList;

public class RecursionPractice  {

static ArrayList<Integer> fibArray = new ArrayList<Integer>();

	public static void main(String[] args) {
		RecursionPractice rp = new RecursionPractice();
		//System.out.println("final element is " + rp.fibI(6)); //custom	
		//System.out.println(fibArray);
		
		System.out.println(rp.EuclideanAlg(50,60));
	}
	
	public int fibR(int m){
	if(m<=1) {
		return m;
	}
	else {
		
		int f = (fibR(m-1) + fibR(m-2));
		//System.out.println(f);
		return(f);
		
		}
	}
	
	public int fibI(int m) {
		int f0 = 0;
		int f1 = 1;
		int s = 0;
		fibArray.add(f0);
		fibArray.add(f1);
		for(int i=0;i<m-1;i++) {
			s =f0 + f1;
			f0=f1;
			f1 =s;
			fibArray.add(s);
		}
		return s;
	}
	
	public int EuclideanAlg(int a, int b) {
		
		int q =1 ;
		while(b*q < a) {
			q=q+1;
		}
		q=q-1;
		int r=a-b*q;
		
		return r;
	}
	
}

