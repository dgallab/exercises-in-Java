package com.p1.stuff;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Date;
import com.p1.util.ConnFactory;


import com.p1.stuff.Approver.Request;
import com.p1.stuff.TRForm.eventType;
import com.p1.stuff.TRForm.gradingFormat;

public class Employee {
	
	public static ArrayList<Employee> all_employees = new ArrayList<Employee>();
	
	private String username; //this must be unique
	private String password;
	private String name;
	public double available = 1000;
	public double awarded = 0 ;
	
	public static ConnFactory cf=ConnFactory.getInst();
	
	//each employee has their own pile_of_forms
	public ArrayList<TRForm> pile_of_forms = new ArrayList<TRForm>(); 
	
	//each employee has their own set of requests from approvers. <Approver, String, false> is an unresolved request
	public ArrayList <Request> inbox = new ArrayList<Request>(); 
	
	//constructor
	public Employee(String username, String password, String name) throws SQLException {
		this.username = username;
		this.password = password;
		this.name = name;
		
		Connection conn=cf.getConn();
		Statement stmt=conn.createStatement();
		stmt.executeQuery("INSERT INTO EMPLOYEES (USERNAME,NAMEE,PASSWORDD, AVAILABLE,AWARDED) VALUES ('" + username + "', '"+ name + "', '" + password + "', " + available + ", "+ awarded + ')' );
	}
	
	//employee menu
	public static void options() throws SQLException {
		Scanner sc= new Scanner(System.in);
		Employee e = employeeLogin();
		boolean loggedin=true;
		while (loggedin) {
			System.out.println("Welcome: " +e.username +'\n'+'\n'
					+ "fill out a tuition reimbursement form [0]" + '\n' 
					+ "finish a course and upload grade [1]" + '\n'
					+"respond to an information request [2]" + '\n'
					+ "look at your forms [3] " + '\n'
					+ "let 1 year pass [4]" + '\n'
					+ "Exit [-1]");
			
			int input = sc.nextInt();
			
			switch (input) {
			
			//returns to system 
			case -1:
				loggedin=false;
				break;
			//fills out a TR form
			case 0:
				
				e.fillTRF();
				break;
			//finish a class
			case 1:
				if(e.pile_of_forms.size()==0) {
					System.out.println("no forms");
					break;
				}
				TRForm trf = e.selectForm();
				if(trf.grade != null) {
					System.out.println("grade has already been uploaded");
					break;
				}
				e.finishClass(trf);
				break;
			case 2:
				if(e.inbox.size()==0) {
					System.out.println("inbox is empty");
					break;
				}
				e.answerRequest();
				break;
			case 3:
				e.viewForms();
				break;
			//alter space time continuum
			case 4:
				yearHasPassed();
				break;
				
			}
		}	
	}
	
	public static Employee employeeLogin() throws SQLException {
		System.out.println("0->new employee, 1->returning employee");
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		int inp = sc.nextInt();
		System.out.println("enter username");
		String username = sc2.nextLine();
		
		
		if(inp == 0) {
			
			if (checkUsername(username)) {
				System.out.println("username is taken");
				return employeeLogin();
			}
			else {
			System.out.println("enter a password");
			String password = sc2.nextLine();
			System.out.println("enter your name");
			String name = sc2.nextLine();
			Employee e = new Employee(username, password, name);
			all_employees.add(e);
			return e;
			}
		}
		
		else {

			if (!(checkUsername(username))) {
				System.out.println("username does not exist");
				return employeeLogin();
			}
			else {
			
				System.out.println("enter password");
				String input = sc2.nextLine();
				System.out.println(input);
				Employee e = retrieveAccountInfo(username);
				if(input.equals(e.password))
					return e;
				else {
					System.out.println("incorrect password");
					return employeeLogin();
				}
			}
		}
	}
	
	public static void yearHasPassed() {
		for (Employee e:all_employees) {
			e.awarded = 1000 - e.available;
			e.available = 1000;
		}
		
	}
	
	public static boolean checkUsername(String new_username) {
		
		for (Employee e:all_employees) {
			if (e.username.equals(new_username))
				return true;
			
		}
		return false;
	}
	
	public static Employee retrieveAccountInfo(String username) {
		
		for (Employee e:all_employees) {
			if (e.username.equals(username))
				return e;
		}
		return all_employees.get(0);
	}
	
	//upon entering the initial values for the form on the web page, the TRF constructor is called
	//form is added to employee's pile
	public TRForm fillTRF() throws SQLException {
		
		long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
        
		TRForm form = new TRForm(this.username, "today",  "this is an example", 580, gradingFormat.Percentage, eventType.CPC);
		TRForm form1 = new TRForm(this.username, "beforefore",  "this is another example", 900, gradingFormat.SatisUnsatis, eventType.Seminar);
		this.pile_of_forms.add(form);
		return form;		
	}
	
	public void viewForms() {
		for(int i=0;i<this.pile_of_forms.size();i++) {
			System.out.println(i + " " + this.pile_of_forms.get(i));
		}
	}
	

public TRForm selectForm() {
		
		viewForms();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("[0->" + (this.pile_of_forms.size()-1) + "]");
		int x  = sc.nextInt() % pile_of_forms.size();
		return this.pile_of_forms.get(x);
	}
	
	public void answerRequest() {
		 for(int i=0;i<this.inbox.size();i++) {
			 System.out.println(i + " " + this.inbox.get(i));
		 }
		 Scanner sc = new Scanner(System.in);
		 System.out.println("[0->" + (this.inbox.size()-1) + "]");
		 int x  = sc.nextInt() % inbox.size();
		 
		 //choose a request
		 Request r = inbox.get(x);

		 //user response
		 String response="";
		 r.msg = response;
		 r.resolved = true;	 
	 }
	
	

	//enters grade after class has been finished
	public TRForm finishClass(TRForm trf) {
		
		Scanner es =new Scanner(System.in);
		switch(trf.gf) {
		
		case Percentage:
			System.out.println("Enter a percentage [0.00->100.00]");
			trf.grade = es.nextLine();
			break;	
		case ABCDE:
			System.out.println("Enter a letter grade [A,B,C,D,E]");
			trf.grade = es.nextLine();
			break;
		case SatisUnsatis :
			System.out.println("Enter a letter grade [S->satisfactory,U->unsatisfactory]");
			trf.grade = es.nextLine();
			break;
		case Presentation:
			System.out.println("Evaluate presentation [S->satisfactory,U->unsatisfactory]");
			trf.grade = es.nextLine();
			break;
			
			}
		
		return trf;
		}

	public String toString() {
		return (username+ " "+  name + " "+ available + " " + awarded ); 
	}
	
		
	
				
}


