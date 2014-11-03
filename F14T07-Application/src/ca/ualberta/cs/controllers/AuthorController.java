package ca.ualberta.cs.controllers;

import android.content.Context;
import ca.ualberta.cs.models.AuthorModel;
import ca.ualberta.cs.views.MainScreenActivity;
import ca.ualberta.cs.views.Observer;

/**
 * This controller allows the author's name to be set.
 * @author bbruner
 *
 */
public class AuthorController 
{
	private AuthorModel authorModel;
	
	/**
	 * Constructor for the AuthorModel controller.
	 * @param viewsContext This is the instance of the view creating the controller. Generally, the argument will be "this".
	 */
	public AuthorController(Observer viewsContext)
	{
		this.authorModel = new AuthorModel();
		this.authorModel.addObserver(viewsContext);
	}
	
	/**
	 * Sets who the author is.
	 * @param author Name of the author to be set.
	 */
	public void setSessionAuthor(String author)
	{
		if( author != null && author.trim().length() > 0 && author.length() < 30)
		{
			this.authorModel.setSessionAuthor(author);
			this.authorModel.notifyObservers();
		}
	}
	
	/**
	 * Forces the model to call all of its observer's update function.
	 */
	public void refresh()
	{
		this.authorModel.notifyObservers();
	}
	
}