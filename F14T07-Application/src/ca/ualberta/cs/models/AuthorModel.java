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
 * @author bbruner
 *
 */
public class AuthorModel extends Observable<Observer> 
{
	private static String author = null;
	
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
}
