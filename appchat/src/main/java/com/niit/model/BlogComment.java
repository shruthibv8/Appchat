package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="BlogComment")
@SequenceGenerator(name="blogcommentidseq",sequenceName="myblogcommentseq")
public class BlogComment
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="blogcommentidseq")
	int blogcommentId;
	int blogId;
   String Comment;
   Date CommentDate;
   int UserId;
   String username;

   
   public int getBlogcommentId() {
	return blogcommentId;
}
public void setBlogcommentId(int blogcommentId) {
	this.blogcommentId = blogcommentId;
}
public int getBlogId() {
	return blogId;
}
public void setBlogId(int blogId) {
	this.blogId = blogId;
}
public String getComment() {
	return Comment;
}
public void setComment(String comment) {
	Comment = comment;
}
public Date getCommentDate() {
	return CommentDate;
}
public void setCommentDate(Date commentDate) {
	CommentDate = commentDate;
}
public int getUserId() {
	return UserId;
}
public void setUserId(int userId) {
	UserId = userId;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
}
