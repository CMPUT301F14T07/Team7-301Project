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

}
