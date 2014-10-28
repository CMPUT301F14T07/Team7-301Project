package ca.ualberta.cs.f14t07_application.test;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.f14t07_application.AskActivity;
import ca.ualberta.cs.f14t07_application.BrowseActivity;
import ca.ualberta.cs.f14t07_application.MainScreenActivity;
import ca.ualberta.cs.f14t07_application.SearchActivity;


public class MainScreenUITest extends ActivityInstrumentationTestCase2<MainScreenActivity> {

	public MainScreenUITest() {
		super(MainScreenActivity.class);
		// TODO Auto-generated constructor stub
	}

	public void AskButtonTest() {
		MainScreenActivity m = getActivity();
		Button askButton = (Button) m.findViewById(ca.ualberta.cs.f14t07_application.R.id.askButton);
		
		askButton.performClick();
		Intent newIntent = getStartedActivityIntent();
		Intent AskIntent = new Intent(MainScreenUITest.this, AskActivity.class);
		
		assertTrue(newIntent.filterEquals(AskIntent));
	}

	public void BrowseButtonTest() {
		MainScreenActivity m = getActivity();
		Button browseButton = (Button) m.findViewById(ca.ualberta.cs.f14t07_application.R.id.browseButton);
		
		browseButton.performClick();
		Intent newIntent = getStartedActivityIntent();
		Intent BrowseIntent = new Intent(MainScreenUITest.this, BrowseActivity.class);
		
		
		assertTrue(newIntent.filterEquals(BrowseIntent));
	}

	public void SearchButtonTest() {
		MainScreenActivity m = getActivity();
		Button searchButton = (Button) m.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchButton);
		EditText searchTerm = (EditText) m.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchTerm);
		
		searchTerm.setText("foo");
		searchButton.performClick();
		
		Intent newIntent = getStartedActivityIntent();
		Intent SearchIntent = new Intent(MainScreenUITest.this,SearchActivity.class);
		
		assertTrue(newIntent.filterEquals(SearchIntent));
		

		SearchActivity s = new SearchActivity();
		EditText searchScreenTerm = (EditText) s.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchTextInput);
		String searchString = searchTerm.getText().toString();
		String searchScreenString = searchScreenTerm.getText().toString();
		
		assertEquals(searchString, searchScreenString);
	}


	
}
