
package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.BlogCommentDAO;

import com.niit.model.BlogComment;

@Repository("blogcommentDAO")
@Transactional

public class BlogCommentDAOImpl implements BlogCommentDAO 
{
	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addBlogComment(BlogComment blogcomment)
	{
		try
		{
			sessionFactory.getCurrentSession().save(blogcomment);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Adding BlogComment"+e);
		return false;
		}
	}

	
	public boolean deleteBlogComment(BlogComment blogcomment) 
	{
		try
		{
		sessionFactory.getCurrentSession().remove(blogcomment);
		return true;
	}
	catch(Exception e)
		{
		System.out.println("Exception Arised:Deleting BlogComment"+e);
		return false;
	}
	}
	
	public List<BlogComment> getAllBlogComments()
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from BlogComment");
		@SuppressWarnings("unchecked")
		List<BlogComment> listBlogComment=(List<BlogComment>)query.list();
		session.close();
		return listBlogComment;
	
	}

}
