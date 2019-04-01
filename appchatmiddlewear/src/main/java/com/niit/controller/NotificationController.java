package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.NotificationDAO;
import com.niit.model.ErrorClass;
import com.niit.model.Notification;

@RestController
public class NotificationController
{
	@Autowired
	private NotificationDAO notificationdao;
	   @RequestMapping(value="/notificationsnotviewed",method=RequestMethod.GET)
		public ResponseEntity<?> getAllNotificationsNotViewed(HttpSession session){
			String email=(String)session.getAttribute("email");
			if(email==null){
				ErrorClass errorclass=new ErrorClass(6,"Please login...");
	    		return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);//login.html
			}
			List<Notification> notificationsNotViewed= notificationdao.getAllNotificationsNotViewed(email);
			return new ResponseEntity<List>(notificationsNotViewed,HttpStatus.OK);
		}
	   @RequestMapping(value="/getnotification/{notificationId}",method=RequestMethod.GET)
		public ResponseEntity<?> getNotification(@PathVariable int notificationId, HttpSession session){
			String email=(String)session.getAttribute("email");
			if(email==null){
				ErrorClass errorclass=new ErrorClass(6,"Please login...");
	   		return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);//login.html
			}
			Notification notification= notificationdao.getNotification(notificationId);
			return new ResponseEntity<Notification>(notification,HttpStatus.OK);
		}
	   @RequestMapping(value="/updatenotification/{notificationId}",method=RequestMethod.PUT)
		public ResponseEntity<?> updateNotification(@PathVariable int notificationId,HttpSession session){
			String email=(String)session.getAttribute("email");
			if(email==null){
				ErrorClass errorclass=new ErrorClass(6,"Please login...");
	   		return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);//login.html
			}
		  notificationdao.updateNotificactionViewedStatus(notificationId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

}
