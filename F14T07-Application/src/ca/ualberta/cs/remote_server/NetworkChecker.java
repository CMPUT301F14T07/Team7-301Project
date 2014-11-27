package ca.ualberta.cs.remote_server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.net.ConnectivityManager;
import ca.ualberta.cs.intent_singletons.ContextSingleton;
import ca.ualberta.cs.models.Answer;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;

/**
 * Checks whether the device is hooked up to 
 * a network. 
 * If a question is asked with no network it saves
 * it to a file instead 
 * 
 * If a network connection is created it adds them to 
 * the elastic search server
 * 
 * @author Lexie
 */
public class NetworkChecker {
	
	private Boolean isOnline;
	private String FILENAME = "pushOnline.sav";
	private Gson gson;
	
	public NetworkChecker(){
		gson = new Gson();
		checkStatus();
	}
	public Boolean getIsOnline(){
		return isOnline;
	}
	public void dealWithNetworkStuff(){
		checkStatus();
		if(isOnline){
			AddThread addThread = new AddThread();
			addThread.start();
		}
	}
	public void checkStatus()
	{
		Context ctx = ContextSingleton.getInstance().getContext();
	    ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

	  // test for connection
	          if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable()
	                  && cm.getActiveNetworkInfo().isConnected()) {
	              isOnline = true;
	          } else {
	              isOnline = false;
	          }
	}
	public void saveForLater(ForumEntry forumEntry){
		Context ctx = ContextSingleton.getInstance().getContext();
		try
		{
			FileOutputStream fos = ctx.openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			String json = gson.toJson(forumEntry);
			fos.write(json.getBytes());
			fos.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void addOnline(){
		ArrayList<ForumEntry> fel = new ArrayList<ForumEntry>();
		Context ctx = ContextSingleton.getInstance().getContext();
		DataManager dm = new DataManager();
		
		try {
			FileOutputStream fos;
			fos = ctx.openFileOutput(FILENAME,Context.MODE_PRIVATE);
			
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			
			Gson gson=new GsonBuilder().create();
			gson.toJson(fel,osw);
			osw.flush();
			osw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(fel.size()!=0){
			for(int i = 0; i<fel.size(); i++){
				dm.addForumEntry(fel.get(i));
			}
		}
	}
	
	class AddThread extends Thread
	{
		public AddThread()
		{
		}
		
		@Override
		public void run()
		{
			addOnline();
		}
	}
}
