package ca.ualberta.cs.f14t07_application;

import java.util.ArrayList;

/**
 * Describes what a forum post's main question or answer to main question contains.
 * @author Brendan
 * 
 */
public class Post
{
	private int upVote, downVote;
	private String post, postersName;
	private ArrayList<Reply> replies;

	/**
	 * Instantiate a forum post's main question or answer 
	 * @param post			The text that the main question or answer contains.
	 * @param postersName	The name of the person who posted to the main question or answer.
	 */
	public Post(String post, String postersName)
	{
		super();
		this.upVote = 0;
		this.downVote = 0;
		this.post = post;
		this.postersName = postersName;
		this.replies = new ArrayList<Reply>();
	}

	/**
	 * Retrieve a specific reply to a main question or answer.
	 * @param id	The position of the reply. For example, if there are 4 replies to a main 
	 * 				question or answer then an argument of 0 returns the first reply, an
	 * 				argument of 1 returns the second reply,... 
	 * @return		A Reply object which contains the reply.
	 */
	public Reply getReplySpecific(int id)
	{
		return this.replies.get(id);
	}
	
	/**
	 * Add a reply to the main question or answer.
	 * @param reply	The Reply object that contains the reply.
	 */
	public void addReply(Reply reply)
	{
		this.replies.add(reply);
	}
	
	/**
	 * Retrieve all replies to a main question or answer.
	 * @return	All replies to a main question or answer in the form of an ArrayList<Reply>.
	 */
	public ArrayList<Reply> getReplies()
	{
		return replies;
	}

	/**
	 * How many times the main question or answer has been up voted.
	 * @return How many times the main question or answer has been up voted.
	 */
	public int getUpVote()
	{
		return upVote;
	}

	/**
	 * Specify the exact number of people who have up voted the main question or answer.
	 * @param upVote How many up votes the main question or answer has.
	 */
	public void setUpVote(int upVote)
	{
		this.upVote = upVote;
	}

	/**
	 * How many times the main question or answer has been down voted.
	 * @return How many times the main question or answer has been down voted.
	 */
	public int getDownVote()
	{
		return downVote;
	}

	/**
	 * Specify the exact number of people who have down voted the main question or answer.
	 * @param downVote How many down votes the main question or answer has.
	 */
	public void setDownVote(int downVote)
	{
		this.downVote = downVote;
	}

	/**
	 * The main question or answer's text.
	 * @return The text of the main question or answer.
	 */
	public String getPost()
	{
		return post;
	}

	/**
	 * Specify the text of the main question or answer.
	 * @param post The text of the main question or answer.
	 */
	public void setPost(String post)
	{
		this.post = post;
	}

	/**
	 * Get the text of the poster's name.
	 * @return The text of the poster's name.
	 */
	public String getPostersName()
	{
		return postersName;
	}

	/**
	 * Specify the text of the poster's name.
	 * @param postersName The text of the poster's name.
	 */
	public void setPostersName(String postersName)
	{
		this.postersName = postersName;
	}

}
