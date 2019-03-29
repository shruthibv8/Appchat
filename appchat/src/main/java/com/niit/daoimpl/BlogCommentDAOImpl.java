
package com.niit.daoimpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.BlogCommentDAO;
import com.niit.model.BlogComment;

@Repository
@Transactional

public abstract class BlogCommentDAOImpl implements BlogCommentDAO 
{
	@Autowired
	private SessionFactory sessionFactory;
		public void addBlogComment(BlogComment blogComment)
		{
			Session session=sessionFactory.getCurrentSession();
			session.save(blogComment);
		}
		public List<BlogComment> getAllBlogComments(int blogPostId) 
		{
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogComment where blogPost.blogPostId=:blogId");
		query.setInteger("blogId", blogPostId);
		List<BlogComment> comments=query.list();
		return comments;
		}
		@Transactional
	    public void updateBlogComment(BlogComment blogComment)
	    {
	    	/*final Session session = this.sessionFactory.getCurrentSession();
	        final BlogComment comment =(BlogComment)session.get(BlogComment.class, blogComment.getCommentId(),LockMode.PESSIMISTIC_WRITE);*/
			Session session=sessionFactory.getCurrentSession();
			int commentId1=blogComment.getCommentId();
	    	System.out.println(commentId1);
	    	String commentTxt1=blogComment.getCommentTxt();
	    	Date commentedOn1=blogComment.getCommentedOn();
	    	System.out.println(commentedOn1);
	    	Query query=session.createQuery("update BlogComment set commentTxt=?,commentedOn=? where commentId=?");
	    	query.setString(0, commentTxt1);
	    	query.setParameter(1, commentedOn1);
	    	query.setInteger(2, commentId1);
	    	int res =query.executeUpdate();
	    	session.flush();
	    	
	    	/*final Session session = this.sessionFactory.getCurrentSession();
	        final BlogComment comment =(BlogComment)session.get(BlogComment.class, blogComment.getCommentId(),LockMode.PESSIMISTIC_WRITE);
	        //session.refresh(comment);
	        blogComment.setCommentedOn(new Date());
	        blogComment.setCommentedBy(commentedBy);
	        session.update(blogComment);
	        session.flush();
	    	//session.update(blogComment);
	*/    }
	    public void deleteBlogComment(int commentId)
	    {
	    	Session session=sessionFactory.getCurrentSession();
	    	BlogComment blogComment=(BlogComment)session.get(BlogComment.class, commentId);
	    	session.delete(blogComment);
	    }
	    public BlogComment getBlogCommentById(int blogCommentId)
	    {
	    	System.out.println("BlogCommentById");
	    	Session session=sessionFactory.getCurrentSession();
	    	BlogComment blogComment=(BlogComment)session.get(BlogComment.class, blogCommentId);
			return blogComment;
	    }

	
	
	
	
}
