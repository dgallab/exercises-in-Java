package com.p1.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.p1.stuff.Approver.DHead;
import com.p1.stuff.Approver.DSupervisor;
import com.p1.stuff.Approver.Role;

public class ApproverTest {

	@Test
	public void test() throws SQLException {
		
		DHead dh=  new DHead("a department head","dhead123","Philip", Role.DHead);
		DSupervisor ds = new DSupervisor("a department supervisor","dsup123","James",Role.DSup);
		Assert.assertEquals(ds.inbox.size(),0);
		Assert.assertEquals(dh.inbox.size(),0);
		
		//this should not add to the dh inbox
		ds.requestInfo(dh);
		
		//this should  add to the ds inbox
		dh.requestInfo(ds);
		
		Assert.assertEquals(ds.inbox.size(),1);
		Assert.assertEquals(dh.inbox.size(),0);
		
		
		
	}

}
