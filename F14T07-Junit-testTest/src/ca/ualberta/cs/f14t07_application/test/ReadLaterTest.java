package ca.ualberta.cs.f14t07_application.test;

import ca.ualberta.cs.f14t07_application.DataManager;
import ca.ualberta.cs.f14t07_application.ForumEntry;
import ca.ualberta.cs.f14t07_application.ForumEntryList;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;

public class ReadLaterTest extends ActivityInstrumentationTestCase2 {

	private DataManager datamanager;
	private Context context;
	
	public ReadLaterTest(Class activityClass) {
		super(activityClass);
		datamanager = new DataManager(getActivity().getApplicationContext());
	}
	
	public void isSavedTest() {
		ForumEntryList questions = new ForumEntryList();
		questions.add(new ForumEntry("What is life?","Kibbles"));
		datamanager.save(questions);
		ForumEntryList check = new ForumEntryList();
		check = datamanager.load();
		check.equals(questions);
	}
}
