package ca.ualberta.cs.f14t07_application.test;

import ca.ualberta.cs.f14t07_application.DataManager;
import ca.ualberta.cs.f14t07_application.PostList;
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
		PostList questions = new PostList();
		questions.add(new Question('What is life?','Kibbles'));
		datamanager.save(questions);
		PostList check = new PostList();
		check = datamanager.load();
		check.equals(questions);
	}
}
