package ca.ualberta.cs.f14t07_application;

/**
 * Describes what a reply to an answer or main question looks like.
 * @author Brendan
 *
 */
public class Reply {
	private String reply;
	
	/**
	 * Instantiate a Reply object.
	 * @param reply The reply to an answer or main question.
	 */
	public Reply(String reply)
	{
		this.reply = reply;
	}
	
	/**
	 * Set the reply to an answer or main question.
	 * @param reply The reply to an answer or main question.
	 */
	public void setReply(String reply)
	{
		this.reply = reply;
	}
	
	/**
	 * Get the reply to a main question or answer.
	 * @return The reply to a main question or answer.
	 */
	public String getReply()
	{
		return this.reply;
	}

}
