package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.FriendDAO;
import com.niit.dao.UserDAO;
import com.niit.model.ErrorClass;
import com.niit.model.Friend;
import com.niit.model.User;

@RestController
public class FriendController
{
	@Autowired
private FriendDAO frienddao;
	@Autowired
private UserDAO userdao;
	@RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
public ResponseEntity<?> getAllSuggestedUsers(HttpSession session)
{
	//CHECK IF THE HTTP REQUEST IS FROM AN AUTHENTICATED USER OR NOT
	String email=(String)session.getAttribute("email");
	if(email==null){
		ErrorClass errorclass=new ErrorClass(6,"Please login...");
		return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);//login.html
	}
	List<User> suggestedUsers=frienddao.getAllSuggestedUsers(email);
	return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
}
	@RequestMapping(value="/friendrequest", method=RequestMethod.POST)
	public ResponseEntity<?> friendRequest(@RequestBody User toId, HttpSession session)
	{
		String email=(String)session.getAttribute("email");
		if(email==null){
			ErrorClass errorclass=new ErrorClass(6,"Please login...");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);//login.html
		}
		Friend friend=new Friend();
		friend.setToid(toId);
		friend.setStatus('P');
		friend.setFromid(userdao.getUser(email));
		frienddao.friendRequest(friend);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@RequestMapping(value="/pendingrequests", method=RequestMethod.GET)
	public ResponseEntity<?> pendingrequests( HttpSession session)
	{
		String email=(String)session.getAttribute("email");
		if(email==null){
			ErrorClass errorclass=new ErrorClass(6,"Please login...");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);//login.html
		}
		List<Friend> pendingRequests=frienddao.pendingRequests(email);
		return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
	}
	@RequestMapping(value="/acceptfriendrequest",method=RequestMethod.PUT)
	public ResponseEntity<?> acceptFriendRequest(@RequestBody Friend friend, HttpSession session)
	{
	String email=(String)session.getAttribute("email");
	if(email == null)
	{
	ErrorClass errorclass=new ErrorClass(6,"Please Login............");
	return new   ResponseEntity<ErrorClass>(errorclass, HttpStatus.UNAUTHORIZED);
	}
	frienddao.acceptFriendRequest(friend);
	return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value="/deletefriendrequest",method=RequestMethod.PUT)
	public ResponseEntity<?> deleteFriendRequest(@RequestBody Friend friend, HttpSession session)
	{
	String email=(String)session.getAttribute("email");
	if(email == null)
	{
	ErrorClass errorclass=new ErrorClass(6,"Please Login............");
	return new   ResponseEntity<ErrorClass>(errorclass, HttpStatus.UNAUTHORIZED);
	}
	frienddao.deleteFriendRequest(friend);
	return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value="/listoffriends", method=RequestMethod.GET)
	public ResponseEntity<?> listOfFriends(HttpSession session)
	{
	String email=(String)session.getAttribute("email");
	if(email == null)
	{
	ErrorClass errorclass=new ErrorClass(6,"Please Login............");
	return new   ResponseEntity<ErrorClass>(errorclass, HttpStatus.UNAUTHORIZED);
	}
	List<User> friends=frienddao.listOfFriends(email);
	return new ResponseEntity<List<User>>(friends, HttpStatus.OK);
	}


}
