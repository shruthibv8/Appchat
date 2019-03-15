package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;


public interface BlogCommentDAO
{
	public boolean addBlogComment(BlogComment blogcomment);
	public boolean deleteBlogComment(BlogComment blogcomment);
	
	public List<BlogComment> getAllBlogComments();

}
