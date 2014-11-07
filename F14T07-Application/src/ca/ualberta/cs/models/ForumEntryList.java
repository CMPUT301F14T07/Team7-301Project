package ca.ualberta.cs.models;

import java.util.ArrayList;

import ca.ualberta.cs.views.Observer;

/**
 * This is the model class for BrowseActivity and QuestionActivity. It provides a simple
 * set and get interface plus the notify observers routine from Observable.
 *
 */
public class ForumEntryList extends Observable<Observer> {

	private ArrayList<ForumEntry> forumEntries;
	
	/**
	 * Create a new ForumEntryList model.
	 */
	public ForumEntryList(){}
	
	/**
	 * Returns the data in this model
	 * @return The data in this model.
	 */
	public ArrayList<ForumEntry> getView() {
		
		ArrayList<ForumEntry> list=null;
		return list;
	}
	
	/**
	 * Sets the data in this model.
	 * @param list The array list of forum entries to set in this model.
	 */
	public void setView(ArrayList<ForumEntry> list) {
		
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param list
	 */
	public void setMyAuthored(ArrayList<ForumEntry> list) {
		
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param list
	 */
	public void setReadLater(ArrayList<ForumEntry> list) {
		
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param list
	 */
	public void setFavourites(ArrayList<ForumEntry> list) {
		
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param list
	 */
	public boolean setRemote(ArrayList<ForumEntry> list) {
		
		return false;
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param list
	 */
	public boolean setRemoteBlynd(int x, ForumEntry fe) {
		
		return false;
	}
	
	
	/**
	 * Deprecated. Do not use.
	 * @param list
	 */
	public ArrayList<ForumEntry> getMyAuthored() {
		
		ArrayList<ForumEntry> list=null;
		return list;
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param list
	 */
	public ArrayList<ForumEntry> getReadLater() {
		
		ArrayList<ForumEntry> list=null;
		return list;
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param list
	 */
	public ArrayList<ForumEntry> getFavourites() {
		
		ArrayList<ForumEntry> list=null;
		return list;
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param list
	 */
	public ArrayList<ForumEntry> getRemote(String a,String b, String c) {
		
		ArrayList<ForumEntry> list=null;
		return list;
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param list
	 */
	public boolean appendToRemote(ForumEntry fe) {
		
		return true;
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param list
	 */
	public ForumEntry getRemoteBlyd(int x){
		
		ForumEntry fe = null;
		return fe;
	}
}
