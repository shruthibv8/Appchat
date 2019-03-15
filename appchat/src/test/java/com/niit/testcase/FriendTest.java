package com.niit.testcase;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.FriendDAO;

import com.niit.model.Friend;



public class FriendTest 
{
static FriendDAO friendDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		friendDAO=(FriendDAO)context.getBean("friendDAO");
	}
	
	//@Ignore
	@Test
	public void addFriend()
	{
		Friend friend=new Friend();
		friend.setStatus("Offline");
		
		  assertTrue("Problem in Adding Friend",friendDAO.addFriend(friend));
		    
}
}