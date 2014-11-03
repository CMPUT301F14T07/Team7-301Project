package ca.ualberta.cs.views;

import java.util.ArrayList;

import ca.ualberta.cs.models.ForumEntry;

/**
 * Interface all views must implement. This interface defines an update function for the view.
 * @author bbruner
 *
 * @param <T> This must be the data type of the model the view uses.
 */
public interface Observer<T> 
{
	/**
	 * Called when ever data in the model changes.
	 * @param model This is a copy of the model class where the data was changed.
	 */
	public void update(T model);
}
