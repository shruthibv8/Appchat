package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;

@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {
	@Autowired
	SessionFactory sessionFactory;

	
	public boolean addBlog(Blog blog) {
		try
		{
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Adding Blog"+e);
		return false;
	}
	}

	
	public boolean deleteBlog(Blog blog) {
	
			try
			{
			sessionFactory.getCurrentSession().remove(blog);
			return true;
		}
		catch(Exception e)
			{
			System.out.println("Exception Arised:Deleting Blog"+e);
		return false;
			}
	}

	
	public boolean updateBlog(Blog blog) {
		try
		{
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Updating Blog"+e);
		return false;
	}
	}
	
	public Blog getBlog(int blogId) {
		Session session=sessionFactory.openSession();
		Blog blog=session.get(Blog.class, blogId);
		session.close();
		return blog;
	}

	
	public List<Blog> getAllBlogs() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Blog");
		@SuppressWarnings("unchecked")
		List<Blog> listBlog=(List<Blog>)query.list();
		session.close();
		return listBlog;
		
	}

	
	public boolean incrementLikes(Blog blog) {
		// TODO Auto-generated method stub
		try
		{
			blog.setLikes(blog.getLikes()+1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Increment Likes:"+e);
		return false;
	}
	}
	
	public boolean incrementDisLikes(Blog blog) {
		// TODO Auto-generated method stub
		try
		{
			blog.setDislikes(blog.getDislikes()+1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Increment DisLikes:"+e);
		return false;
	}
	}

	
	public boolean approveBlog(Blog blog) {
		// TODO Auto-generated method stub
		try
		{
			blog.setStatus("A");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Approve Blog:"+e);
		return false;
	}
	}
	
	public boolean rejectBlog(Blog blog) {
		// TODO Auto-generated method stub
		try
		{
			blog.setStatus("NA");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Reject Blog"+e);
		return false;
	}
	}


public List<Blog> Listblog(int BlogId)
{
	Session session=sessionFactory.openSession();
	Query query=session.createQuery("from Blog where blogId=:myBlogId");
	List<Blog>Listblog=query.list();
	session.close();
	return Listblog;
}

}

