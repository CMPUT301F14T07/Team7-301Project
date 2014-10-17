package ca.ualberta.cs.f14t07_application.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

public class SearchTest extends ActivityInstrumentationTestCase2<BrowseActivity> {

	private BrowseActivity testActivity;
	
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
    	ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
		posts.add(new ForumEntry("no term","author1"));
		posts.add(new ForumEntry("still no term","author2"));
		posts.add(new ForumEntry("has foo!","author3")) ;
		posts.add(new ForumEntry("has foo foo twice!","author4")); //If it has the search term twice, it should probably be above? (Tested in sortedList2)
		(new DataManager()).save(posts);
		
		ArrayList<ForumEntry> sortedList = new ArrayList<ForumEntry>();
		sortedList.add(new ForumEntry("has foo!", "author3"));
		sortedList.add(new ForumEntry("has foo foo twice!","author4"));
		sortedList.add(new ForumEntry("no term","author1"));
		sortedList.add(new ForumEntry("still no term","author2"));
		
		assertEquals(posts.getlist(), sortedList);
		
		testActivity.getBrowseControllerForTesting().sortBySearchTerm();

		ArrayList<ForumEntry> testList = (new DataManager()).load();

		assertEquals(posts.getlist(), sortedList);
    }

}
