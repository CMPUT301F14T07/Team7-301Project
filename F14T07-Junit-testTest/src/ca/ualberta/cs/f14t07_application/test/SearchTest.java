package ca.ualberta.cs.f14t07_application.test;

import android.test.ActivityInstrumentationTestCase2;

public class SearchTest extends ActivityInstrumentationTestCase2<SearchActivity> {

    public SearchTest(){
    	super(SearchActivity.class);
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

    public void sortBySearchTerm()){
    	ForumEntryList posts = new ForumEntryList;
		posts.add(new ForumEntry("no term"));
		posts.add(new ForumEntry("still no term"));
		posts.add(new ForumEntry("has foo!"));
		posts.add(new ForumEntry("has foo foo twice!")); //If it has the search term twice, it should probably be above? (Tested in sortedList2)
		posts.sortBySearchTerm("foo");

		ArrayList<ForumEntry> sortedList = new ArrayList<ForumEntry>();
		sortedList.add(new ForumEntry("has foo!"));
		sortedList.add(new ForumEntry("has foo foo twice!"));
		sortedList.add(new ForumEntry("no term"));
		sortedList.add(new ForumEntry("still no term"));
		assertEquals(posts.getlist(), sortedList);
		/*
		ArrayList<ForumEntry> sortedList2 = new ArrayList<ForumEntry>();
		sortedList2.add(new ForumEntry("has foo foo twice!"));
		sortedList2.add(new ForumEntry("has foo!"));
		sortedList2.add(new ForumEntry("no term"));
		sortedList2.add(new ForumEntry("still no term"));
		assertEquals(posts.getlist(), sortedList2);*/
    }

}
