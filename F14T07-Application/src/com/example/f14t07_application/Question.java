package com.example.f14t07_application;

import java.util.ArrayList;

public class Question
{
	private ArrayList<Post> answers;
	private Post question;
	
	public Question(String question, String author)
	{
		this.question.setPost(question);
		this.question.setPostersName(author);
		
		this.answers = new ArrayList<Post>();
	}
	
	public Post getQuestion()
	{
		return this.question;
	}
	
	public void addAnswer(Post answer)
	{
		this.answers.add(answer);
	}
	
	public Post getAnswer(int postPosition)
	{
		return this.answers.get(postPosition);
	}
	
	public void upVoteQuestion(int deltaVote)
	{
		this.question.setUpVote(this.question.getUpVote() + deltaVote);
	}
	
	public void downVoteQuestion(int deltaVote)
	{
		this.question.setDownVote(this.question.getDownVote() + deltaVote);
	}
	
	public int getUpVoteOfQuestion()
	{
		return this.question.getUpVote();
	}
	
	public int getDownVoteOfQuestion()
	{
		return this.question.getDownVote();
	}
	
	public void upVoteAnswer(int postPosition, int deltaVote)
	{
		Post answerHolder;
		
		answerHolder = this.answers.get(postPosition);
		answerHolder.setUpVote(answerHolder.getUpVote() + deltaVote);
	}
	
	public int getUpVoteOfAnswer(int postPosition)
	{
		return this.answers.get(postPosition).getUpVote();
	}
	
	public void downVoteAnswer(int postPosition, int deltaVote)
	{
		Post answerHolder;
		
		answerHolder = this.answers.get(postPosition);
		answerHolder.setUpVote(answerHolder.getUpVote() + deltaVote);
	}
	
	public int getDownVoteOfAnswer(int postPosition)
	{
		return this.answers.get(postPosition).getDownVote();
	}
	
	public void addReplyToQuestion(String reply)
	{
		this.question.addReply(reply);
	}
	
	public ArrayList<String> getRepliesToQuestion()
	{
		return this.question.getReplies();
	}
	
	public ArrayList<String> getRepliesToAnswer(int postPosition)
	{
		return this.answers.get(postPosition).getReplies();
	}
	
	public void addReplyToAnswer(String reply, int postPosition)
	{
		this.answers.get(postPosition).addReply(reply);
	}
}
