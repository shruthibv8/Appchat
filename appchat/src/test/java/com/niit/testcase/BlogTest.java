package com.niit.testcase;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;

public class BlogTest 
{
	static BlogDAO blogDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		blogDAO=(BlogDAO)context.getBean("blogDAO");
	}
	@Ignore
	@Test
	public void addBlog()
	{
		Blog blog=new Blog();
		blog.setBlogName("Hibernate");
		blog.setBlogContent("Here you can find Good Content about Hibernate");
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(0);
		blog.setDislikes(0);
		blog.setStatus("Available");
	    blog.setUsername("shruthi");
	    
	    assertTrue("Problem in Adding the Blog",blogDAO.addBlog(blog));
	    
	}


}
