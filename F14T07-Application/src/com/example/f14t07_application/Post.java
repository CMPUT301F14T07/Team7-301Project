package com.example.f14t07_application;

import java.util.ArrayList;

public class Post
{
	private int upVote, downVote;
	private String post, postersName;
	private ArrayList<String> replies;

	public Post(String post, String postersName)
	{
		super();
		this.upVote = 0;
		this.downVote = 0;
		this.post = post;
		this.postersName = postersName;
		this.replies = new ArrayList<String>();
	}

	public String getReplySpecific(int id)
	{
		return this.replies.get(id);
	}
	
	public void addReply(String reply)
	{
		this.replies.add(reply);
	}
	
	public ArrayList<String> getReplies()
	{
		return replies;
	}

	public int getUpVote()
	{
		return upVote;
	}

	public void setUpVote(int upVote)
	{
		this.upVote = upVote;
	}

	public int getDownVote()
	{
		return downVote;
	}

	public void setDownVote(int downVote)
	{
		this.downVote = downVote;
	}

	public String getPost()
	{
		return post;
	}

	public void setPost(String post)
	{
		this.post = post;
	}

	public String getPostersName()
	{
		return postersName;
	}

	public void setPostersName(String postersName)
	{
		this.postersName = postersName;
	}

}
