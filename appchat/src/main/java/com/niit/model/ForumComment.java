package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ForumComment")
@SequenceGenerator(name="forumcommentidseq",sequenceName="myforumcommentseq")
public class ForumComment 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="forumcommentidseq")
	int forumcommentId;
	int forumId;
   String Comment;
   Date CommentDate;
   int UserId;
   String username;
public int getForumcommentId() {
	return forumcommentId;
}
public void setForumcommentId(int forumcommentId) {
	this.forumcommentId = forumcommentId;
}
public int getForumId() {
	return forumId;
}
public void setForumId(int forumId) {
	this.forumId = forumId;
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
