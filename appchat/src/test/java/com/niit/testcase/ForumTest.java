package com.niit.testcase;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.niit.dao.ForumDAO;
import com.niit.model.Forum;

public class ForumTest {
	static ForumDAO forumDAO;
	@BeforeClass
	public static void initialize()
	
		{
			AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
			context.scan("com.niit");
			context.refresh();
			forumDAO=(ForumDAO)context.getBean("forumDAO");
		}
	@Ignore
		@Test
		public void addForum()
		{
			Forum forum=new Forum();
			forum.setForumName("adv java");
			forum.setForumContent("Here you can discuss doubts about advanced java");
			forum.setCreateDate(new java.util.Date());
			forum.setStatus("Available");
		    forum.setUsername("shruthi");
		    
		    assertTrue("Problem in Adding the Forum",forumDAO.addForum(forum));
		    
		}


	}

