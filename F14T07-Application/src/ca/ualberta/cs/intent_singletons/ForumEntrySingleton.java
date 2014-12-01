package ca.ualberta.cs.intent_singletons;

import ca.ualberta.cs.models.ForumEntry;

/**
 * This is a singleton class that is useful for setting a focus ForumEntry. By this, I mean that this 
 * singleton allows a person to set a ForumEntry which anyone can see and 'focus' on. It
 * provides a global access point to one ForumEntry.
 *
 * @author bbruner
 */


public class ForumEntrySingleton 
{
	private static ForumEntrySingleton forumEntrySingleton = null;
	private ForumEntry forumEntry;
	private boolean replyFlag;
	private int replyIndex=0;
	private ForumEntrySingleton()
	{
		super();
		this.forumEntry = null;
	}
	
	/**
	 * Get an instance of this singleton.
	 * @return ForumEntrySingleton
	 */
	public static ForumEntrySingleton getInstance()
	{
		if(forumEntrySingleton == null)
		{
			forumEntrySingleton = new ForumEntrySingleton();
		}
		return forumEntrySingleton;
	}
	
	/**
	 * Set the ForumEntry in this singleton.
	 * @param forumEntry
	 */
	public void setForumEntry(ForumEntry forumEntry)
	{
		this.forumEntry = forumEntry;
	}
	
	
	/**
	 * Get the ForumEntry in this singleton. 
	 * @return ForumEntry Will return null if a call to setForumEntry has not been made yet.
	 */
	public ForumEntry getForumEntry()
	{
		return this.forumEntry;
	}

	/**
	 * Set a flag. This is used by QuestionActivity to tell AskActivity
	 * it should set up its screen to add a reply to the question.
	 */
	public void setReplyFlag()
	{
		this.replyFlag = true;
	}
	/**
	 * Clear a flag. This is used by QuestionActivity to tell AskActivity
	 * it should not set up its screen to add a reply to the question.
	 */
	public void clearReplyFlag()
	{
		this.replyFlag = false;
	}
	
	/**
	 * This is used by AskActivity to see if it should set up the 
	 * screen for a reply or not.
	 * @return true if setting up for a reply, false otherwise.
	 */
	public boolean isReplyFlagSet()
	{
		return this.replyFlag;
	}
}
