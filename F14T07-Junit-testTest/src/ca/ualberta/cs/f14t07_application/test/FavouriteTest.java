package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
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
	
	public FavouriteTest(Class<FavouriteActivity> activityClass) {
		super(FavouriteActivity.class);
		ctx = testActivity.getApplicationContext();
		datamanager = new DataManager(ctx);
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
		testActivity =  getActivity();
	}
	
	// Test for u29
	
	public void testViewFavourite() throws Throwable {
		ForumEntry exampleEntry = new ForumEntry("subject","What is life?","Kibbles");
    	datamanager.saveFavourite(exampleEntry); 
    	 
		runTestOnUiThread(new Runnable() {
			@Override
			public void run() {
				datamanager.loadFavourites();
				
			}
		});
		ViewAsserts.assertOnScreen(testActivity.getWindow().getDecorView(), getActivity().getView());
		
	}
}
