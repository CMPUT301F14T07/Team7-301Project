package ca.ualberta.cs.intent_singletons;

/**
 * This is a singleton used for passing around browse requests to activites.
 * A browse request is simply one activity asking the browse activity to 
 * display everything that matches a certain string. No silly, don't go opening
 * any sockets for this.
 *
 */
public class BrowseRequestSingleton 
{
	private static final BrowseRequestSingleton browseRequestSingleton = new BrowseRequestSingleton();
	private String searchToken;
	
	private BrowseRequestSingleton()
	{
		super();
		searchToken = "*";
	}
	
	/**
	 * Get an instance of this singleton.
	 * @return BrowseRequestionSingleton
	 */
	public static BrowseRequestSingleton getInstance()
	{
		return browseRequestSingleton;
	}
	
	/**
	 * Set the search token in this singleton.
	 * @param searchToken
	 */
	public void setSearchToken(String searchToken)
	{
		this.searchToken = searchToken;
	}
	
	/**
	 * Get the search token in this singleton
	 * @return String
	 */
	public String getSearchToken()
	{
		return this.searchToken;
	}
}
