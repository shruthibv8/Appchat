package com.niit.dao;

import java.util.List;

import com.niit.model.Friend;

public interface FriendDAO 
{
	public boolean addFriend(Friend friend);
	public boolean deleteFriend(Friend friend);
	
	public List<Friend> getAllFriends();
	

}
