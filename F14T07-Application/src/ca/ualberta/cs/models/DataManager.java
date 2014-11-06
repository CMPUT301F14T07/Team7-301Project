package ca.ualberta.cs.models;



import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;


import com.google.gson.Gson;


// utility class that saves and loads postList 
public class DataManager extends Activity {
	private static final String SEARCH_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t07/ForumEntry/_search";
	//change this one for ours 
	private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t07/ForumEntry";
	private static final String TAG = "ForumEntrySearch";
	
	private static final String FILENAME = "saveQuestion.sav";
	
	private Gson gson;
	private ForumEntry forumEntryTest;
	public DataManager() {
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
	//private Context context;
	/*
	public DataManager(Context appContext) {
		context = appContext;
	}

		public ForumEntryList load() {
			ForumEntryList forumEntryList = new ForumEntryList();
			try {
				FileInputStream fis = context.openFileInput(FILENAME);
				ObjectInputStream ois = new ObjectInputStream(fis);
				forumEntryList = (ForumEntryList) ois.readObject();
				fis.close();
				ois.close();
			} catch (Exception e) {
				Log.i("Kibbles", "Error casting");
				e.printStackTrace();
			} 
			return forumEntryList;
		}
		
		public void save(ForumEntryList forumEntryList) {
			try {
				FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(forumEntryList);
				fos.close();
				oos.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}*/ 

	public void saveAuthor(String author) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<ForumEntry> loadLocallySaved() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteLocalAll() {
		// TODO Auto-generated method stub
		
	}
}
	class AddThread extends Thread{ 
		private ForumEntry forumEntry;
		private DataManager dataManager= new DataManager();
		
		public AddThread(ForumEntry forumEntry_){
			forumEntry=forumEntry_;
		}
		
		@Override 
		public void run(){ 
			super.run();
			dataManager.addForumEntry(forumEntry);
			
			try{ 
				Thread.sleep(500);
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

