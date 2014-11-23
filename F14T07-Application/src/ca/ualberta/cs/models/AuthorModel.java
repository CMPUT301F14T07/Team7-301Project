package ca.ualberta.cs.models;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import ca.ualberta.cs.views.MainScreenActivity;
import ca.ualberta.cs.views.Observer;

/**
 * Models the person's name who is using the app. This is the author.
 * Also models the user's location as per their request
 * @author bbruner
 * @author dlacours
 *
 */
public class AuthorModel extends Observable<Observer> 
{
	private static String author = null;
	private static String location = null;
	
	public static final String NO_AUTHOR = "";

	/**
	 * Saves in local memory who the person is that will be authoring.
	 * @param author The person who is authoring.
	 */
	public void setSessionAuthor(String author)
	{
		AuthorModel.author = author;
	}
	
	/**
	 * Retrieves from memory the person who is authoring.
	 * @return The person who is authoring.
	 */ 
	public String getSessionAuthor()
	{
		return author;
	}
	/**
	 * Saves in local memory the location of the user.
	 * @param userLoc - a string inputed by the user or their GPS.
	 */
	public void setSessionLocation(String userLoc) {
		// TODO Auto-generated method stub
		AuthorModel.location = userLoc;
	}

	/**
	 * Retrieves from memory the location of the user.
	 * @return The location of the user.
	 */ 
	public String getSessionLocation()
	{
		return location;
	}
}
