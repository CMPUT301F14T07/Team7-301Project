package ca.ualberta.cs.f14t07_application.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

public class SearchTest extends ActivityInstrumentationTestCase2<BrowseActivity> {

	private BrowseActivity testActivity;
	
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
		testButton = null;
	}
	
    public SearchTest(){
    	super(BrowseActivity.class);
    }
    

    public void SearchTermTest(){
    	String searchTerm = "foo";
    	SearchText = (EditText) SearchActivity.findViewById(com.example.f14t07_application.activity_searchactivity.R.id.SearchText);
    	SearchText.setText("foo");
    
    	SearchButton = (Button) SearchActivity.findViewById(com.example.f14t07_application.activity_searchactivity.R.id.SearchButton);
    	SearchButton.performClick();
    	
    	Intent newIntent = getStartedActivityIntent();
    	Intent searchIntent = new Intent(SearchTest.this, SearchActivity.class);
    	assertTrue(newIntent.filterEquals(searchIntent));

    	String inputedTerm = SearchTerm();
    	assertEquals(inputedTerm,searchTerm);	
    }

    public void sortBySearchTerm(){
    	long time=123456789;
    	ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
		posts.add(new ForumEntry("no term","author1",Date(time)));
		posts.add(new ForumEntry("still no term","author2",Date(time)));
		posts.add(new ForumEntry("has foo!","author3",Date(time))) ;
		posts.add(new ForumEntry("has foo foo twice!","author4",Date(time))); //If it has the search term twice, it should probably be above? (Tested in sortedList2)
		(new DataManager()).save(posts);
		
		ArrayList<ForumEntry> sortedList = new ArrayList<ForumEntry>();
		sortedList.add(new ForumEntry("has foo!", "author3",time));
		sortedList.add(new ForumEntry("has foo foo twice!","author4",time));
		sortedList.add(new ForumEntry("no term","author1",time));
		sortedList.add(new ForumEntry("still no term","author2",time));
		
		assertEquals(posts, sortedList);
		
		testActivity.getBrowseControllerForTesting().sortBySearchTerm();

		ArrayList<ForumEntry> testList = (new DataManager()).load();

		assertEquals(posts.getlist(), sortedList);
    }

}
