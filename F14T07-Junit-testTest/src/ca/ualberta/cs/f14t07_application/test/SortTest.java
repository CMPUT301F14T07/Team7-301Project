package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import ca.ualberta.cs.f14t07_application.ForumEntry;
import ca.ualberta.cs.f14t07_application.PostList;
import ca.ualberta.cs.f14t07_application.Question;

import android.test.ActivityInstrumentationTestCase2;

/** 
 * This class tests the different sorting algorithms. These tests are
 * done by creating a few forum entries, saving them, instantiating
 * a browse controller, and calling its sorting functions. Finally
 * the forum entries are loaded from memory and compared to the order
 * they ought to appear in.
 * @author Marcin, Lexie, Brendan
 */
public class SortTest extends ActivityInstrumentationTestCase2<BrowseActivity> {
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
	public SortTest(){
		super(BrowseActivity.class);
	}
	
	public void sortByTimeTest(){
		ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
		posts.add(new ForumEntry("test 1","author1"));
		Thread.sleep(1000);
		posts.add(new ForumEntry("test 2","author2"));
		Thread.sleep(1000);
		posts.add(new ForumEntry("test 3","author3"));
		(new DataManager()).save(posts);
		
		//set time using index, day month year hour minute
		posts.get(0).getDate().setTime(time+3);
		posts.get(1).getDate().setTime(time+2);
		posts.get(2).getDate().setTime(time);
		
		(new DataManager()).save(posts);
		
		(new BrowseController()).sortByTime();
		
		ForumEntryList testList= new ForumEntryList();
		testList.add(new ForumEntry("test 3","author3"));
		testList.add(new ForumEntry("test 2","author2"));
		testList.add(new ForumEntry("test 1","author1"));
		
		assertEquals(posts,testList);
		
		}
	
	public void sortByRatingTest(){
		ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
		posts.add(new ForumEntry("test 1","author1"));
		posts.add(new ForumEntry("test 2","author2"));
		posts.add(new ForumEntry("test 3","author3"));
		
		posts.get(0).setUpVote(1);
		posts.get(1).setUpVote(5);
		posts.get(2).setUpVote(3);
		
		(new DataManager()).save(posts);
		
		(new BrowseController()).sortByRating();
		
		ForumEntryList testList= new ForumEntryList();
		testList.add(new ForumEntry("test 2","author2"));
		testList.add(new ForumEntry("test 3","author3"));
		testList.add(new ForumEntry("test 1","author1"));
		
		assertEquals(posts,testList);
		
		}
	
	public void sortByHasPictureTest(){
		ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
		posts.add(new ForumEntry("test 1","author1"));
		posts.add(new ForumEntry("test 2","author2"));
		posts.add(new ForumEntry("test 3","author3"));
		
		posts.get(1).addPicture(new Picture());
		
		posts.sortByHasPicture();
		
		(new DataManager()).save(posts);
		
		(new BrowseController()).soryByHasPicture();
		
		ForumEntryList testList= new ForumEntryList();
		testList.add(new ForumEntry("test 2","author2"));
		testList.add(new ForumEntry("test 1","author1"));
		testList.add(new ForumEntry("test 3","author3"));
		
		assertEquals(posts,testList);
		
		}
}


