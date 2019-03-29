package com.niit.dao;

import java.util.List;

import com.niit.model.Friend;
import com.niit.model.User;

public interface FriendDAO 
{
	List<User> getAllSuggestedUsers(String email);
	List<Friend> pendingRequests(String email);
	void friendRequest(Friend friend);
	void acceptFriendRequest(Friend friend);
	void deleteFriendRequest(Friend friend);
	List<User> listOfFriends(String email);

}
	

