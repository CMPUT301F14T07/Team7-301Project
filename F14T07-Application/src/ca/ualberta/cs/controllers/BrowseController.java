package ca.ualberta.cs.controllers;

import java.util.ArrayList;
import java.util.List;


import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.views.Observer;

/**
 * Controls the data in the model of the BrowseActivity
 * Also takes care of sorting
 * Contains a list of ForumEntries and the searchController
 * and determines whether we use the favourites or readLater view
 */
public class BrowseController {
	private List<ForumEntry> forumEntries;
	private SearchController searchController;
	
	private ForumEntryList forumEntryList;

	
	/**
	 * Creates a new BrowseController.
	 * @param viewsContext Context of the view which is using the model.
	 */
	public BrowseController(Observer viewsContext){ 
		forumEntries = new ArrayList<ForumEntry>();
		searchController = new SearchController();
		forumEntryList = new ForumEntryList();
		forumEntryList.addObserver(viewsContext);
	}
	
	/**
	 * Sorts the ForumEntrys in the model by their date.
	 */
	public void sortByTime(){}
	
	/**
	 * Sorts the ForumEntrys in the model by their rating (up votes).
	 */
	public void sortByRating(){}
	
	/**
	 * Sorts the ForumEntrys in the model by if they have a picture or not.
	 */
	public void sortByHasPicture(){}
	
	/**
	 * Searchs the remote server for the searchTerm and sets the model with the results.
	 * @param searchTerm The term to search for.
	 */
	public void searchAndSet(String searchTerm){}
	
	/**
	 * Sets the model to be what ever was found in the remote server.
	 */
	public void useOnLineView(){}
	
	/**
	 * Sets the model to be what ever was found in the read later save location.
	 */
	public void useReadLaterView(){}
	
	/**
	 * Sets the model to be what ever was found in the favourites save location.
	 */
	public void useFavouritesView(){}
	
	/**
	 * Sets the model to be what ever was found in the my authored save location
	 */
	public void useMyAuthoredView(){}
	
	/**
	 * Asks the model to update its observers (views).
	 */
	public void refresh(){}
	
	/**
	 * Deprecated. Do not use.
	 * @param author
	 */
	public void setSessionAuthor(String author) {
		//MainScreenModel msm = new MainScreenModel();
		//msm.setSessionAuthor(author);
	}
	/**
	 * Deprecated. Do not use.
	 * @return
	 */
	public List<ForumEntry> getAllEntries(){
		forumEntries.addAll(searchController.searchAll());
		ForumEntry forumEntry = new ForumEntry("browse","browse","browse");
		forumEntries.add(forumEntry);
		return forumEntries;
		
	}
	/**
	 * Deprecated. Do not use.
	 * @param forumEntryList
	 */
	public void sortByTime(ArrayList<ForumEntry> forumEntryList){}
	
	/**
	 * Deprecated. Do not use.
	 * @param forumEntryList
	 */
	public void sortByHasPicture(ArrayList<ForumEntry> forumEntryList){}
	
	/**
	 * Deprecated. Do not use.
	 * @param forumEntryList
	 */
	public void sortByRating(ArrayList<ForumEntry> forumEntryList){}
	
	/**
	 * Deprecated. Do not use.
	 * @param forumEntryList
	 */
	public void sortBySearchTerm(ArrayList<ForumEntry> forumEntryList){}
}
