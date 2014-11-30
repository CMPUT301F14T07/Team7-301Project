package ca.ualberta.cs.models;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;
import ca.ualberta.cs.intent_singletons.ContextSingleton;
import ca.ualberta.cs.remote_server.NetworkChecker;
import ca.ualberta.cs.views.AskActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Model class for the data saved in memory and/or the remote server.
 * It has the ability to pass questions to either elastic search 
 * or into a file found on the phone 
 * 
 * @author lexie
 */
public class DataManager
{
	/*
	 * TODO: Append Answers onto ForumEntries in the remote server
	 * TODO: Merge changes into the remote server (like increasing an upvote)
	 * TODO: Get and set caches for favourites, my authored, and read laters
	 */
	private static String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t07/ForumEntry";
	private static String ORIGINAL_RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t07/ForumEntry";
	private static final String TAG = "ForumEntrySearch";
	
	private Context ctx;
	private Gson gson;
	private ForumEntry forumEntryTest;

	public DataManager()
	{
		gson = new Gson();
	}


	/**
	 * adds a new forum entry to the system
	 * 
	 * @param forum
	 *            entry
	 * */
	public void addForumEntry(ForumEntry forumEntry)
	{
		NetworkChecker networkChecker = new NetworkChecker();
		
		if(networkChecker.getIsOnline()){
		gson = new Gson();
		HttpClient httpClient = new DefaultHttpClient();
		forumEntry.setId("0");

		try
		{
			HttpPost addRequest = new HttpPost(RESOURCE_URL);

			StringEntity stringEntity = new StringEntity(
					gson.toJson(forumEntry));
			addRequest.setEntity(stringEntity);
			addRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(addRequest);
			String status = response.getStatusLine().toString();

			System.out.println(status);
			HttpEntity entity = response.getEntity();
			BufferedReader br;
			try
			{
				br = new BufferedReader(new InputStreamReader(
						entity.getContent()));
				String output;
				System.err.println("Output from Server -> ");
			
				while ((output = br.readLine()) != null)
				{
					System.err.println(output);
					String[]str_array = output.split(",");
					String id = str_array[2].replaceAll("^\"|\"$","");
					id.replaceAll(":", "");
					id.replaceAll("_","");
					id = id.substring(6);
					System.err.println(id);
					if(id!=null){
						forumEntry.setId(id);
					}
				}
			} catch (IOException e)
			{
				System.err.println(e);
			}
		} catch (Exception e)
		{

			e.printStackTrace();
		}
		}
		else{
			Toast.makeText(ctx,
					"Must Connect To the Internet",
					Toast.LENGTH_SHORT).show();
			networkChecker.saveForLater(forumEntry);
		}
		forumEntryTest = forumEntry;
		
		
	}
	
