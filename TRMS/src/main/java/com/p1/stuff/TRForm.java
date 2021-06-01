package com.p1.stuff;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


import com.p1.util.ConnFactory;
import java.util.Date;


public class TRForm {

	public static enum gradingFormat {Percentage, ABCDE, SatisUnsatis, Presentation};
	public static enum eventType {Ucourse, Seminar, CPC, Cert, Technical, Other};
	public String username;
	public String name;
	public String date;
	public double cost;
	public gradingFormat gf;
	public eventType et;  
	public String grade;  			//not known at time of form creation	
	public boolean pass;  			//not known at time of form creation (false)	
	public double reimbursement;   //not known at time of form creation (0)
	
	//the required signatures (all false atofc)
	public boolean approvalDS, approvalBenCo, approvalDH; 
	public String DSSig,DHeadSig,BenCoSig;           
	
	public boolean submitted; 
	
	public static ConnFactory cf=ConnFactory.getInst();
	
	
	
public TRForm(String username, String date,  String description, double cost, gradingFormat gf, eventType et) throws SQLException {
		this.username = username;
		this.date = date; 
		this.cost = cost;
		this.gf = gf;
		this.et = et;
		Connection conn=cf.getConn();
		Statement stmt=conn.createStatement();
		stmt.executeQuery("INSERT INTO TRFORMS (NAMEE, DATEE, COSTT, GF, ET, GRADE, PASS, REIMBURSEMENT, DSSIG, DHSIG, BENCOSIG, SUBMITTED) VALUES ('" + username + "', '" + date +"', " + cost +",'"+ gf +"', '" + et+"', '" + grade + "', '" + pass +"', "+reimbursement +", "+DSSig +", " + DHeadSig + ", " + BenCoSig +", '" + submitted +"')");
		
		
	}


public String toString() {
	return ("Name: " + name+ " " + "GRADE: " + grade + " "+ "PASSED: " + pass + " "+ "COST: " +cost+ " "+ "REIMBURSEMENT: " + reimbursement + " DS sig: " + DSSig + " DH sig: " + DHeadSig + " BenCo sig: "+BenCoSig ); 
}

//takes a form, checks grade and grading format to determine pass
public void determinePass() {
	
	
	
	switch(gf) {
	
	case Percentage:
		double p = Double.valueOf(grade);
		if (p > 70.00) // 
			pass = true;
		break;	
	case ABCDE:
		if (!(grade.equals("E"))) 
			pass = true;
		break;
	case SatisUnsatis :
		if (grade.equals("S")) 
			pass = true;
		break;
	case Presentation:
		if (grade.equals("S")) 
			pass = true;
		break;
		
		}
	
	}
	
//takes a form, checks pass and event type to determine reimbursement

public TRForm calcReimbursement() {
	
	if(pass) {
	
	switch(et) {
	
	case Ucourse:
		reimbursement = .8 * cost;
		break;
	case Seminar:
		reimbursement = .75 * cost;
		break;
	case CPC:
		reimbursement = .6 * cost;
		break;
	case Cert:
		reimbursement = cost;
		break;
	case Technical:
		reimbursement = .9 * cost;
		break;
	case Other:
		reimbursement = .3 * cost;
	
		}
	}
return this;
}
	
	

	
	
}
