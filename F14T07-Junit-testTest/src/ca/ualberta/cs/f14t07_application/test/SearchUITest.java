package ca.ualberta.cs.f14t07_application.test;

import ca.ualberta.cs.views.MainScreenActivity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

public class SearchUITest extends ActivityInstrumentationTestCase2<MainScreenActivity> {
	MainScreenActivity testActivity;
	public SearchUITest()
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
	
	public void testSearchButton() {
		final MainScreenActivity m = getActivity();
		final Button searchButton = (Button) m.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchButton);
		final EditText searchTerm = (EditText) m.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchTerm);
		
		m.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
				searchTerm.setText("foo");
				searchButton.performClick();
				Intent newIntent = m.intent2;
				//Intent SearchIntent = new Intent(m,SearchActivity.class);
				
				//assertTrue(newIntent.filterEquals(SearchIntent));


				//SearchActivity s = new SearchActivity();
				//EditText searchScreenTerm = (EditText) s.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchTextInput);
				String searchString = searchTerm.getText().toString();
				//String searchScreenString = searchScreenTerm.getText().toString();
		
				//assertEquals(searchString, searchScreenString);
	}
});
	}
		
}
