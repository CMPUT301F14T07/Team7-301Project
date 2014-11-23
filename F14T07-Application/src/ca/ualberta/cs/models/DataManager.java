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
import ca.ualberta.cs.views.AskActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Model class for the data saved in memory and/or the remote server.
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
	 * Deprecated. Do not use. Use DataManager() instead.
	 * 
	 * @param ctx
	 */
	public DataManager(Context ctx_)
	{
		gson = new Gson();
		ctx=ctx_;
	}

	/**
	 * adds a new forum entry to the system
	 * 
	 * @param forum
	 *            entry
	 * */
	public void addForumEntry(ForumEntry forumEntry)
	{
		if(isOnline()){
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
					for(int i = 0; i<str_array.length;i++){
						System.err.println(str_array[i]);
					}
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
			saveToPush();
		}
		forumEntryTest = forumEntry;
		
		
	}
	public void saveToPush(){ 
		
	}

	public Boolean isOffline()
	{
		return false;
	}

	public Boolean isOnline()
	{
	     ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

	  // test for connection
	          if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable()
	                  && cm.getActiveNetworkInfo().isConnected()) {
	              return true;
	          } else {
	              return false;
	          }
	}
	
	public void updateForumEntry(ForumEntry forumEntry) {
		RESOURCE_URL=RESOURCE_URL+ "/"+ forumEntry.getId();
		
		addForumEntry(forumEntry);
		RESOURCE_URL=ORIGINAL_RESOURCE_URL;
	}

	/**
	 * Save the List<ForumEntry> as read later forum entries.
	 * 
	 * @param fel
	 *            The List<ForumEntry> to save.
	 */
	public void setReadLater(ArrayList<ForumEntry> fel)
	{
		Context ctx = ContextSingleton.getInstance().getContext();
		try
		{
			FileOutputStream fos = ctx.openFileOutput("readlater.sav",
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
	 * Load the List<ForumEntry> marked as read later.
	 * 
	 * @return The List<ForumEntry> marked as read later.
	 */
	public ArrayList<ForumEntry> getReadLater()
	{
		ArrayList<ForumEntry> fel = new ArrayList<ForumEntry>();
		Context ctx = ContextSingleton.getInstance().getContext();

		try
		{
			BufferedReader fis = new BufferedReader(new InputStreamReader(
					ctx.openFileInput("readlater.sav")));
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
	 * Save the List<ForumEntry> that the user has authored.
	 * 
	 * @param fel
	 *            The List<ForumEntry> to save.
	 */
	public void setMyAuthored(List<ForumEntry> fel)
	{
		Context ctx = ContextSingleton.getInstance().getContext();
		if (ctx == null)
		{
			return;
		} 
	}

	/**
	 * Load the List<ForumEntry> that the user has authored.
	 * 
	 * @return The List<ForumEntry> the user has authored.
	 */
	public ArrayList<ForumEntry> getMyAuthored()
	{
		Context ctx = ContextSingleton.getInstance().getContext();
		if (ctx == null)
		{
			return new ArrayList<ForumEntry>();
		}
		return new ArrayList<ForumEntry>();
	}

	/**
	 * Save the List<ForumEntry> that the user has selected as favourites.
	 * 
	 * @param fel
	 *            List<ForumEntry> to save.
	 */
	public void setFavourites(ArrayList<ForumEntry> fel)
	{
		Context ctx = ContextSingleton.getInstance().getContext();
		try
		{
			FileOutputStream fos = ctx.openFileOutput("favourites.sav",
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
	 * Load the List<ForumEntry> that contains the users favourites.
	 * 
	 * @return The ForumEntryList the user has favourited.
	 */
	public ArrayList<ForumEntry> getFavourites()
	{
		ArrayList<ForumEntry> fel = new ArrayList<ForumEntry>();
		Context ctx = ContextSingleton.getInstance().getContext();

		try
		{
			BufferedReader fis = new BufferedReader(new InputStreamReader(
					ctx.openFileInput("favourites.sav")));
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
	 * 
	 * gets a forum entry for tests
	 * 
	 * @return forum entry
	 */
	public ForumEntry getForumEntry()
	{
		return forumEntryTest;
	}

	/**
	 * loads the saved forum entries for testing
	 */
	public ForumEntryList load()
	{
		ForumEntryList fe = null;

		return fe;
	}

	public void saveAuthor(String author)
	{
		// TODO Auto-generated method stub

	}


}
