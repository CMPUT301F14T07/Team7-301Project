package ca.ualberta.cs.intent_singletons;

import ca.ualberta.cs.models.Entry;

/**
 * Use this singleton as a global access point to one Entry. This is useful for sending
 * information about one Entry between activities.
 *
 * @author bbruner
 */
public class EntrySingleton 
{
	private static final EntrySingleton entrySingleton = new EntrySingleton();
	private Entry entry;
	
	private EntrySingleton()
	{
		super();
		this.entry = null;
	}
	
	/**
	 * Get an instance of the singleton.
	 * @return EntrySingleton
	 */
	public static EntrySingleton getInstance()
	{
		return entrySingleton;
	}
	
	/**
	 * Set the Entry of the singleton.
	 * @param entry
	 */
	public void setEntry(Entry entry)
	{
		this.entry = entry;
	}
	
	/**
	 * Get the singleton's Entry.
	 * @return Entry
	 */
	public Entry getEntry()
	{
		return this.entry;
	}
}
