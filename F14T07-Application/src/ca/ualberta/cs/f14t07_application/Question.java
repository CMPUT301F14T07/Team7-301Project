package ca.ualberta.cs.f14t07_application;

import java.util.ArrayList;

/**
 * Contains all the information of one forum post.
 * @author Brendan
 *
 */
public class Question
{
	private ArrayList<Post> answers;
	private Post question;
	
	/** 
	 * Instantiate a question.
	 * @param question	The text contains the main question for the forum post.
	 * @param author	The name of the main poster
	 */
	public Question(String question, String author)
	{
		super();
		
		this.question.setPost(question);
		this.question.setPostersName(author);
		
		this.answers = new ArrayList<Post>();
	}
	
	/**
	 * Get the forum post's main question.
	 * @return A Post object describing the main question.
	 */
	public Post getQuestion()
	{
		return this.question;
	}
	
	/**
	 * Add a Post object describing an answer. 
	 * @param answer Post object describing an answer.
	 */
	public void addAnswer(Post answer)
	{
		this.answers.add(answer);
	}
	
	/**
	 * Get a particular answer.
	 * @param postPosition	The position of the answer in the forum post. ie: if postPosition is 0 then the first 
	 * 						answer is returned, if postPosition is 1 then the second answer is returned,...
	 * @return	A Post object describing the answer.
	 */
	public Post getAnswer(int postPosition)
	{
		return this.answers.get(postPosition);
	}
	
	/**
	 * Change how many up votes the main question has by deltaVote.
	 * @param deltaVote	How much to change the up vote of the main question by.
	 */
	public void upVoteQuestion(int deltaVote)
	{
		this.question.setUpVote(this.question.getUpVote() + deltaVote);
	}
	
	/**
	 * Change how many down votes the main question has by deltaVote.
	 * @param deltaVote	How much to change the down vote of the main question by.
	 */
	public void downVoteQuestion(int deltaVote)
	{
		this.question.setDownVote(this.question.getDownVote() + deltaVote);
	}
	
	/**
	 * Get how many up votes the main question has.
	 * @return	How many up votes the main question has.
	 */
	public int getUpVoteOfQuestion()
	{
		return this.question.getUpVote();
	}
	
	/**
	 * Get how many down votes the main question has.
	 * @return	How many down votes the main question has.
	 */
	public int getDownVoteOfQuestion()
	{
		return this.question.getDownVote();
	}
	
	/**
	 * Change how many up votes an answer has by deltaVote.
	 * @param postPosition	The position of the answer. If postPosition is 0 then the first answers up vote
	 * 						will be changed, if postPosition is 1 then the second answers up vote will be
	 * 						changed,...
	 * @param deltaVote		How much to change the answer's up votes by.
	 */
	public void upVoteAnswer(int postPosition, int deltaVote)
	{
		Post answerHolder;
		
		answerHolder = this.answers.get(postPosition);
		answerHolder.setUpVote(answerHolder.getUpVote() + deltaVote);
	}
	
	/**
	 * How many up votes an answer has.
	 * @param postPosition	The position of the answer. If postPosition is 0 then the first answers up vote
	 * 						is retrieved, if postPosition is 1 then the second answers up vote is retrieved,...
	 * @return	How many up votes the answer has.
	 */
	public int getUpVoteOfAnswer(int postPosition)
	{
		return this.answers.get(postPosition).getUpVote();
	}
	
	/**
	 * Change how many down votes an answer has by deltaVote.
	 * @param postPosition	The position of the answer. If postPosition is 0 then the first answers down vote
	 * 						will be changed, if postPosition is 1 then the second answers down vote will be
	 * 						changed,...
	 * @param deltaVote		How much to change the answer's down votes by.
	 */
	public void downVoteAnswer(int postPosition, int deltaVote)
	{
		Post answerHolder;
		
		answerHolder = this.answers.get(postPosition);
		answerHolder.setUpVote(answerHolder.getUpVote() + deltaVote);
	}
	
	/**
	 * How many down votes an answer has.
	 * @param postPosition	The position of the answer. If postPosition is 0 then the first answers down vote
	 * 						is retrieved, if postPosition is 1 then the second answers down vote is retrieved,...
	 * @return	How many down votes the answer has.
	 */
	public int getDownVoteOfAnswer(int postPosition)
	{
		return this.answers.get(postPosition).getDownVote();
	}
	
	/**
	 * Adds a reply to the main question.
	 * @param reply	The Reply object containing the reply to the main question.
	 */
	public void addReplyToQuestion(Reply reply)
	{
		this.question.addReply(reply);
	}
	
	/**
	 * Get all replies to the main question.
	 * @return An ArrayList<Reply> containing all replies to the main question.
	 */
	public ArrayList<Reply> getRepliesToQuestion()
	{
		return this.question.getReplies();
	}
	
	/**
	 * Get all the replies to an answer.
	 * @param postPosition	The position of the answer. If postPosition is 0 then all replies 
	 * 						to the first answer are retrieved, if postPosition is 1 then all the replies
	 * 						to the second answer are retrieved.
	 * @return	An ArrayList<Reply> containing all replies to the answer.
	 */
	public ArrayList<Reply> getRepliesToAnswer(int postPosition)
	{
		return this.answers.get(postPosition).getReplies();
	}
	
	/**
	 * Adds a reply to a particular answer.
	 * @param reply			The Reply object containing the reply.
	 * @param postPosition	The position of the answer. If postPosition is 0 then adds a reply to
	 * 						the first answer, if postPosition is 1 then adds a reply to the second answer.
	 */
	public void addReplyToAnswer(Reply reply, int postPosition)
	{
		this.answers.get(postPosition).addReply(reply);
	}
}
