package com.niit.testcase;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.ForumCommentDAO;

import com.niit.model.ForumComment;

public class ForumCommentTest
{
	static ForumCommentDAO forumcommentDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		forumcommentDAO=(ForumCommentDAO)context.getBean("forumcommentDAO");
	}

	@Ignore
	@Test
	public void addForumComment()
	{
		ForumComment forumcomment=new ForumComment();
		
		forumcomment.setForumId(200);
		forumcomment.setUsername("shruthi");
	    forumcomment.setComment("Thanks for the forumcomment");
	    forumcomment.setCommentDate(new java.util.Date());
	    assertTrue("Problem in Adding the ForumComment",forumcommentDAO.addForumComment(forumcomment));
	    
	}

}
