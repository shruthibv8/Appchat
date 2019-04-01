package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.BlogDAO;
import com.niit.dao.NotificationDAO;
import com.niit.dao.UserDAO;
import com.niit.model.Blog;
import com.niit.model.ErrorClass;
import com.niit.model.Notification;
import com.niit.model.User;

@Controller
public class BlogController
{
	@Autowired
	private BlogDAO blogdao;
	@Autowired
	private UserDAO userdao;
	@Autowired
	private NotificationDAO  notificationdao;
		// input  : {'blogTitle':'..','blogContent':'.....'}
		@RequestMapping(value="/addblog",method=RequestMethod.POST)
		public ResponseEntity<?> addBlog(@RequestBody Blog blog,HttpSession session){
			String email=(String)session.getAttribute("email");
			//NOT LOGGED IN
			if(email==null)
			{
				ErrorClass errorclass=new ErrorClass(6,"Please login...");
	    		return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);//login.html
			}
			blog.setPostedOn(new Date());
			User postedBy=userdao.getUser(email);
			blog.setPostedBy(postedBy);
			try
			{
			blogdao.addBlog(blog);
			}
			catch(Exception e){
				ErrorClass errorclass=new ErrorClass(8,"Unable to save blog post details.." + e.getMessage());
				return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
		@RequestMapping(value="/blogswaitingforapproval",method=RequestMethod.GET)
		public ResponseEntity<?> getBlogsWaitingForApproval(HttpSession session){
			String email=(String)session.getAttribute("email");
			//NOT LOGGED IN
			if(email==null){
				ErrorClass errorclass=new ErrorClass(6,"Please login...");
	    		return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);//login.html
			}
			//ROLE - AUTHORIZATION
			User user=userdao.getUser(email);
			if(!user.getRole().equals("ADMIN"))
			{
				ErrorClass errorclass=new ErrorClass(9,"You are not authorized to view the content..");
				return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
			}
			List<Blog> blogsWaitingForApproval=blogdao.getBlogsWaitingForApproval();
			return new ResponseEntity<List<Blog>>(blogsWaitingForApproval,HttpStatus.OK);
		}
		@RequestMapping(value="/blogsapproved",method=RequestMethod.GET)
		public ResponseEntity<?> getBlogsApproved(HttpSession session){
			String email=(String)session.getAttribute("email");
			//NOT LOGGED IN
			if(email==null){
				ErrorClass errorclass=new ErrorClass(6,"Please login...");
	    		return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);//login.html
			}
			
			List<Blog> blogsApproved=blogdao.getBlogsApproved();
			return new ResponseEntity<List<Blog>>(blogsApproved,HttpStatus.OK);
		}
		@RequestMapping(value="/getblog/{blogId}",method=RequestMethod.GET)
		public ResponseEntity<?> getBlog(@PathVariable int blogId,HttpSession session){
			String email=(String)session.getAttribute("email");
			//NOT LOGGED IN
			if(email==null){
				ErrorClass errorclass=new ErrorClass(6,"Please login...");
	    		return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);//login.html
			}
			Blog blog=blogdao.getBlog(blogId);
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
		@RequestMapping(value="/approveblogpost/{blogId}",method=RequestMethod.PUT)
		public ResponseEntity<?> approveBlog(@PathVariable int blogId,HttpSession session)
		{
		String email=(String)session.getAttribute("email");
		//NOT LOGGED IN
		if(email==null){
			ErrorClass errorclass=new ErrorClass(6,"Please login...");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);//login.html
		}
		//ROLE - AUTHORIZATION
		User user=userdao.getUser(email);
		if(!user.getRole().equals("ADMIN")){
			ErrorClass errorclass=new ErrorClass(9,"You are not authorized to view the content..");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		blogdao.approveBlog(blogId);
		Blog blog=blogdao.getBlog(blogId);
		Notification notification=new Notification();
		notification.setBlogTitle(blog.getBlogTitle());
		notification.setStatus("Approved");//value of status is "Approved"  or "Rejected"
		notification.setUserToBeNotified(blog.getPostedBy().getEmail());
		notificationdao.addNotification(notification);
			return new ResponseEntity<Void>(HttpStatus.OK);
			
		}
		@RequestMapping(value="/rejectblogpost/{blogId}/{rejectionReason}")
		public ResponseEntity<?> rejectBlog(@PathVariable int blogId,@PathVariable String rejectionReason,HttpSession session){
			String email=(String)session.getAttribute("email");
			//NOT LOGGED IN
			if(email==null){
				ErrorClass errorclass=new ErrorClass(6,"Please login...");
	    		return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);//login.html
			}
			//ROLE - AUTHORIZATION
			User user=userdao.getUser(email);
			if(!user.getRole().equals("ADMIN")){
				ErrorClass errorclass=new ErrorClass(9,"You are not authorized to view the content..");
				return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
			}
			Blog blog=blogdao.getBlog(blogId);
			
			Notification notification=new Notification();
			notification.setBlogTitle(blog.getBlogTitle());
			notification.setStatus("Rejected");
			notification.setUserToBeNotified(blog.getPostedBy().getEmail());
			notification.setRejectionReason(rejectionReason);
			notificationdao.addNotification(notification);
			blogdao.rejectBlog(blogId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		@RequestMapping(value="/update" , method=RequestMethod.PUT)
		public ResponseEntity<?> updateBlog(@RequestBody Blog blog,HttpSession session)
		{
			String email=(String)session.getAttribute("email");
			//NOT LOGGED IN
			if(email==null){
				ErrorClass errorclass=new ErrorClass(6,"Please login...");
				return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);//login.html
			}
		
			try{
				System.out.println("Blog Updated");
				blog.setApproved(false);
				blog.setPostedOn(new Date());
				blogdao.updateBlog(blog);
			}
			catch(Exception e)
			{
				ErrorClass errorclass=new ErrorClass(10,"UNABLE TO UPDATE BLOGPOST CONTENT"+e.getMessage());
				return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		@RequestMapping(value="/getblogbyemail",method=RequestMethod.GET)  
		public ResponseEntity<?> getBlog(HttpSession session){                                 
			String email=(String)session.getAttribute("email");
		
			List<Blog> blogs=blogdao.getBlogByEmail(email);
			return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
		}
		@RequestMapping(value="/getblogbyemail1",method=RequestMethod.GET)  
		public ResponseEntity<?> getBlog1(HttpSession session){                                 
			String email=(String)session.getAttribute("email");
			
			List<Blog> blogs=blogdao.getBlogByEmail1(email);
			return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
		}
		

}
