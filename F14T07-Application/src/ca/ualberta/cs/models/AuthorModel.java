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
	private static double latitude = 0.0;
	private static double longitude = 0.0;
	private static String location = null;
	private static boolean set = false;
	
	
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
	 * @param userLoc - a String inputed by the user.
	 */
	public void setSessionLocation(String loc) {
		AuthorModel.location = loc;
	}
	
	/**
	 * Saves in local memory the latitude of the user.
	 * @param userLoc - a double inputed by the GPS.
	 */
	public void setSessionLatitude(double lat) {
		// TODO Auto-generated method stub
		AuthorModel.latitude = lat;
	}

	/**
	 * Saves in local memory the longitude of the user.
	 * @param userLoc - a double inputed by the GPS.
	 */
	public void setSessionLongitude(double lon) {
		// TODO Auto-generated method stub
		AuthorModel.longitude = lon;
	}
	
	/**
	 * Retrieves from memory the location of the user.
	 * @return The location of the user.
	 */ 
	public String getSessionLocation()
	{
		return location;
	}
	/**
	 * Retrieves from memory the latitude of the user.
	 * @return The latitude of the user.
	 */ 
	public double getSessionLatitude()
	{
		return latitude;
	}
	/**
	 * Retrieves from memory the longitude of the user.
	 * @return The longitude of the user.
	 */ 
	public double getSessionLongitude()
	{
		return longitude;
	}
	/**
	 * checks if the location is set.
	 * If it is this returns true, otherwise false
	 * @return true or false*/
	public static boolean isSet() {
		return set;
	}
	/**
	 * sets the set variable
	 * This tells us if the location is set
	 * @param set - true or false
	 * true if the location is set*/
	public void setSet(boolean set) {
		AuthorModel.set = set;
	}
}
