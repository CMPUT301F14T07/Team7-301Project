package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.views.FavouriteActivity;
import ca.ualberta.cs.views.QuestionActivity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.Button;

public class FavouriteTest extends ActivityInstrumentationTestCase2<FavouriteActivity> {

	private DataManager datamanager;
	private Context ctx;
	private FavouriteActivity testActivity;
	
	public FavouriteTest() {
		super(FavouriteActivity.class);
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
		testActivity =  getActivity();
	}
	
	// Test for u29
	
	public void testViewFavourite() {
		final ForumEntryList fel = new ForumEntryList();
		final ArrayList<ForumEntry> testList = new ArrayList<ForumEntry>();
		final ForumEntry exampleEntry = new ForumEntry("subject","What is life?","Kibbles");
    	 
		try {
			runTestOnUiThread(new Runnable() {
				@Override
				public void run() {
					testList.add(exampleEntry);
					fel.setFavourites(testList);
					datamanager.saveFavourite(fel); 
					datamanager.loadFavourites();
					ViewAsserts.assertOnScreen(testActivity.getWindow().getDecorView(), getActivity().getView());

					
				}
			});
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
