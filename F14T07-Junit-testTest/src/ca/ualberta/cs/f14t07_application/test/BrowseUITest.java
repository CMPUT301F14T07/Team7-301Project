package ca.ualberta.cs.f14t07_application.test;

import ca.ualberta.cs.views.BrowseActivity;
import ca.ualberta.cs.views.MainScreenActivity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

public class BrowseUITest extends ActivityInstrumentationTestCase2<MainScreenActivity> {
	MainScreenActivity testActivity;
	public BrowseUITest()
	{
		super(MainScreenActivity.class);
	}
	protected void setUp() throws Exception {
		super.setUp();		
		setActivityInitialTouchMode(false);

		/* Get an instance of the activity which is running
		 */
		testActivity = getActivity();
		
	}
	
	public void testBrowseButton() {
		final MainScreenActivity m = getActivity();
		final Button browseButton = (Button) m.findViewById(ca.ualberta.cs.f14t07_application.R.id.browseButton);
		m.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
				browseButton.performClick();
				Intent newIntent = m.intent2;
				Intent BrowseIntent = new Intent(m, BrowseActivity.class);
				
				
				assertTrue(newIntent.filterEquals(BrowseIntent));
			}
		});
	}
}
