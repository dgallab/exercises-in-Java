package com.p1.stuff;

import java.util.logging.Logger;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.p1.util.Log2File;
import com.p1.util.ConnFactory;


//any approver may requestInfo from an employee
public abstract class Approver {
		
	public static ArrayList <Approver> all_approvers = new ArrayList<Approver>(); 
	
	public ArrayList<Request> inbox = new ArrayList<Request>(); 
	
	public static ConnFactory cf=ConnFactory.getInst();
	
	public static enum Role {DSup(0), DHead(1), BenCo(2);
	
	private int level;
	
	Role(int i) {
		this.level = i;
		// TODO Auto-generated constructor stub
	}};
	
	public String username; //this must be unique
	public String password;
	public String name;
	Role role ;
	
	public Approver(String username, String password, String name, Role role) throws SQLException {
		
	this.username=username;
	this.password=password;
	this.name=name;
	this.role = role;
	
	Connection conn=cf.getConn();
	Statement stmt=conn.createStatement();
	stmt.executeQuery("INSERT INTO APPROVERS (USERNAME,NAMEE,PASSWORDD, ROLEE) VALUES ('" + username + "', '"+ name + "', '" + password + "', '" + role + "')" );
	
	}
	
	
	abstract TRForm approveTRF(TRForm trf);
	
	public void requestInfo(Employee e) {
		String msg="give me info";
		Request r = new Request(this, msg, false);
		e.inbox.add(r);
				
	}
	
	public void requestInfo(Approver a) {
		int target_level = a.role.level;
		int current_level = this.role.level;
		if (target_level >= current_level) {
			System.out.println("security level not high enough");
			Log2File.logThis("info", "prohibited communication");	
			return;
		}
		String msg="give me info or i kill ur family";
		Request r = new Request(this, msg, false);
		a.inbox.add(r);
	}
	
