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
	private Context context;
	private ReadLaterActivity testActivity;
	
	public ReadLaterTest(Class activityClass) {
		super(ReadLaterActivity.class);
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
	

	// Test for u27
	public void testViewReadLater() throws Throwable {
		ForumEntry exampleEntry = new ForumEntry("subject","What is life?","Kibbles");
    	datamanager.saveLocally(exampleEntry); 
    	 
		runTestOnUiThread(new Runnable() {
			@Override
			public void run() {
				datamanager.loadLocallySaved();
				
			}
		});
		ViewAsserts.assertOnScreen(testActivity.getWindow().getDecorView(), getActivity().getView());
		
	}
	
}
