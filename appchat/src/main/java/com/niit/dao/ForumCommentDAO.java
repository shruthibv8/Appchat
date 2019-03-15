package com.niit.dao;

import java.util.List;

import com.niit.model.ForumComment;

public interface ForumCommentDAO
{
public boolean addForumComment(ForumComment forumcomment);
public boolean deleteForumComment(ForumComment forumcomment);

public List<ForumComment> getAllForumComments();

}
