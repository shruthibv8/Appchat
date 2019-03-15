package com.niit.dao;


import com.niit.model.User;

public interface UserDAO 
{
	public boolean registerUser(User user);
	public boolean updateUser(User user);
	public User getUser(String username);
	public boolean makeOffline(User user);
	public boolean makeOnline(User user);
	public boolean approveUser(User user);
	public boolean rejectUser(User user);
	
	
}
