package ca.ualberta.cs.f14t07_application.test;

import ca.ualberta.cs.views.AskActivity;
import ca.ualberta.cs.views.BrowseActivity;
import ca.ualberta.cs.views.MainScreenActivity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

public class AskUITest extends ActivityInstrumentationTestCase2<MainScreenActivity> {
	
	MainScreenActivity testActivity;
	public AskUITest()
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
	
	public void testAskButton() {
		final MainScreenActivity m = getActivity();
		final Button askButton = (Button) m.findViewById(ca.ualberta.cs.f14t07_application.R.id.askButton);
		
		m.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
				askButton.performClick();
				Intent newIntent = m.intent2;
				Intent AskIntent = new Intent(m, AskActivity.class);
				assertTrue(newIntent.filterEquals(AskIntent));
			}
		});
	}

}
