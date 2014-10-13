package ca.ualberta.cs.f14t07_application;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.util.Log;


// utility class that saves and loads postList 
public class DataManager {

	private static final String FILENAME = "saveQuestion.sav";
	
	private Context context;
	
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
		}
}
