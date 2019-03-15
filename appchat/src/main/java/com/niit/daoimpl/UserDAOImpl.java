package com.niit.daoimpl;


import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.UserDAO;
import com.niit.model.User;
@Repository("UserDAO")
@Transactional
public class UserDAOImpl implements UserDAO
{
@Autowired
SessionFactory sessionFactory;

	public boolean registerUser(User user)
	{
	try
	{
		sessionFactory.getCurrentSession().save(user);
		return true;
	}
	catch(Exception e)
	{
		System.out.println("Exception Arised:Adding User"+e);
	return false;
}
	}

	public boolean updateUser(User user)
	{
		try
		{
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Updating User"+e);
		return false;
	}
	}

	public User getUser(String username)
	{
		Session session=sessionFactory.openSession();
		User user=session.get(User.class,username);
		session.close();
		return user;
	}

	public boolean makeOffline(User user)
	{
		try
		{
			user.setIsOnline("Off");
			sessionFactory.getCurrentSession().update(user);
			return true;
			
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Make Offline:"+e);
			return false;
		}
	}

	public boolean makeOnline(User user)
	{
		try
		{
			user.setIsOnline("On");
			sessionFactory.getCurrentSession().update(user);
			return true;
			
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Make Online"+e);
			return false;
		}
	}

	public boolean approveUser(User user)
	{
		try
		{
			user.setStatus("A");
			sessionFactory.getCurrentSession().update(user);
			return true;
			
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Approve User:"+e);
			return false;
		}
	}

	public boolean rejectUser(User user) 
	{
		try
		{
			user.setStatus("R");
			sessionFactory.getCurrentSession().update(user);
			return true;
			
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Reject User:"+e);
			return false;
		}
	
	}

}
