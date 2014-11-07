package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.views.QuestionActivity;

public class QuestionSaveTest extends ActivityInstrumentationTestCase2<QuestionActivity> {

	private DataManager datamanager;
	private Context ctx;
	private QuestionActivity testActivity;
	
	public QuestionSaveTest(Class<QuestionActivity> activityClass) {
		super(activityClass);
		ctx = testActivity.getApplicationContext();
		datamanager = new DataManager(ctx);

	}

	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		testActivity = getActivity();
	}
	
	// Test for u28
	public void testSaveFavourite() {
		/* Create some forum entries and save them using the data manager.
		 * Pretend that these already existed and were saved to memory/jsong
		 * a long time ago.
		 */
		

		DataManager dm = new DataManager(ctx);
		ForumEntry exampleEntry = new ForumEntry("subject","What is life?","Kibbles");
    	dm.addForumEntry(exampleEntry);
	    
    	ArrayList<ForumEntry> testQuestions = new ArrayList<ForumEntry>();
    	testQuestions.add(exampleEntry);
    	
    	
		/* Get the button that will save a forum entry for offline viewing. */
		//Button favourite = (Button) testActivity.findViewById(ca.ualberta.cs.f14t07_application.R.id.FavouriteQuestion);
		//favourite.performClick();
		
		/* Now we need to check that this forum entry was saved locally */
		ArrayList<ForumEntry> loadCheck = new ArrayList<ForumEntry>();
		loadCheck = datamanager.loadFavourites();
		assertEquals("The entry saved is not equal to what was loaded", testQuestions, loadCheck);
		/* Clean up the run time environment */
		datamanager.deleteLocalAll();
	}
	
	// Test for u26
	public void testSaveReadLater() {
		/* Create some forum entries and save them using the data manager.
		 * Pretend that these already existed and were saved to memory/jsong
		 * a long time ago.
		 */

		DataManager dm = new DataManager(ctx);
		ForumEntry exampleEntry = new ForumEntry("subject","What is life?","Kibbles");
    	dm.addForumEntry(exampleEntry);
	    
    	ArrayList<ForumEntry> testQuestions = new ArrayList<ForumEntry>();
    	testQuestions.add(exampleEntry);
    	
    	
		/* Get the button that will save a forum entry for offline viewing. */
		//Button saveLater = (Button) testActivity.findViewById(ca.ualberta.cs.f14t07_application.R.id.ReadLater);
		/* Simulate a button click */
		//saveLater.performClick();
		
		/* Now we need to check that this forum entry was saved locally */
		ArrayList<ForumEntry> loadCheck = new ArrayList<ForumEntry>();
		loadCheck = datamanager.loadLocallySaved();
		assertEquals("The entry saved is not equal to what was loaded", testQuestions, loadCheck);
		/* Clean up the run time environment */
		datamanager.deleteLocalAll();
	}

}
