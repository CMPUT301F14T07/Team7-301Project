package ca.ualberta.cs.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




import org.apache.http.client.ClientProtocolException;

import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.views.BrowseActivity;
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
	private DataManager dataManager;
	
	private ForumEntryList onLineModel;
	private ForumEntryList readLaterModel;
	private ForumEntryList favouritesModel;
	private ForumEntryList myAuthoredModel;

	
	/**
	 * Creates a new BrowseController.
	 * @param viewsContext Context of the view which is using the model.
	 */
	public BrowseController(Observer viewsContext){ 
		this.forumEntries = new ArrayList<ForumEntry>();
		this.searchController = new SearchController();
		this.dataManager = new DataManager(null);
		
		this.onLineModel = new ForumEntryList();
		this.readLaterModel = new ForumEntryList();
		this.favouritesModel = new ForumEntryList();
		this.myAuthoredModel = new ForumEntryList();
		
		this.onLineModel.addObserver(viewsContext);
		this.readLaterModel.addObserver(viewsContext);
		this.favouritesModel.addObserver(viewsContext);
		this.myAuthoredModel.addObserver(viewsContext);
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
	 * This function can ONLY be called from a network thread within the activity.
	 * This function does NOT invoke the models notifyObservers method. Try using
	 * useOnLineView() method to do that.
	 * @param searchTerm The term to search for.
	 */
	public void searchAndSet(String searchTerm)
	{
		List<ForumEntry> results = null;
		
		try
		{
			results = this.searchController.searchForumEntries(searchTerm, null);
		} 
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		if(results != null)
		{
			this.onLineModel.setView(results);
		}
	}
	
	/**
	 * Updates the view to be what ever is in the onLineModel.
	 */
	public void useOnLineView()
	{
		this.onLineModel.notifyObservers();
	}
	
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
	 * Deprecated. Do not use.
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
		try {
			forumEntries.addAll(searchController.searchForumEntries("",null));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
