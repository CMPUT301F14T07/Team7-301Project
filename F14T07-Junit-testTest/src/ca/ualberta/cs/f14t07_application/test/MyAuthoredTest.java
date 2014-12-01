package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import ca.ualberta.cs.controllers.ForumEntryController;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.views.BrowseActivity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;

public class MyAuthoredTest extends ActivityInstrumentationTestCase2<BrowseActivity> {

	private DataManager datamanager;
	private Context ctx;
	private BrowseActivity testActivity;
	
	public MyAuthoredTest() {
		super(BrowseActivity.class);
	}


	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		testActivity =  getActivity();
		ctx = testActivity.getApplicationContext();
		datamanager = new DataManager();
		/* Turns off the touch screen in the emulator. This must be done to test features that
		 * would require the user to touch something on the screen.
		 */
		setActivityInitialTouchMode(false);
		/* Get an instance of the activity which is running
		 */

	}
	
	// Test for u32
	
	public void testViewMyAuthored() {
		ForumEntryController fec = new ForumEntryController(testActivity);
		ArrayList<ForumEntry> testList = new ArrayList<ForumEntry>();
		ForumEntry exampleEntry = new ForumEntry("subject","What is life?","Kibbles");
		exampleEntry.setId("AF@Q$#WFSVXCZv");
		ArrayList<ForumEntry> compareList = new ArrayList<ForumEntry>();

		// sets the entry to be a favourite in both our copy and the original activity
		testList.add(exampleEntry);
		datamanager.setMyAuthored(testList);
		 
		compareList = datamanager.getMyAuthored();
		assertEquals(testList.get(0), compareList.get(0));
		
		try {
			runTestOnUiThread(new Runnable() {
				@Override
				public void run() {

					assertNotNull(testActivity.getWindow().getDecorView());
					
					ViewAsserts.assertOnScreen(testActivity.getWindow().getDecorView(), getActivity().getView());

					
				}
			});
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
}

