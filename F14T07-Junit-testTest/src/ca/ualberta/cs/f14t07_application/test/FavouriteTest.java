package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.views.FavouriteActivity;
import ca.ualberta.cs.views.QuestionActivity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

public class FavouriteTest extends ActivityInstrumentationTestCase2<FavouriteActivity> {

	private DataManager datamanager;
	private Context context;
	private FavouriteActivity testActivity;
	
	public FavouriteTest(Class<FavouriteActivity> activityClass) {
		super(FavouriteActivity.class);
		datamanager = new DataManager();
	}


	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		/* Turns off the touch screen in the emulator. This must be done to test features that
		 * would require the user to touch something on the screen.
		 */
		setActivityInitialTouchMode(false);
		/* Get an instance of the activity which is running
		 */

		testActivity = getActivity();
		
		/* Reset the testButton - do this so consecutive tests don't accidentally test
		 * the same button.
		 */
		//testButton = null;

	}
	
	public void testIsSaved() {
		/* Create some forum entries and save them using the data manager.
		 * Pretend that these already existed and were saved to memory/jsong
		 * a long time ago.
		 */

		DataManager dm = new DataManager();
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
}
