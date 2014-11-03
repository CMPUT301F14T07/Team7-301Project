package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.controllers.BrowseController;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.views.MainScreenActivity;
import ca.ualberta.cs.views.SearchActivity;

public class SearchTest extends ActivityInstrumentationTestCase2<SearchActivity> {

	private SearchActivity testActivity;
	
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
		testActivity = getActivity();
		
		/* Reset the testButton - do this so consecutive tests don't accidentally test
		 * the same button.
		 */
		//testButton = null;
	}
	
    public SearchTest(){
    	super(SearchActivity.class);
    }
    

    public void SearchTermTest(){
    	String searchTerm = "foo";
		MainScreenActivity m = new MainScreenActivity();
		SearchActivity s = getActivity();
    	EditText SearchText = (EditText) m.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchTerm);
    	SearchText.setText("foo");
    
    	Button SearchButton = (Button) m.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchButton);
    	SearchButton.performClick();

    	String inputedTerm = s.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchTextInput).toString();
    	assertEquals(inputedTerm,searchTerm);	
    }

    public void sortBySearchTerm(){
    	DataManager dm = new DataManager();
    	dm.addForumEntry((new ForumEntry("subject","no term","author1")));
    	dm.addForumEntry((new ForumEntry("subject","still no term","author2")));
    	dm.addForumEntry((new ForumEntry("subject","has foo!","author3")));
    	dm.addForumEntry((new ForumEntry("subject", "has foo foo twice!","author"))); 
    	
		ArrayList<ForumEntry> sortedList = new ArrayList<ForumEntry>();
		sortedList.add(new ForumEntry("subject","has foo!", "author3"));
		sortedList.add(new ForumEntry("subject","has foo foo twice!","author4"));
		sortedList.add(new ForumEntry("subject","no term","author1"));
		sortedList.add(new ForumEntry("subject","still no term","author2"));
		
		BrowseController bc = new BrowseController();
		bc.sortBySearchTerm();
		ArrayList<ForumEntry> testList = (new DataManager()).loadGlobal();

		assertEquals(testList, sortedList);
    }

}
  