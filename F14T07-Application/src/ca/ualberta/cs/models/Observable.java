package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;

public abstract class Observable<R extends Activity, T>
{
	private ArrayList<R> Observers;
	
	public void addObserver(R observer)
	{
		this.Observers.add(observer);
	}
	
	public void deleteObserver(R observer)
	{
		this.Observers.remove(observer);
	}
	
	public void notifyObservers(T updateData)
	{
		Iterator<R> iter = this.Observers.iterator();
		
		while( iter.hasNext() )
		{
			iter.next().update(updateData);
		}
	}
	
}
