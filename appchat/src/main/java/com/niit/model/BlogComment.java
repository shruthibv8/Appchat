package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BlogComment")

public class BlogComment
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int commentId;
private String commentTxt;
@ManyToOne
private Blog blog;
private Date commentedOn;
@ManyToOne
private User commentedBy;
public int getCommentId() 
{
	return commentId;
}
public String getCommentTxt() {
	return commentTxt;
}
public void setCommentTxt(String commentTxt) {
	this.commentTxt = commentTxt;
}
public Blog getBlog() {
	return blog;
}
public void setBlog(Blog blog) {
	this.blog = blog;
}
public Date getCommentedOn() {
	return commentedOn;
}
public void setCommentedOn(Date commentedOn) {
	this.commentedOn = commentedOn;
}
public User getCommentedBy() {
	return commentedBy;
}
public void setCommentedBy(User commentedBy) {
	this.commentedBy = commentedBy;
}
public void setCommentId(int commentId) {
	this.commentId = commentId;
}

}
