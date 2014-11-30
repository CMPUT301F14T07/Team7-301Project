package ca.ualberta.cs.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;




import org.apache.http.client.ClientProtocolException;

import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.models.Question;
import ca.ualberta.cs.views.BrowseActivity;
import ca.ualberta.cs.views.Observer;

/**
 * Controls the data in the model of the BrowseActivity
 * Also takes care of sorting
 * Contains a list of ForumEntries and a searchController
 * and determines whether we use the favourites or readLater view
 */
public class BrowseController {
	/*
	 * TODO: All sorting functions need to implement their sort algorithm.
	 */
	private ArrayList<ForumEntry> forumEntries;
	private SearchController searchController;
	private DataManager dataManager;
	
	private ForumEntryList onLineModel;
	private ForumEntryList readLaterModel;
	private ForumEntryList favouritesModel;
	private ForumEntryList myAuthoredModel;
	
	public ArrayList<ForumEntry> result;

	
	/**
	 * Constructor.
	 * @param viewsContext This is the instance of the view creating the controller. 
	 * Generally, the argument will be "this". This parameter is required so that 
	 * the model classes have access to the views update() method.
	 */
	public BrowseController(Observer viewsContext){ 
		this.forumEntries = new ArrayList<ForumEntry>();
		this.searchController = new SearchController();
		this.dataManager = new DataManager();
		
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
	 * Sorts the ForumEntrys in the model by their date with the most recent appearing
	 * first. This only sorts ForumEntries which are in the online view and this method
	 * does not invoke the models notifyObservers() method.
	 * and calls the models notifyObservers function.
	 */
	public void sortByTime()
	{
		ArrayList<ForumEntry> results = null;
		
		try
		{
			results = this.searchController.searchForumEntries("", null);
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
			Collections.sort(results, new Comparator<ForumEntry>(){
					public int compare( ForumEntry f1, ForumEntry f2){
						if (f1.getQuestion().getDate()==null && f2.getQuestion().getDate()==null){
							return 0;
						}
						else if (f1.getQuestion().getDate()==null){
							return 1;
						}
						else if (f2.getQuestion().getDate()==null){
							return -1;
						}
						else{
							return f2.getQuestion().getDate().compareTo(f1.getQuestion().getDate());
						}
					}
				});
		
			this.onLineModel.setView(results);
		}
	
		
	}

	/**
	 * Sorts the ForumEntrys in the model by their rating (up votes). ForumEntries with the
	 * highest rating appear first. This method only sorts ForumEntries in the online model and
	 * it does not invoke the models notifyObservers method.
	 */
	public void sortByRating(){
		ArrayList<ForumEntry> results = null;
		
		try
		{
			results = this.searchController.searchForumEntries("", null);
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
			Collections.sort(results, new Comparator<ForumEntry>(){
					public int compare( ForumEntry f1, ForumEntry f2){
							return  f2.getQuestion().getUpVote()-f1.getQuestion().getUpVote();
					}
				});
		
			this.onLineModel.setView(results);
		}
	}
	
	/**
	 * Sorts the ForumEntrys in the model by if they have a picture or not. ForumEntries
	 * with a picture appear first. This only sorts ForumEntries in the online model and
	 * it does not invoke the models notifyObservers method.
	 */
	public void sortByHasPicture(){
		ArrayList<ForumEntry> results = null;
		
		try
		{
			results = this.searchController.searchForumEntries("", null);
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
			Collections.sort(results, new Comparator<ForumEntry>(){
				public int compare( ForumEntry f1, ForumEntry f2){
					if (f1.getQuestion().getPicture()==null && f2.getQuestion().getPicture()==null){
						return 0;
					}
					else if (f1.getQuestion().getPicture()==null){
						return 1;
					}else{
						return -1;
					}
				}
			});
			this.onLineModel.setView(results);
		}
	}
	
	/**
	 * Searchs the remote server for the searchTerm and sets the model with the results.
	 * This function can ONLY be called from a network thread within the activity.
	 * This function does NOT invoke the models notifyObservers method. Try using
	 * useOnLineView() method to do that.
	 * @param searchTerm The term to search for.
	 */
	public void searchAndSet(String searchTerm)
	{
		ArrayList<ForumEntry> results = null;
		
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
	 * Directly calls the online models notifyObservers() method.
	 */
	public void useOnLineView()
	{
		this.onLineModel.notifyObservers();
	}
	
	/**
	 * Sets the model to be what ever was found in the read later save location
	 * and calls the models notifyObservers function.
	 */
	public void useReadLaterView()
	{
		ArrayList<ForumEntry> fel = this.dataManager.getReadLater();
		this.readLaterModel.setView(fel);
		this.readLaterModel.notifyObservers();
	}
	
	/**
	 * Sets the model to be what ever was found in the favourites save location
	 * and calls the models notifyObservers function.
	 */
	public void useFavouritesView()
	{
		ArrayList<ForumEntry> fel = this.dataManager.getFavourites();
		this.favouritesModel.setView(fel);
		this.favouritesModel.notifyObservers();
	}
	
	/**
	 * Sets the model to be what ever was found in the my authored save location
	 * and calls the models notifyObservers function.
	 */
	public void useMyAuthoredView()
	{
		ArrayList<ForumEntry> fel = this.dataManager.getMyAuthored();
		this.myAuthoredModel.setView(fel);
		this.myAuthoredModel.notifyObservers();
	}
	
	
	/**
	 * Sorts the ArrayList results by date. ForumEntries in the list with an earlier appear
	 * first. This method will update the model and call the models notifyObservers() method.
	 * @param results The ArraList to sort
	 * @param casetype Indicates what view is being sorted. 'F' for favourites, 'S' for read later
	 * and 'M' for my authored.
	 */
	public void sortByTimeLocal(ArrayList<ForumEntry> results, String casetype){
		if(results != null)
		{
			Collections.sort(results, new Comparator<ForumEntry>(){
					public int compare( ForumEntry f1, ForumEntry f2){
						if (f1.getQuestion().getDate()==null && f2.getQuestion().getDate()==null){
							return 0;
						}
						else if (f2.getQuestion().getDate()==null){
							return -1;
						}
						else if (f1.getQuestion().getDate()==null){
							return 1;
						}
						else{
							return f2.getQuestion().getDate().compareTo(f1.getQuestion().getDate());
						}
					}
				});
		    if (casetype.equals("F")){
		    	this.favouritesModel.setView(results);
		    	this.favouritesModel.notifyObservers();
		    }
		    else if (casetype.equals("S")){
				this.readLaterModel.setView(results);
				this.readLaterModel.notifyObservers();
			}
		    else if (casetype.equals("M")){
				this.myAuthoredModel.setView(results);
				this.myAuthoredModel.notifyObservers();
			} 
		    result=results;
		}
	
	}
	
	/**
	 * Sorts the ArrayList results by rating. ForumEntries in the list with a higher rating appear
	 * first. This method will update the model and call the models notifyObservers() method.
	 * @param results The ArraList to sort
	 * @param casetype Indicates what view is being sorted. 'F' for favourites, 'S' for read later
	 * and 'M' for my authored.
	 */
	public void sortByRatingLocal(ArrayList<ForumEntry> results, String casetype){
		if(results != null)
		{
			Collections.sort(results, new Comparator<ForumEntry>(){
					public int compare( ForumEntry f1, ForumEntry f2){
							return  f2.getQuestion().getUpVote()-f1.getQuestion().getUpVote();
					}
				});
		
			if (casetype.equals("F")){
		    	this.favouritesModel.setView(results);
		    	this.favouritesModel.notifyObservers();
		    }
			else if (casetype.equals("S")){
				this.readLaterModel.setView(results);
				this.readLaterModel.notifyObservers();
			}
			else if (casetype.equals("M")){
				this.myAuthoredModel.setView(results);
				this.myAuthoredModel.notifyObservers();
			}
			result=results;
		}
	}
	
	/**
	 * Sorts the ArrayList results by if they have a picture. ForumEntries in the list with 
	 * a picture appear first ones wihtout a picture appear last.
	 * This method will update the model and call the models notifyObservers() method.
	 * @param results The ArraList to sort
	 * @param casetype Indicates what view is being sorted. 'F' for favourites, 'S' for read later
	 * and 'M' for my authored.
	 */
	public void sortByHasPictureLocal(ArrayList<ForumEntry> results, String casetype){
		if(results != null)
		{
			Collections.sort(results, new Comparator<ForumEntry>(){
				public int compare( ForumEntry f1, ForumEntry f2){
					if (f1.getQuestion().getPicture()==null && f2.getQuestion().getPicture()==null){
						return 0;
					}
					else if (f1.getQuestion().getPicture()==null){
						return 1;
					}else{
						return -1;
					}
				}
			});
			if (casetype.equals("F")){
		    	this.favouritesModel.setView(results);
		    	this.favouritesModel.notifyObservers();
		    }
			else if (casetype.equals("S")){
				this.readLaterModel.setView(results);
				this.readLaterModel.notifyObservers();
			}
			else if (casetype.equals("M")){
				this.myAuthoredModel.setView(results);
				this.myAuthoredModel.notifyObservers();
			}
			result=results;
		}
	}

	public void sortByLoction() {
		// TODO Auto-generated method stub
		ArrayList<ForumEntry> results = null;
		try {
			results = this.searchController.searchForumEntries("", null,true);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<ForumEntry> sortedResults = new ArrayList<ForumEntry>();
		AuthorController authorController = new AuthorController();
		if (authorController.getModel().isSet() && !authorController.getModel().isGpsSet()){
			for(int i = 0; i<results.size(); i++){
			if(authorController.getModel().getSessionLocation() == results.get(i).getLocation()){
				sortedResults.add(results.get(i));
				sortedResults.remove(i);
				}
			}
		}
		
		
		this.onLineModel.setView(results);
	}
	
	


}