	public static void options() throws SQLException {
		
		Scanner sc= new Scanner(System.in);
		Approver a = approverLogin();
		boolean loggedin=true;
		
		while (loggedin) {
			System.out.println("Welcome: " +a.username +'\n'+'\n'  
					+ "approve a tuition reimbursement form [0]" + '\n' 
					+ "request information from an employee [1]" + '\n'
					+ "request information from an approver [2]"  + '\n'
					+ "respond to an information request [3]" + '\n'
					+ "confirm passing or failing grade, and reimbursement amount  [4]" + '\n'
					+ "complete transaction  [5]" + '\n'
					+ "alter reimbursement amount  [6]" + '\n'
					+ "Exit [-1]");
			
			int input = sc.nextInt();
			
			switch (input) {
			
			//returns to system 
			case -1:
				loggedin=false;
				break;
			//approve an employee's TRF 
			case 0:
				if(Employee.all_employees.size() == 0 ) {
					System.out.println("no employees");
					break;
				}
				Employee e = a.searchEmployees(); 
				
				if(e.pile_of_forms.size()==0) {
					System.out.println("no forms");
					break;
				}
				TRForm trf = a.selectForm(e);
				a.approveTRF(trf);
				break;
			//request info from an employee
			case 1:
				if(Employee.all_employees.size() == 0 ) {
					System.out.println("no employees");
					break;
				}
				Employee e1 = a.searchEmployees(); 
				a.requestInfo(e1);	
				break;
			//request info from another approver
			case 2:
				Approver a2 = searchApprovers();
				a.requestInfo(a2);
				break;
			case 3:
			//respond to a request
				if(a.inbox.size()==0) {
					System.out.println("inbox is empty");
					break;
				}
				Request r =a.selectRequest(a.inbox);
				a.answerRequest(r);
				break;
			//determine pass, then calculates reimbursement
			case 4:
				if(a.role != Role.BenCo) {
					System.out.println("Only a BenCo may approve reimbursement");
					break;
				}
				if(Employee.all_employees.size() == 0 ) {
					System.out.println("no employees");
					break;
				}
				Employee e2 = a.searchEmployees(); 
				if(e2.pile_of_forms.size()==0) {
					System.out.println("no forms");
					break;
				}
				TRForm trf1 = a.selectForm(e2);
				if(trf1.grade == null) {
					System.out.println("grade has not been uploaded yet");
					break;
				}
				trf1.determinePass();
				trf1.calcReimbursement();
				break;
			
			//completeTransaction
			case 5:
				if(a.role != Role.BenCo) {
					System.out.println("Only a BenCo may approve reimbursement");
					break;
				}
				
				if(Employee.all_employees.size() == 0 ) {
					System.out.println("no employees");
					break;
				}
				Employee e3 = a.searchEmployees();
				if(e3.pile_of_forms.size() == 0 ) {
					System.out.println("no forms");
					break;
				}
				
				TRForm trf2 = a.selectForm(e3);
				a.completeTransaction(e3, trf2);
				break;
			//alter reimbursement amount
			case 6:
				if(Employee.all_employees.size() == 0 ) {
					System.out.println("no employees");
					break;
				}
				Employee e4 = a.searchEmployees();
				if(e4.pile_of_forms.size() == 0 ) {
					System.out.println("no forms");
					break;
				}
				
				TRForm trf3 = a.selectForm(e4);
				a.alterReimbursement(e4, trf3);		
			}
		}
			
	}

public void alterReimbursement(Employee e,TRForm trf) {
	Scanner sc1 = new Scanner(System.in);
	System.out.println("Change current reimbursement amount to: ");
	trf.reimbursement = sc1.nextDouble();
	requestInfo(e);	
}
	

public void completeTransaction(Employee e, TRForm trf) {
	if(trf.submitted == true) {
		System.out.println("form has already been submitted");
		return;
	}
		
	boolean ready = true;
	if(trf.grade == null) {
		System.out.println("grade has not been uploaded");
		ready=false;
	}
	if(trf.approvalDS == false) {
		System.out.println("missing department supervisor's signature");
		ready = false;
	}
	if(trf.approvalDH == false) {
		System.out.println("missing department head's signature");
		ready = false;
	}
	if(trf.approvalBenCo == false) {
		System.out.println("missing benefit coordinator's signature");
		ready=false;
	}
	
	if(ready) {
		
		e.available = e.available - trf.reimbursement;
	
		if(e.available < 0) {
			System.out.println("reimbursement exceeds available. Is this okay?");
			Scanner sc = new Scanner(System.in);
			if (sc.nextLine().equals("n")){
				e.available = 0;
			}
		trf.submitted = true;
		}
		
	}	
}

public  Employee searchEmployees() {
		
		for(int i=0;i<Employee.all_employees.size();i++) {
			System.out.println(i + " " + Employee.all_employees.get(i));
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("[0->" + (Employee.all_employees.size()-1) + "]");
		int x  = sc.nextInt() % Employee.all_employees.size();
		return Employee.all_employees.get(x);
		
	}

public static Approver searchApprovers() {
	
	for(int i=0;i<all_approvers.size();i++) {
		System.out.println(i + " " + all_approvers.get(i));
	}
	Scanner sc = new Scanner(System.in);
	System.out.println("[0->" + (all_approvers.size()-1) + "]");
	int x  = sc.nextInt() % all_approvers.size();
	return all_approvers.get(x);
}

public TRForm selectForm(Employee e) {
	
	for(int i=0;i<e.pile_of_forms.size();i++) {
		System.out.println(i + " " + e.pile_of_forms.get(i));
	}
	Scanner sc = new Scanner(System.in);
	System.out.println("[0->" + (e.pile_of_forms.size()-1) + "]");
	int x  = sc.nextInt() % e.pile_of_forms.size();
	return e.pile_of_forms.get(x);	
}
	

	
	public static Approver approverLogin() throws SQLException {
		
		System.out.println("0->new approver, 1->returning approver");
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		int inp = sc.nextInt();
		System.out.println("enter username");
		String username = sc2.nextLine();
		
		//new user
		if(inp == 0) {
			
			if (checkUsername(username)) {
				System.out.println("username is taken");
				return approverLogin();
			}
			else {
			System.out.println("enter a password");
			
			String password = sc2.nextLine();
			String name = "generic";
			if(password.equals("benco123")) {
				BenCo bc=  new BenCo(username,password,name,Role.BenCo);
				all_approvers.add(bc);
				return bc;
			}
			else if (password.equals("dhead123")){
				DHead dh=  new DHead(username,password,name,Role.DHead);
				all_approvers.add(dh);
				return dh;
			}
			
			else if (password.equals("dsup123" )) {
				DSupervisor ds=  new DSupervisor(username,password,name,Role.DSup);
				all_approvers.add(ds);
				return ds;
			}
			else {
				System.out.println("invalid password");
				return approverLogin();
			}
		}
	}
	//returning user
		else {

			if (!(checkUsername(username))) {
				System.out.println("username does not exist");
				return approverLogin();
			}
			else {
				System.out.println("enter password");
				String input = sc2.nextLine();
				Approver a = retrieveAccountInfo(username);
				if(input.equals(a.password))
					return a;
				else {
					System.out.println("incorrect password");
					return approverLogin();
				}
			}
		}
	}
	
	public static boolean checkUsername(String new_username) {
		
		for (Approver a:all_approvers) {
			if (a.username.equals(new_username))
				return true;
		}
		return false;
	}
	
	public static Approver retrieveAccountInfo(String username) {
		
		for (Approver a:all_approvers) {
			if (a.username.equals(username))
				return a;
		}
		return all_approvers.get(0);
	}
	

	
	 public void answerRequest(Request r) {
		 String response="";
		 r.msg = response;
		 r.resolved = true;	
		}
	 
	 public Request selectRequest(ArrayList<Request> inbox) {
		 for(int i=0;i<inbox.size();i++) {
			 System.out.println(i + " " +inbox.get(i)); 
		 }
		 //choose a request
		 Scanner sc = new Scanner(System.in);
		 int x = sc.nextInt();
		 Request r = inbox.get(x);
		 return r;
		 	 
	 }
	

	public class Request{ 
		public Approver entity;
		public String msg;
		public boolean resolved = false;
		
		public Request (Approver a, String s, boolean b) {
			this.entity = a;
			this.msg = s;
			this.resolved = b;
		}
			
	}
	
	public String toString() {
		return (username+ " "+  name + " "+ role  ); 
	}
//subclasses
	public static class DSupervisor extends Approver{
	 
	public DSupervisor(String username, String password, String name, Role r) throws SQLException {
		super(username, password, name, Role.DSup);
		// TODO Auto-generated constructor stub
	}

	public TRForm approveTRF(TRForm trf) {
			
		trf.approvalDS = true;
		trf.DSSig = this.username;
		return trf;
		}  
 }
 
  public static class DHead extends Approver{
	 
	 public DHead(String username, String password, String name, Role r) throws SQLException {
		super(username, password, name, Role.DHead);
		// TODO Auto-generated constructor stub
	}

	public TRForm approveTRF(TRForm trf) {
			
		trf.approvalDH = true;
		trf.DHeadSig = this.username;
		return trf;
			}
	 	 
 }
 

static class BenCo extends Approver{
	 
		public BenCo(String username, String password, String name, Role r) throws SQLException {
			super(username, password, name, Role.BenCo);
		// TODO Auto-generated constructor stub
	}

		public TRForm approveTRF(TRForm trf) {
			trf.approvalBenCo = true;
			trf.BenCoSig = this.username;
			return trf;
		}
 }
		
	
}