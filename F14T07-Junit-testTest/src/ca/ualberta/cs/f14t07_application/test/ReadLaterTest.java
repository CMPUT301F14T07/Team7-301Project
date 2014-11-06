package ca.ualberta.cs.f14t07_application.test;

import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.views.QuestionActivity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import junit.framework.TestCase;

public class ReadLaterTest extends ActivityInstrumentationTestCase2<QuestionActivity> {

	private DataManager datamanager;
	private Context context;
	private QuestionActivity testActivity;
	
	public ReadLaterTest(Class activityClass) {
		super(QuestionActivity.class);
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
	
	public void isSavedTest() {
		/* Create some forum entries and save them using the data manager.
		 * Pretend that these already existed and were saved to memory/jsong
		 * a long time ago.
		 */

		DataManager dm = new DataManager();
    	dm.addForumEntry((new ForumEntry("subject","What is life?","Kibbles")));
	
		/* Get the button that will save a forum entry for offline viewing. */
		Button saveLater = (Button) testActivity.findViewById(ca.ualberta.cs.f14t07_application.R.id.ReadLater);
		/* Simulate a button click */
		saveLater.performClick();
		
		/* Now we need to check that this forum entry was saved locally */
		ArrayList<ForumEntry> check = new ArrayList<ForumEntry>();
		check = datamanager.loadLocallySaved();
		AssertEquals("The entry saved is not equal to what was loaded", questions, check);
		/* Clean up the run time environment */
		dataManager.deleteLocalAll();
	}
}
