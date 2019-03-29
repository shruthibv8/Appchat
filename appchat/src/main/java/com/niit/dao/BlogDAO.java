package com.niit.dao;

import java.util.List;

import com.niit.model.Blog;

public interface BlogDAO 
{
	void addBlog(Blog blog);
	List<Blog> getBlogsWaitingForApproval();
	List<Blog> getBlogsApproved();
	Blog getBlog(int blogId);
	void approveBlog(int blogId);
	void rejectBlog(int blogId);
	void updateBlog(Blog blog);
	List<Blog> getBlogByEmail(String user_email);
	List<Blog> getBlogByEmail1(String email);


}
