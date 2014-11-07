package ca.ualberta.cs.models;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

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


/**
 * Model class for the data saved in memory and/or the remote server.
 *
 *@author lexie
 */
public class DataManager {
	
	private static final String SEARCH_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t07/ForumEntry/_search";
	private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t07/ForumEntry";
	private static final String TAG = "ForumEntrySearch";
	private static final String FILENAME = "saveQuestion.sav";
	
	private Context ctx;
	private Gson gson;
	private ForumEntry forumEntryTest;
	
	public DataManager(Context ctx) {
		this.ctx = ctx;
		gson = new Gson();
	}
	
	public ForumEntry getForumEntry(int id) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(RESOURCE_URL + id);

		HttpResponse response;

		try {
			response = httpClient.execute(httpGet);
			//SearchHit<Movie> sr = parseMovieHit(response);
			//return sr.getSource();

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return null;
	}

	public void addReplyToEntry(ForumEntry forumEntry,String s){
		
	}
	public void addForumEntry(ForumEntry forumEntry) {
		HttpClient httpClient = new DefaultHttpClient();
		forumEntryTest = forumEntry;
		try {
			HttpPost addRequest = new HttpPost(RESOURCE_URL);

			StringEntity stringEntity = new StringEntity(gson.toJson(forumEntry));
			addRequest.setEntity(stringEntity);
			addRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(addRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ForumEntry getForumEntry(){ 
		return forumEntryTest;
	}
	
	public ForumEntryList load(){
		ForumEntryList fe = new ForumEntryList();
		
		try {
			BufferedReader fis = new BufferedReader(new InputStreamReader (ctx.openFileInput(FILENAME)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fe;
	}

	public void saveAuthor(String author) {
		// TODO Auto-generated method stub
		
	}

	public void saveLocally(ForumEntry forumEntry) {
		
	}
	
	public ArrayList<ForumEntry> loadLocallySaved() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteLocalAll() {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<ForumEntry> loadFavourites() {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveFavourite(ForumEntry forumEntry) {
		// TODO Auto-generated method stub
		
	}
	
	public Boolean isOffline(){ 
		return false;
	}
	
	public Boolean isOnline(){
		return false;
	}
}	

