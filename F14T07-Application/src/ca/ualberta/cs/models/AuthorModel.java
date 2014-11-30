package ca.ualberta.cs.models;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import ca.ualberta.cs.views.MainScreenActivity;
import ca.ualberta.cs.views.Observer;

/**
 * Model class for the users name and geographical location. location and name data
 * is stored as statics, therefore, this class gives a global access point to that
 * information. 
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
	private static String locType = "none";
	
	public static final String NO_AUTHOR = "";

	/**
	 * Saves the name of the author to a static variable.
	 * @param author The person who is authoring.
	 */
	public void setSessionAuthor(String author)
	{
		AuthorModel.author = author;
	}
	
	/**
	 * Return then name of the author from the static variable set with
	 * setSessionAuthor(String author).
	 * @return The person who is authoring.
	 */ 
	public String getSessionAuthor()
	{
		return author;
	}
	
	/**
	 * Saves to a static variable the geographical location of the user.
	 * @param userLoc - a String inputed by the user.
	 */
	public void setSessionLocation(String loc) {
		AuthorModel.location = loc;
	}
	
	/**
	 * Saves the users latitude to a static variable.
	 * @param userLoc - a double inputed by the GPS.
	 */
	public void setSessionLatitude(double lat) {
		// TODO Auto-generated method stub
		AuthorModel.latitude = lat;
	}

	/**
	 * Saves users longitude to a static variable.
	 * @param userLoc - a double inputed by the GPS.
	 */
	public void setSessionLongitude(double lon) {
		// TODO Auto-generated method stub
		AuthorModel.longitude = lon;
	}
	
	/**
	 * Returns the users location which was saved to a static variable with setSessionLocation().
	 * @return The location of the user.
	 */ 
	public String getSessionLocation()
	{
		return location;
	}
	/**
	 * Returns the users latitude from that static variable set with setSessionLatitude().
	 * @return The latitude of the user.
	 */ 
	public double getSessionLatitude()
	{
		return latitude;
	}
	/**
	 * Returns the users longitude from that static variable set with setSessionLatitude().
	 * @return The longitude of the user.
	 */ 
	public double getSessionLongitude()
	{
		return longitude;
	}
	/**
	 * Returns the type of location set
	 * @return none, gps, or text*/
	public static String getLocationType() {
		return locType;
	}
	/**
	 * sets the type of the location that 
	 * the user has set on the main screen.
	 * @param the location type being set*/
	public void setLocationType(String loctype){
		AuthorModel.locType = loctype;
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
