package ca.ualberta.cs.views;

import java.util.ArrayList;

import ca.ualberta.cs.models.ForumEntry;

public interface Observer<T> 
{
	public void update(T msg);
}
