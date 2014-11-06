package ca.ualberta.cs.f14t07_application.test;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.controllers.AuthorController;
import ca.ualberta.cs.models.AuthorModel;
import ca.ualberta.cs.views.AskActivity;
import ca.ualberta.cs.views.BrowseActivity;
import ca.ualberta.cs.views.MainScreenActivity;
import ca.ualberta.cs.views.SearchActivity; 


public class MainScreenUITest extends ActivityInstrumentationTestCase2<MainScreenActivity> {

	public MainScreenUITest() {
		super(MainScreenActivity.class);
		// TODO Auto-generated constructor stub
	}

	public void testAskButton() {
		MainScreenActivity m = getActivity();
		Button askButton = (Button) m.findViewById(ca.ualberta.cs.f14t07_application.R.id.askButton);
		
		askButton.performClick();
		Intent newIntent = m.intent2;
		Intent AskIntent = new Intent(m, AskActivity.class);
		assertTrue(newIntent.filterEquals(AskIntent));
	}

	public void testBrowseButton() {
		MainScreenActivity m = getActivity();
		Button browseButton = (Button) m.findViewById(ca.ualberta.cs.f14t07_application.R.id.browseButton);
		
		browseButton.performClick();
		Intent newIntent = m.intent2;
		Intent BrowseIntent = new Intent(m, BrowseActivity.class);
		
		
		assertTrue(newIntent.filterEquals(BrowseIntent));
	}

	public void testSearchButton() {
		MainScreenActivity m = getActivity();
		Button searchButton = (Button) m.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchButton);
		EditText searchTerm = (EditText) m.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchTerm);
		
		searchTerm.setText("foo");
		searchButton.performClick();
		
		Intent newIntent = m.intent2;
		Intent SearchIntent = new Intent(m,SearchActivity.class);
		
		assertTrue(newIntent.filterEquals(SearchIntent));
		

		SearchActivity s = new SearchActivity();
		EditText searchScreenTerm = (EditText) s.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchTextInput);
		String searchString = searchTerm.getText().toString();
		String searchScreenString = searchScreenTerm.getText().toString();
		
		assertEquals(searchString, searchScreenString);
	}
	
	public void testSetUsername(){
		String author="John";
		AuthorController ac= new AuthorController(getActivity());
		ac.setSessionAuthor(author);
		
		assertEquals((new AuthorModel()).getSessionAuthor(), author);
	}


	
}
