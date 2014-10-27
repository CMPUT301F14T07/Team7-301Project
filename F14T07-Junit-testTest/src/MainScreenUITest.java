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
		Button askButton = (Button) MainScreenActivity.findViewById(ca.ualberta.cs.f14t07_application.title_activity_main_screen.R.id.askButton);
		
		askButton.performClick();
		Intent newIntent = getStartedActivityIntent();
		Intent AskIntent = new Intent(MainScreenUITest.this,AskActivity.class);
		
		assertTrue(newIntent.filterEquals(AskIntent));
	}

	public void BrowseButtonTest() {
		Button browseButton = (Button) MainScreenActivity.findViewById(ca.ualberta.cs.f14t07_application.title_activity_main_screen.R.id.browseButton);
		
		browseButton.performClick();
		Intent newIntent = getStartedActivityIntent();
		Intent BrowseIntent = new Intent(MainScreenUITest.this, BrowseActivity.class);
		
		
		assertTrue(newIntent.filterEquals(BrowseIntent));
	}

	public void SearchButtonTest() {
		Button searchButton = (Button) MainScreenActivity.findViewById(ca.ualberta.cs.f14t07_application.title_activity_main_screen.R.id.searchButton);
		EditText searchTerm = (EditText) MainScreenActivity.findViewById(ca.ualberta.cs.f14t07_application.title_activity_main_screen.R.id.searchTerm);
		
		searchTerm.setText("foo");
		searchButton.performClick();
		
		Intent newIntent = getStartedActivityIntent();
		Intent SearchIntent = new Intent(MainScreenUITest.this,SearchActivity.class);
		
		assertTrue(newIntent.filterEquals(SearchIntent));
		
		
		EditText searchScreenTerm = (EditText) SearchActivity.findViewById(ca.ualberta.cs.f14t07_application.title_activity_search.R.id.searchTextInput);
		String searchString = searchTerm.getText().toString();
		String searchScreenString = searchScreenTerm.getText().toString();
		
		assertEquals(searchString, searchScreenString);
	}


	
}
