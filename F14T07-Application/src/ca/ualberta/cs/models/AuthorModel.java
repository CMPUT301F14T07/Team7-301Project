package ca.ualberta.cs.models;

/**
 * Models the person's name who is using the app. This is the author.
 * @author bbruner
 *
 */
public class AuthorModel extends Observable<String> 
{
	private static String authorSaveLocation = "AUTHOR_SESSSION_SAVE";
	
	/**
	 * Saves in local memory who the person is that will be authoring.
	 * @param author The person who is authoring.
	 */
	public void setSessionAuthor(String author)
	{
		// TODO: save String author in memory
	}
	
	/**
	 * Retrieves from memory the person who is authoring.
	 * @return The person who is authoring.
	 */
	public String getSessionAuthor()
	{
		//TODO: retrieve String author from memory
		return "derp";
	}
}
