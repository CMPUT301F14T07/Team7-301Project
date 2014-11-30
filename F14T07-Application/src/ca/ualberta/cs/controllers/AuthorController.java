package ca.ualberta.cs.controllers;

import android.content.Context;
import ca.ualberta.cs.models.AuthorModel;
import ca.ualberta.cs.views.MainScreenActivity;
import ca.ualberta.cs.views.Observer;

/**
 * The users name and geographical location are controlled through this class. It parses data
 * before using the AuthorModel class to save it. It also gives access to AuthorModels 
 * notifyObservers() method.
 * @author bbruner
 * @author dlacours
 */
public class AuthorController 
{
	private AuthorModel authorModel;
	
	/**
	 * Constructor.
	 * @param viewsContext This is the instance of the view creating the controller. 
	 * Generally, the argument will be "this". This parameter is required so that 
	 * AuthorModel has access to the views update() method.
	 */
	public AuthorController(Observer viewsContext)
	{
		this.authorModel = new AuthorModel();
		this.authorModel.addObserver(viewsContext);
	}
	/** this is for getting only
	 * not to be used for setting 
	 */
	public AuthorController(){
		this.authorModel = new AuthorModel();
	}
	/**this is for getting only 
	 * not to be used for setting 
	 * @return authorModel
	 */
	public AuthorModel getModel(){
		return authorModel;
	}
	
	/**
	 * Sets who the author is after parsing the text. The parsing checks that the 
	 * provided author name is not a null pointer and that the name is not too long
	 * (greater than 30 characters). The models notifiyObservers() function is called
	 * if the parsing is passed, otherwise, nothing is done.
	 * @param author Name of the author to be set.
	 */
	public void setSessionAuthor(String author)
	{
		if( author != null && author.length() < 30)
		{
			this.authorModel.setSessionAuthor(author);
			this.authorModel.notifyObservers();
		}
	}
	
	/**
	 * Directly calls the models notifyObservers() method.
	 */
	public void refresh()
	{
		this.authorModel.notifyObservers();
	}
	
	/**
	 * Sets the location of the author after parsing their location. The location is parsed
	 * to check whether they are setting or unsetting their location and to make sure their
	 * location is not greater than 30 characters.
	 * @param userLoc Author location to be set. pass a null pointer to unset the authors
	 * location.
	 */
	public void setSessionLocation(String userLoc) {
		// TODO Auto-generated method stub
		
		if((userLoc != null) && userLoc.length() < 30)
		{
			//if it is null, then they are unsetting their location
			this.authorModel.setSessionLocation(userLoc);
		} else {

			this.authorModel.setSet(false);
			
		}
	}
	
	/**
	 * Sets where the author is. This input should be from a gps. There is no restriction
	 * on where the input comes from, but other applications will expect that this method
	 * was called with real gps latitude.
	 * @param lat Author latitude to be set.
	 */
	public void setSessionLatitude(double lat) {
		this.authorModel.setSessionLatitude(lat);
	}	
	
	/**
	 * Sets where the author is. This input should be from a gps. There is no restriction
	 * on where the input comes from, but other applications will expect that this method
	 * was called with real gps longitude.
	 * @param lon Author longitude to be set.
	 */
	public void setSessionLongitude(double lon) {
		this.authorModel.setSessionLongitude(lon);
	}
	
	/**
	 * Set if the authors location is set :/. So basically, pass an argument of true
	 * if the authors location is set. Pass an argument of false if the authors location
	 * is not set.
	 * @param set True if the authors location is set, false if the authors location is not set.
	 */
	public void setLocationBool(boolean set) {
		this.authorModel.setSet(set);
	}
	

}
