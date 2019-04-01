package com.niit.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.niit.dao.ProfilePictureDAO;
import com.niit.model.ErrorClass;
import com.niit.model.ProfilePicture;

@RestController
public class ProfilePictureController
{
	@Autowired
	private ProfilePictureDAO profilepicturedao;
		@RequestMapping(value="/uploadprofilepic", method=RequestMethod.POST)
		public ResponseEntity<?> saveOrUpdateProfilePicture(@RequestParam MultipartFile image, HttpSession session)
		{
			String email=(String)session.getAttribute("email");
			if(email==null){
				ErrorClass errorclass=new ErrorClass(6,"Please login...");
	    		return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);//login.html
			}
			ProfilePicture profilePicture=new ProfilePicture();
			profilePicture.setEmail(email);
			try
			{
				profilePicture.setImage(image.getBytes());
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			profilepicturedao.saveOrUpdateProfilePicture(profilePicture);
			return new ResponseEntity<ProfilePicture>(profilePicture, HttpStatus.OK);
		}
		@RequestMapping(value="/getimage", method=RequestMethod.GET)
		public byte[] getProfilePicture(@RequestParam String email, HttpSession session)
		{
			String authEmail=(String)session.getAttribute("email");
			if(authEmail==null){
	    		return null;
			}
			ProfilePicture profilePicture= profilepicturedao.getProfilePicture(email);
			if(profilePicture == null)
				return null;
			else
				return profilePicture.getImage();
		}
		

}
