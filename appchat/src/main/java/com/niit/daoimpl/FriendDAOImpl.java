package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.FriendDAO;

import com.niit.model.Friend;
@Repository("friendDAO")
@Transactional

public class FriendDAOImpl implements FriendDAO
{
	@Autowired
	SessionFactory sessionFactory;


	public boolean addFriend(Friend friend) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(friend);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:Adding Friend"+e);
		return false;
	}
	}

	public boolean deleteFriend(Friend friend)
	{

		try
		{
		sessionFactory.getCurrentSession().remove(friend);
		return true;
	}
	catch(Exception e)
		{
		System.out.println("Exception Arised:Deleting Friend"+e);
	return false;
		}
	}

	public List<Friend> getAllFriends()
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where friendId=:myFriendId");
		List<Friend>Listfriend=query.list();
		session.close();
		return Listfriend;
	}

}
