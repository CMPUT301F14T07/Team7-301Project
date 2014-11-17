package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import junit.framework.Assert;

import ca.ualberta.cs.controllers.ForumEntryController;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.views.BrowseActivity;
import ca.ualberta.cs.views.QuestionActivity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.Button;

public class FavouriteTest extends ActivityInstrumentationTestCase2<BrowseActivity> {

	private DataManager datamanager;
	private Context ctx;
	private BrowseActivity testActivity;
	
	public FavouriteTest() {
		super(BrowseActivity.class);
	}


	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		testActivity =  getActivity();
		ctx = testActivity.getApplicationContext();
		datamanager = new DataManager(ctx);
		/* Turns off the touch screen in the emulator. This must be done to test features that
		 * would require the user to touch something on the screen.
		 */
		setActivityInitialTouchMode(false);
		/* Get an instance of the activity which is running
		 */

	}
	
	// Test for u29
	
	public void testViewFavourite() {
		ForumEntryController fec = new ForumEntryController(testActivity);
		final ArrayList<ForumEntry> testList = new ArrayList<ForumEntry>();
		final ForumEntry exampleEntry = new ForumEntry("subject","What is life?","Kibbles");
		ForumEntryController testFec = new ForumEntryController(testActivity);
	
		
		// sets the entry to be a favourite in both our copy and the original activity
		fec.saveFavouritesCopy(exampleEntry);
		testFec.saveFavouritesCopy(exampleEntry);
		
		
		assertEquals(fec.returnFavourite?, testFec.returnFavourite?);
		
		 
		testFec = datamanager.loadFavourites();
		assertEquals(fec, testFec);
		// test that after save and load it is still the same
		//assertEquals(fel, testFel);
		
		try {
			runTestOnUiThread(new Runnable() {
				@Override
				public void run() {


					// first check that 
					assertNotNull(testActivity.getWindow().getDecorView());
					
					ViewAsserts.assertOnScreen(testActivity.getWindow().getDecorView(), getActivity().getView());

					
				}
			});
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
}
