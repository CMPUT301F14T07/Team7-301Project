package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.List;

import ca.ualberta.cs.views.Observer;

/**
 * This is the model class for BrowseActivity and QuestionActivity. It provides
 * a simple set and get interface plus the notify observers routine from
 * Observable.
 * 
 */
public class ForumEntryList extends Observable<Observer>
{
	public static final int FIRST_FORUM_ENTRY = 0;
	
	private ArrayList<ForumEntry> forumEntries;

	/**
	 * Create a new ForumEntryList model.
	 */
	public ForumEntryList()
	{
		/*
		 * Assigning a new array list hear will prevent unexpected output from
		 * the getView function in the event that getView is called before a call
		 * to setView is made.
		 */
		this.forumEntries = new ArrayList<ForumEntry>();
	}

	/**
	 * Returns the data in this model
	 * 
	 * @return The data in this model.
	 */
	public ArrayList<ForumEntry> getView()
	{

		return forumEntries;
	}

	/**
	 * Sets the data in this model. This does not call the models notifyObservers function.
	 * @param list
	 *            The array list of forum entries to set in this model.
	 */
	public void setView(ArrayList<ForumEntry> list)
	{
		forumEntries = list;
	}

	/**
	 * Deprecated. Do not use.
	 * 
	 * @param list
	 */
	public void setMyAuthored(ArrayList<ForumEntry> list)
	{

	}

	/**
	 * Deprecated. Do not use.
	 * 
	 * @param list
	 */
	public void setReadLater(ArrayList<ForumEntry> list)
	{

	}

	/**
	 * Deprecated. Do not use.
	 * 
	 * @param list
	 */
	public void setFavourites(ArrayList<ForumEntry> list)
	{

	}

	/**
	 * Deprecated. Do not use.
	 * 
	 * @param list
	 */
	public boolean setRemote(ArrayList<ForumEntry> list)
	{

		return false;
	}

	/**
	 * Deprecated. Do not use.
	 * 
	 * @param list
	 */
	public boolean setRemoteBlynd(int x, ForumEntry fe)
	{

		return false;
	}

	/**
	 * Deprecated. Do not use.
	 * 
	 * @param list
	 */
	public ArrayList<ForumEntry> getMyAuthored()
	{

		ArrayList<ForumEntry> list = null;
		return list;
	}

	/**
	 * Deprecated. Do not use.
	 * 
	 * @param list
	 */
	public ArrayList<ForumEntry> getReadLater()
	{

		ArrayList<ForumEntry> list = null;
		return list;
	}

	/**
	 * Deprecated. Do not use.
	 * 
	 * @param list
	 */
	public ArrayList<ForumEntry> getFavourites()
	{

		ArrayList<ForumEntry> list = null;
		return list;
	}

	/**
	 * Deprecated. Do not use.
	 * 
	 * @param list
	 */
	public ArrayList<ForumEntry> getRemote(String a, String b, String c)
	{

		ArrayList<ForumEntry> list = null;
		return list;
	}

	/**
	 * Deprecated. Do not use.
	 * 
	 * @param list
	 */
	public boolean appendToRemote(ForumEntry fe)
	{

		return true;
	}

	/**
	 * Deprecated. Do not use.
	 * 
	 * @param list
	 */
	public ForumEntry getRemoteBlyd(int x)
	{

		ForumEntry fe = null;
		return fe;
	}
}
