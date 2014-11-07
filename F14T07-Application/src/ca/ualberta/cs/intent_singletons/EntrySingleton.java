package ca.ualberta.cs.intent_singletons;

import ca.ualberta.cs.models.Entry;

/**
 * This is a singleton class used to pass around an Entry between activities.
 *
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
