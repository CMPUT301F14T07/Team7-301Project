package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ca.ualberta.cs.controllers.BrowseController;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.views.SearchActivity;

public class SearchTest extends ActivityInstrumentationTestCase2<SearchActivity> {

	private SearchActivity testActivity;
	private Context ctx;
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		
		/* Turns off the touch screen in the emulator. This must be done to test features that
		 * would require the user to touch something on the screen.
		 */
		setActivityInitialTouchMode(true);
		
		/* Get an instance of the activity which is running
		 */
		testActivity = getActivity();
		ctx = testActivity.getApplicationContext();
		
	}
	
    public SearchTest(){
    	super(SearchActivity.class);
    }
    

    public void testSearchQuestion(){
    	String searchTerm = "foo";
		//MainScreenActivity m = new MainScreenActivity();
		SearchActivity s = getActivity();
    	final EditText SearchText = (EditText) s.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchTextInput);
    	
    	s.runOnUiThread(new Runnable(){
    		@Override
    		public void run(){
    			SearchText.setText("foo",TextView.BufferType.EDITABLE);
    			sendKeys(KeyEvent.KEYCODE_ENTER);
    		}
    	});
    	String inputedTerm = s.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchTextInput).toString();
    	assertEquals(inputedTerm,searchTerm);	
    }
    
    public void testSearchAnswer(){
    	String searchTerm = "bar";
		//MainScreenActivity m = getActivity();
		SearchActivity s = getActivity();
    	final EditText SearchText = (EditText) s.findViewById(ca.ualberta.cs.f14t07_application.R.id.searchTextInput);
  
    	
		s.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
    			SearchText.setText("bar",TextView.BufferType.EDITABLE);
    			sendKeys(KeyEvent.KEYCODE_ENTER);
			}
		});


    	String inputedTerm = SearchText.getText().toString();
    	assertEquals(inputedTerm,searchTerm);	
    }

    public void testSortBySearchTerm(){
    	ForumEntry f1 = new ForumEntry("subject","no term","author1");
    	ForumEntry f2 = new ForumEntry("subject","still no term","author2");
    	ForumEntry f3 = new ForumEntry("subject","has foo!","author3");
    	ForumEntry f4 = new ForumEntry("subject", "has foo foo twice!","author"); 
    	
    	ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
    	posts.add(f1);
    	posts.add(f2);
    	posts.add(f3);
    	posts.add(f4);
    	
		ArrayList<ForumEntry> sortedList = new ArrayList<ForumEntry>();
		sortedList.add(new ForumEntry("subject","has foo foo twice!","author4"));
		sortedList.add(new ForumEntry("subject","has foo!", "author3"));
		sortedList.add(new ForumEntry("subject","no term","author1"));
		sortedList.add(new ForumEntry("subject","still no term","author2"));
		
		BrowseController bc = new BrowseController(null);
		bc.sortBySearchTerm(posts);

		assertEquals(posts, sortedList);
    }
    

} 