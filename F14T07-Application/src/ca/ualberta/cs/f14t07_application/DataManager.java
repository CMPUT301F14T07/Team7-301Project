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

		public PostList load() {
			PostList postList = new PostList();
			try {
				FileInputStream fis = context.openFileInput(FILENAME);
				ObjectInputStream ois = new ObjectInputStream(fis);
				postList = (PostList) ois.readObject();
				fis.close();
				ois.close();
			} catch (Exception e) {
				Log.i("Kibbles", "Error casting");
				e.printStackTrace();
			} 
			return postList;
		}
		
		public void save(PostList postList) {
			try {
				FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(postList);
				fos.close();
				oos.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
}
