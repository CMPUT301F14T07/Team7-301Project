package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import ca.ualberta.cs.f14t07_application.PostList;
import ca.ualberta.cs.f14t07_application.Question;

import android.test.ActivityInstrumentationTestCase2;


public class SortTest extends ActivityInstrumentationTestCase2<BrowseActivity> {
	/* TODO: The Class generic that goes with ActivityInstrumentationTestCase2 is
	 * an activity class - the activity which will be active when the test cases
	 * are called.
	 */
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
		long time=123456789;
		ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
		posts.add(new ForumEntry("test 1","author1",new Date(time)));
		posts.add(new ForumEntry("test 2","author2",new Date(time)));
		posts.add(new ForumEntry("test 3","author3",new Date(time)));
		(new DataManager()).save(posts);
		
		//set time using index, day month year hour minute
		posts.get(0).getDate().setTime(time+3);
		posts.get(1).getDate().setTime(time+2);
		posts.get(2).getDate().setTime(time);
		
		(new DataManager()).save(posts);
		
		testActivity.getBrowseControllerForTesting().sortByTime();
		
		ForumEntryList testList= new ForumEntryList();
		testList.add(new ForumEntry("test 3","author3",new Date(time)));
		testList.add(new ForumEntry("test 2","author2",new Date(time+2)));
		testList.add(new ForumEntry("test 1","author1",new Date(time+3)));
		
		assertEquals(posts,testList);
		
		}
	
	public void sortByRatingTest(){
		ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
		long time=123456789;
		posts.add(new ForumEntry("test 1","author1",new Date(time)));
		posts.add(new ForumEntry("test 2","author2",new Date(time)));
		posts.add(new ForumEntry("test 3","author3",new Date(time)));
		
		posts.get(0).setUpVote(1);
		posts.get(1).setUpVote(5);
		posts.get(2).setUpVote(3);
		
		(new DataManager()).save(posts);
		
		testActivity.getBrowseControllerForTesting().sortByTime();
		
		ForumEntryList testList= new ForumEntryList();
		testList.add(new ForumEntry("test 2","author2",new Date(time)));
		testList.add(new ForumEntry("test 3","author3",new Date(time)));
		testList.add(new ForumEntry("test 1","author1",new Date(time)));
		
		assertEquals(posts,testList);
		
		}
	
	public void sortByHasPictureTest(){
		ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
		long time=123456789;
		posts.add(new ForumEntry("test 1","author1",new Date(time)));
		posts.add(new ForumEntry("test 2","author2",new Date(time)));
		posts.add(new ForumEntry("test 3","author3",new Date(time)));
		
		posts.get(1).addPicture(new Picture());
		
		posts.sortByHasPicture();
		
		(new DataManager()).save(posts);
		
		testActivity.getBrowseControllerForTesting().sortByTime();
		
		ForumEntryList testList= new ForumEntryList();
		testList.add(new ForumEntry("test 2","author2",new Date(time)));
		testList.add(new ForumEntry("test 1","author1",new Date(time)));
		testList.add(new ForumEntry("test 3","author3",new Date(time)));
		
		assertEquals(posts,testList);
		
		}
	}


