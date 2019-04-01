package com.niit.daoimpl;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;
@Repository
@Transactional
public abstract class BlogDAOImpl implements BlogDAO 
{
	@Autowired
	private SessionFactory sessionFactory;
	
		public void addBlog(Blog blog)
		
		{
		Session session=sessionFactory.getCurrentSession();
		session.save(blog);
	}
		
	public List<Blog> getBlogsWaitingForApproval()
	{
		Session session=sessionFactory.getCurrentSession();
		
		Query query=session.createQuery("from BlogPost where approved=false");
		List<Blog> blogForApproval=query.list();
		return blogForApproval;
	}
	public List<Blog> getBlogsApproved()
	{
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Blog where approved=true");
		List<Blog> blogApproved=query.list();
		return blogApproved;
	}
	public Blog getBlog(int blogId) 
	{
		Session session=sessionFactory.getCurrentSession();
		Blog blog=(Blog)session.get(Blog.class,blogId);
		return blog;
	}
	public void approveBlog(int blogId)
	{
		Session session=sessionFactory.getCurrentSession();
		Blog blog=(Blog)session.get(Blog.class,blogId);
		blog.setApproved(true);
		session.update(blog);
	}
	public void rejectBlog(int blogId) 
	{
		Session session=sessionFactory.getCurrentSession();
		Blog blog=(Blog)session.get(Blog.class,blogId);
		session.delete(blog);
		
	}
	public void updateBlog(Blog blog) 
	{
		Session session=sessionFactory.getCurrentSession();
		 session.update(blog);
	}
	
	public List<Blog> getBlogByEmail(String user_email) 
	{
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where approved=? and postedBy.email=?");
		query.setBoolean(0, false);
		query.setString(1, user_email);
		List<Blog> blog=query.list();
		return blog;
	}
	public List<Blog> getBlogByEmail1(String email) 
	{
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where approved=? and postedBy.email=?");
		query.setBoolean(0, true);
		query.setString(1, email);
		List<Blog> blog1=query.list();
		return blog1;
	}



}

