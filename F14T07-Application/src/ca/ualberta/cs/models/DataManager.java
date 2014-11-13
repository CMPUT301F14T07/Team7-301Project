package ca.ualberta.cs.models;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Model class for the data saved in memory and/or the remote server.
 * 
 * @author lexie
 */
public class DataManager
{
	private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t07/ForumEntry";
	private static final String TAG = "ForumEntrySearch";
	private static final String FILENAME = "saveQuestion.sav";

	private Context ctx;
	private Gson gson;
	private ForumEntry forumEntryTest;

	public DataManager(Context ctx)
	{
		gson = new Gson();
	}

	public void addReplyToEntry(ForumEntry forumEntry, String s)
	{

	}

	/**
	 * adds a new forum entry to the system
	 * 
	 * @param forum
	 *            entry
	 * */
	public void addForumEntry(ForumEntry forumEntry)
	{
		gson = new Gson();
		HttpClient httpClient = new DefaultHttpClient();

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
				}
			} catch (IOException e)
			{
				System.err.println(e);
			}
		} catch (Exception e)
		{

			e.printStackTrace();
		}
		forumEntryTest = forumEntry;
	}

	/**
	 * gets a forum entry for tests
	 * 
	 * @return forum entry
	 */
	public ForumEntry getForumEntry()
	{
		return forumEntryTest;
	}

	/**
	 * loads the saved forum entries
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

	/**
	 * saves the forum entry list
	 * 
	 * @param forumEntryList
	 */
	public void saveLocally(ForumEntryList fel)
	{
		try
		{
			FileOutputStream fos = ctx.openFileOutput("read_later.sav",
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
	 * loads locally saved list
	 * 
	 * @return the locally saved forum entry list
	 */
	public ForumEntryList loadLocallySaved()
	{
		ForumEntryList fe = new ForumEntryList();

		try
		{
			BufferedReader fis = new BufferedReader(new InputStreamReader(
					ctx.openFileInput("read_later.sav")));
			String line;
			StringBuffer fileContent = new StringBuffer();

			while ((line = fis.readLine()) != null)
			{
				fileContent.append(line);
			}

			Type collectionType = new TypeToken<Collection<ForumEntry>>()
			{
			}.getType();

			fe = gson.fromJson(fileContent.toString(), collectionType);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return fe;
	}

	public void deleteLocalAll()
	{
		// TODO Auto-generated method stub

	}

	/**
	 * loads the favourites
	 * 
	 * @return forum entry list
	 */
	public ForumEntryList loadFavourites()
	{
		ForumEntryList fe = new ForumEntryList();

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

			fe = gson.fromJson(fileContent.toString(), collectionType);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return fe;
	}

	/**
	 * saves favourites
	 * 
	 * @param forum
	 *            entry list
	 */
	public void saveFavourite(ForumEntryList fel)
	{
		try
		{
			FileOutputStream fos = ctx.openFileOutput("favourites",
					Context.MODE_PRIVATE);
			String json = gson.toJson(fel);
			fos.write(json.getBytes());
			fos.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public Boolean isOffline()
	{
		return false;
	}

	public Boolean isOnline()
	{
		return false;
	}
}

