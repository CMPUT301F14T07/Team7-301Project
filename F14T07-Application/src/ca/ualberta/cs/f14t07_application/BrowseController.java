package ca.ualberta.cs.f14t07_application;

import java.util.ArrayList;
import java.util.List;



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
