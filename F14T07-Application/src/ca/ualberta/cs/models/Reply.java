package ca.ualberta.cs.models;

import java.util.Date;

/**
 * Describes what a reply to an answer or main question looks like. It contains a date
 * (When the reply was made) and the text of the reply.
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
		this.date = new Date();
	}
	public String toString(){
		return reply;
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

	/**
	 * Gets the date of the reply.
	 * @return The Date.
	 */
	public Date getDate()
	{
		return this.date;
	}
}
