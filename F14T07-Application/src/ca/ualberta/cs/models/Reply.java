package ca.ualberta.cs.models;

import java.util.Date;

/**
 * Describes what a reply to an answer or main question looks like.
 * @author Brendan
 *
 */
public class Reply {
	private String reply;
	private Date date;
	/**
	 * Instantiate a Reply object.
	 * @param reply The reply to an answer or main question.
	 */
	public Reply(String reply_)
	{
		reply = reply_;
	}
	
	/**
	 * Set the reply to an answer or main question.
	 * @param reply The reply to an answer or main question.
	 */
	public void setReply(String reply_)
	{
		reply = reply_;
	}
	
	/**
	 * Get the reply to a main question or answer.
	 * @return The reply to a main question or answer.
	 */
	public String getReply()
	{
		return reply;
	}

}