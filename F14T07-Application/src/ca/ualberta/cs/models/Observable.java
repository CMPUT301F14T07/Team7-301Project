package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.Iterator;

import ca.ualberta.cs.views.Observer;

import android.app.Activity;

/**
 * Contains a list of all the views who are "observing" this model. All classes which are added here must implement the Observer interface.
 * @author bbruner
 *
 * @param <T> This is the data type of the view
 */
public abstract class Observable<T extends Observer>
{
	private ArrayList<T> observers;
	
	public Observable()
	{
		observers = new ArrayList<T>();
	}
	
	/**
	 * Add an observer to the class.
	 * @param observer The observer to add.
	 */
	public void addObserver(T observer)
	{
		if( !this.observers.contains(observer) )
		{
			this.observers.add(observer);
		}
	}
	
	/**
	 * Delete an observer from the class
	 * @param observer The observer to delete.
	 */
	public void deleteObserver(T observer)
	{
		this.observers.remove(observer);
	}
	
	/**
	 * Notify all the observers that data has changed.
	 * @param updateData
	 */
	public void notifyObservers()
	{
		Iterator<T> iter = this.observers.iterator();
		
		while( iter.hasNext() )
		{
			iter.next().update(this);
		}
	}
	
}
