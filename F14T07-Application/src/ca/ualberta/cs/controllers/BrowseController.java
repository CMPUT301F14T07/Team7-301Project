package ca.ualberta.cs.controllers;

import java.util.ArrayList;
import java.util.List;

import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.MainScreenModel;



public class BrowseController {
	private List<ForumEntry> forumEntries;
	private SearchController searchController;
	
	public void setSessionAuthor(String author) {
		MainScreenModel msm = new MainScreenModel();
		msm.setSessionAuthor(author);
	}
	
	public BrowseController(){ 
		forumEntries = new ArrayList<ForumEntry>();
		searchController = new SearchController();
		
	}
	public List<ForumEntry> getAllEntries(){
		forumEntries.addAll(searchController.searchAll());
		ForumEntry forumEntry = new ForumEntry("browse","browse","browse");
		forumEntries.add(forumEntry);
		return forumEntries;
		
	}
}
