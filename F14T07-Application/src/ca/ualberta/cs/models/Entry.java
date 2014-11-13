package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.graphics.Picture;

/**
 * Super class of Question and Answer.
 * 
 */
public class Entry
{
	private int upVote;
	private String post;
	private String posterName;
	private List<Reply> replies;
	private Date date;
	private Picture picture = null;

	/**
	 * Creates a new Entry.
	 * 
	 * @param post_
	 *            The post (main body of text) of the Entry.
	 * @param author_
	 *            The person who wrote the Entry.
	 */
	public Entry(String post_, String author_)
	{
		this.post = post_;
		this.posterName = author_;
		this.upVote = 0;
		this.replies = new ArrayList<Reply>();
		this.date = new Date();
	}

	/**
	 * Returns the main body of text for this entry (the post).
	 * @return The main body of text.
	 */
	public String toString()
	{
		return this.post;
	}
	
	/**
	 * Get all the replies to this Entry.
	 * 
	 * @return ArrayList<Reply>
	 */
	public List<Reply> getReplies()
	{
		return replies;
	}

	/**
	 * Add a Reply to this Entry.
	 * 
	 * @param reply
	 *            The Reply to add.
	 */
	public void addReplies(Reply reply)
	{
		replies.add(reply);
	}

	/**
	 * Get how many up votes this Entry has.
	 * 
	 * @return int
	 */
	public int getUpVote()
	{
		return upVote;
	}

	/**
	 * Set how many up votes this Entry has.
	 * 
	 * @param upVote_
	 *            How many up votes this entry has.
	 */
	public void setUpVote(int upVote_)
	{
		upVote = upVote_;
	}

	/**
	 * Get the post (main body of text) for this Entry.
	 * 
	 * @return String
	 */
	public String getPost()
	{
		return post;
	}

	/**
	 * Set the post (main body of text) for this Entry.
	 * 
	 * @param s
	 *            The post (main body of text) for this Entry.
	 */
	public void setPost(String s)
	{
		post = s;
	}

	/**
	 * Get the name of the person who created this Entry.
	 * 
	 * @return String
	 */
	public String getAuthorsName()
	{
		return posterName;
	}

	/**
	 * Set the name of the person who created this Entry.
	 * 
	 * @param name
	 *            The person who created this Entry.
	 */
	public void setAuthorName(String name)
	{
		posterName = name;
	}

	/**
	 * Get the date this Entry was made
	 * 
	 * @return Java Date object.
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * Set the picture attached to this Entry. No, it cannot have two picture.
	 * Don't be stupid.
	 * 
	 * @param pic
	 *            The picture.
	 */
	public void setPicture(Picture pic)
	{
		picture = pic;
	}

	/**
	 * Returns the picture attached to this Entry.
	 * 
	 * @return Picture
	 */
	public Picture getPicture()
	{
		return picture;
	}
}
