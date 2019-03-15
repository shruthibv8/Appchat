package com.niit.testcase;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserDAO;
import com.niit.model.User;



public class UserTest
{
	static UserDAO userdao;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userdao=(UserDAO)context.getBean("userDAO");
	}
	//@Ignore
	@Test
	public void registerUser()
	{
		User user = new User();
		user.setUsername("shruthi");
		user.setPassword("shruthi");
		user.setRole("student");
		user.setStatus("Online");
		user.setEmailId("shruthi.bv@gmail.com");
		user.setIsOnline("Online");
		assertTrue("Problem in regestering user", userdao.registerUser(user));
	}

}
