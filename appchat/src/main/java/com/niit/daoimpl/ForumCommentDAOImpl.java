package com.niit.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.ForumCommentDAO;

import com.niit.model.ForumComment;
@Repository("forumcommentDAO")
@Transactional
public class ForumCommentDAOImpl implements ForumCommentDAO
{
	@Autowired
	SessionFactory sessionFactory;
	
	
	public boolean addForumComment(ForumComment forumcomment) 
	{
		
		try
		{
			sessionFactory.getCurrentSession().save(forumcomment);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Adding ForumComment"+e);
		return false;
		}
	}

	public boolean deleteForumComment(ForumComment forumcomment) 
	{
		try
		{
		sessionFactory.getCurrentSession().remove(forumcomment);
		return true;
	}
	catch(Exception e)
		{
		System.out.println("Exception Arised:Deleting ForumComment"+e);
		return false;
	}
	}

	public List<ForumComment> getAllForumComments() 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from ForumComment");
		@SuppressWarnings("unchecked")
		List<ForumComment> listForumComment=(List<ForumComment>)query.list();
		session.close();
		return listForumComment;
	
	}

}
