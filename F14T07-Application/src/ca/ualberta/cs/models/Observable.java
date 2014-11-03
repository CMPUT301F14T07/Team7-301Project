package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.Iterator;

import ca.ualberta.cs.views.Observer;

import android.app.Activity;

public abstract class Observable<T>
{
	private ArrayList<Observer<T>> Observers;
	
	public void addObserver(Observer<T> observer)
	{
		this.Observers.add(observer);
	}
	
	public void deleteObserver(Observer<T> observer)
	{
		this.Observers.remove(observer);
	}
	
	public void notifyObservers(T updateData)
	{
		Iterator<Observer<T>> iter = this.Observers.iterator();
		
		while( iter.hasNext() )
		{
			iter.next().update(updateData);
		}
	}
	
}
