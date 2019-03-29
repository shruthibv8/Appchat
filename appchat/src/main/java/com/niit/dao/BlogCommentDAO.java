package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;

public interface BlogCommentDAO
{
	void addBlogComment(BlogComment blogComment);
	List<BlogComment> getAllBlogComments(int blogPostId);
	void updateBlogComment(BlogComment blogComment);
	void deleteBlogComment(int commentId);
	BlogComment getBlogCommentById(int blogCommentId);

}
