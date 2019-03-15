package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.ForumDAO;
import com.niit.model.Blog;
import com.niit.model.Forum;


@Repository("forumDAO")
@Transactional
public class ForumDAOImpl implements ForumDAO {
	@Autowired
	SessionFactory sessionFactory;

	public boolean addForum(Forum forum) {
		try
		{
			sessionFactory.getCurrentSession().save(forum);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Adding Forum"+e);
		return false;
	}
	}

	public boolean deleteForum(Forum forum) {
		try
		{
		sessionFactory.getCurrentSession().remove(forum);
		return true;
	}
	catch(Exception e)
		{
		System.out.println("Exception Arised:Deleting Forum"+e);
		return false;
	}
	}

	public boolean updateForum(Forum forum) {
		try
		{
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Updating Forum"+e);
		return false;
	}
	}
	public Forum getForum(int ForumId) {
		Session session=sessionFactory.openSession();
		Forum forum=session.get(Forum.class, ForumId);
		session.close();
		return forum;
		
	}

	public List<Forum> getAllForums() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Forum");
		@SuppressWarnings("unchecked")
		List<Forum> listForum=(List<Forum>)query.list();
		session.close();
		return listForum;
	
	}

	public boolean approveForum(Forum forum) {
		try
		{
			forum.setStatus("A");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Approve Forum:"+e);
		return false;
	}
	}

	public boolean rejectForum(Forum forum) {
		try
		{
			forum.setStatus("NA");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Reject Forum"+e);
		return false;
		}
	}

}