	public void deleteMovie(ForumEntry forumEntry) {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpDelete deleteRequest = new HttpDelete(RESOURCE_URL + forumEntry.getId());
			deleteRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(deleteRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateForumEntry(ForumEntry forumEntry) {
		RESOURCE_URL=RESOURCE_URL+ "/"+ forumEntry.getId();
		
		addForumEntry(forumEntry);
		RESOURCE_URL=ORIGINAL_RESOURCE_URL;
	}

	
	/**
	 * Removes a given ForumEntry from the current locally cached list.
	 * <p>Is called by the BrowseActivity when showing FavouritesView or ReadLaterView
	 * 
	 * @param focus is a ForumEntry to be removed.
	 * @param string is given by the caller to declare which of Favourites or ReadLaters is being removed.    
	 * */
	public void unSave(ForumEntry focus, String string) 
	{		
		ArrayList<ForumEntry> fel;
		
		
		if (string.equals("F")) {
			
			fel = this.getFavourites();
			fel.remove(focus);
			this.setFavourites(fel);
			
		} else {
			
			fel = this.getReadLater();
			fel.remove(focus);
			this.setReadLater(fel);
			
		}
		
	}
	
	/**
	 * Saves a List of ForumEntries to given location in Json format.
	 * <p>Is only called within the DataManager. 
	 * 
	 * @param fel is a List of ForumEntry to be Json'd and saved.
	 * @param FILE_NAME is given by the caller to declare where to save the list.    
	 * */
	private void saveLocal(ArrayList<ForumEntry> fel, String FILE_NAME) {
		Context ctx = ContextSingleton.getInstance().getContext();
		try
		{
			FileOutputStream fos = ctx.openFileOutput(FILE_NAME,
					Context.MODE_PRIVATE);
			String json = gson.toJson(fel);
			fos.write(json.getBytes());
			fos.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads a List of ForumEntries from a given location.
	 * <p>Is only called within the DataManager. 
	 * 
	 * @param FILE_NAME is given by the caller to declare which list to load.    
	 * */
	private ArrayList<ForumEntry> loadLocal(String FILE_NAME) 
	{
		ArrayList<ForumEntry> fel = new ArrayList<ForumEntry>();
		Context ctx = ContextSingleton.getInstance().getContext();

		try
		{
			BufferedReader fis = new BufferedReader(new InputStreamReader(
					ctx.openFileInput(FILE_NAME)));
			String line;
			StringBuffer fileContent = new StringBuffer();

			while ((line = fis.readLine()) != null)
			{
				fileContent.append(line);
			}

			Type collectionType = new TypeToken<Collection<ForumEntry>>()
			{
			}.getType();

			fel = gson.fromJson(fileContent.toString(), collectionType);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return fel;
	}
	
	/**
	 * Saves a List of ForumEntries as readlater forum entries.
	 * <p>Called by the ForumEntryController when prompted by the QuestionActivity.
	 * 
	 * @param fel is the list to be saved.
	 *            
	 */
	public void setReadLater(ArrayList<ForumEntry> fel)
	{
		this.saveLocal(fel,"readlater.sav");
	}

	/**
	 * Load the List of ForumEntries marked as readlater.
	 * <p> Called by the BrowseActivity when viewing ReadLaters to populate list. Also called before saving new entries. 
	 * 
	 * @return The List<ForumEntry> marked as read later.
	 */
	public ArrayList<ForumEntry> getReadLater()
	{
		return this.loadLocal("readlater.sav");
	}

	/**
	 * Saves a List of ForumEntries as MyAuthored forum entries.
	 * <p>Called by the ForumEntryController when prompted by the AskActivity.
	 * 
	 * @param fel is the list to be saved.
	 *            
	 */
	public void setMyAuthored(ArrayList<ForumEntry> fel)
	{
		this.saveLocal(fel,"myauthor.sav");
	}

	/**
	 * Load the List of ForumEntries marked as MyAuthored.
	 * <p> Called by the BrowseActivity when viewing MyAuthored to populate list. Also called before saving new entries. 
	 * 
	 * @return The List<ForumEntry> marked as read later.
	 */
	public ArrayList<ForumEntry> getMyAuthored()
	{
		return this.loadLocal("myauthor.sav");
	}

	/**
	 * Saves a List of ForumEntries as Favourites forum entries.
	 * <p>Called by the ForumEntryController when prompted by the BrowseActivity.
	 * 
	 * @param fel is the list to be saved.
	 *            
	 */
	public void setFavourites(ArrayList<ForumEntry> fel)
	{
		this.saveLocal(fel, "favourites.sav");
	}

	/**
	 * Load the List of ForumEntries marked as Favourites.
	 * <p> Called by the BrowseActivity when viewing Favourites to populate list. Also called before saving new entries. 
	 * 
	 * @return The List<ForumEntry> marked as read later.
	 */
	public ArrayList<ForumEntry> getFavourites()
	{
		return this.loadLocal("favourites.sav");
	}

	/**
	 * this is for Testing purposes only
	 * Its too see if a forumEntry is successfully added 
	 * @return forumEntryTest
	 * 
	 */

	public ForumEntry getForumEntry() {
		// TODO Auto-generated method stub
		return forumEntryTest;
	}

	


}
