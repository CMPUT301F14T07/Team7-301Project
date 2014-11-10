package ca.ualberta.cs.intent_singletons;

import ca.ualberta.cs.models.ForumEntry;

/**
 * This is a singleton class that is useful for starting the QuestionActivity. It allows
 * one activity to pass an instance of a ForumEntry around.
 *
 * @author bbruner
 */
public class ForumEntrySingleton 
{
	private static final ForumEntrySingleton forumEntrySingleton = new ForumEntrySingleton();
	private ForumEntry forumEntry;
	
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
	 * @return ForumEntry
	 */
	public ForumEntry getForumEntry()
	{
		return this.forumEntry;
	}
}
