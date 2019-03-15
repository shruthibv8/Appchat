package com.niit.testcase;

import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.dao.BlogCommentDAO;
import com.niit.model.BlogComment;


public class BlogCommentTest 
{
static BlogCommentDAO blogcommentDAO;

@BeforeClass
public static void initialize()
{
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("com.niit");
	context.refresh();
	blogcommentDAO=(BlogCommentDAO)context.getBean("blogcommentDAO");
}


@Ignore
@Test
public void addBlogComment()
{
	BlogComment blogcomment=new BlogComment();
	
	blogcomment.setBlogId(412);
	blogcomment.setUsername("shruthi");
    blogcomment.setComment("Thanks for the comment");
    blogcomment.setCommentDate(new java.util.Date());
    assertTrue("Problem in Adding the BlogComment",blogcommentDAO.addBlogComment(blogcomment));
    
}


}
