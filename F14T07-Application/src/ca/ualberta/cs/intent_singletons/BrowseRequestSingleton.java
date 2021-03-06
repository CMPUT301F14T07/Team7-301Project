package ca.ualberta.cs.intent_singletons;

/**
 * This is a singleton used for passing around browse requests to activites.
 * A browse request is simply one activity asking the browse activity to 
 * display everything that matches a certain string.
 * @author bbruner
 *
 */
public class BrowseRequestSingleton 
{
	private static BrowseRequestSingleton browseRequestSingleton = null;
	private String searchToken;
	private String viewToken;
	
	public static final String ON_LINE_VIEW_SEARCH = "Search";
	public static final String ON_LINE_VIEW = "Browse";
	public static final String READ_LATER_VIEW = "Saved";
	public static final String FAVOURITES_VIEW = "Favourites";
	public static final String MY_AUTHORED_VIEW = "My Questions";
	
	public static final String SEARCH_EVERYTHING = "";
	
	private BrowseRequestSingleton()
	{
		super();
		searchToken = BrowseRequestSingleton.SEARCH_EVERYTHING;
		viewToken = BrowseRequestSingleton.ON_LINE_VIEW;
	}
	
	/**
	 * Get an instance of this singleton.
	 * @return BrowseRequestionSingleton
	 */
	public static BrowseRequestSingleton getInstance()
	{
		if(browseRequestSingleton == null)
		{
			browseRequestSingleton = new BrowseRequestSingleton();
		}
		return browseRequestSingleton;
	}
	
	/**
	 * Set the view token to what sort of view should be used. ie, read later, favourites, etc..
	 * @param viewToken The token. Take this from BrowseRequestSingleton. ie, BrowseRequestSingleton.ON_LINE_VIEW.
	 */
	public void setViewToken(String viewToken)
	{
		this.viewToken = viewToken;
	}
	
	/**
	 * Get the view token to what sort of view should be used. ie, read later, favourites, etc..
	 * @return the view token.
	 */
	public String getViewToken()
	{
		return this.viewToken;
	}
	
	/**
	 * Set the search token in this singleton.
	 * @param searchToken To search for everything (wild card) give as input 
	 * BrowseRequestSingleton.SEARCH_EVERYTHING
	 */
	public void setSearchToken(String searchToken)
	{
		this.searchToken = searchToken;
	}
	
	/**
	 * Get the search token in this singleton
	 * @return String The search token
	 */
	public String getSearchToken()
	{
		return this.searchToken;
	}
}
