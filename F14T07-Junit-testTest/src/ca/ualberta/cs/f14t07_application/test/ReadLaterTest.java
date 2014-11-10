package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.views.QuestionActivity;
import ca.ualberta.cs.views.ReadLaterActivity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.Button;
import junit.framework.TestCase;

public class ReadLaterTest extends ActivityInstrumentationTestCase2<ReadLaterActivity> {

	private DataManager datamanager;
	private Context ctx;
	private ReadLaterActivity testActivity;
	
	public ReadLaterTest() {
		super(ReadLaterActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		ctx = testActivity.getApplicationContext();
		datamanager = new DataManager(ctx);
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
	

	// Test for u27
	public void testViewReadLater() throws Throwable {
		ForumEntryList fel = new ForumEntryList();
		ArrayList<ForumEntry> testList = new ArrayList<ForumEntry>();
		ForumEntry exampleEntry = new ForumEntry("subject","What is life?","Kibbles");
		testList.add(exampleEntry);
		fel.setFavourites(testList);
		datamanager.saveLocally(fel); 
    	 
		runTestOnUiThread(new Runnable() {
			@Override
			public void run() {
				datamanager.loadFavourites();
				
			}
		});
		ViewAsserts.assertOnScreen(testActivity.getWindow().getDecorView(), getActivity().getView());
		
	}
}
