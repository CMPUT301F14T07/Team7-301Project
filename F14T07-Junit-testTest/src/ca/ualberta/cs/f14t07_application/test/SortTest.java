package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import ca.ualberta.cs.views.BrowseActivity;
import ca.ualberta.cs.controllers.BrowseController;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;

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
		//testButton = null;
	}
	public SortTest(){
		super(BrowseActivity.class);
	}
	
	public void sortByTimeTest(){
		DataManager dm = new DataManager();
    	dm.addForumEntry((new ForumEntry("subject","test 1","author1")));
    	dm.addForumEntry((new ForumEntry("subject","test 2","author2")));
    	dm.addForumEntry((new ForumEntry("subject","test 3","author3")));

		
		//set time using index, day month year hour minute
    	
		posts.get(0).getDate().setTime(time+3); //How are we doing this now?????????????????????????????????????????
		posts.get(1).getDate().setTime(time+2);
		posts.get(2).getDate().setTime(time);
		
		BrowseController bc = new BrowseController();
		bc.sortByTime();
		ArrayList<ForumEntry> testList = (new DataManager()).loadGlobal();
		
		ArrayList<ForumEntry> sortedList= new ArrayList<ForumEntry>();

		sortedList.add(new ForumEntry("subject","test 3","author3"));
		sortedList.add(new ForumEntry("subject","test 2","author2"));
		sortedList.add(new ForumEntry("subject","test 1","author1"));
		
		assertEquals(sortedList,testList);
		
		}
	
	public void sortByRatingTest(){
		DataManager dm = new DataManager();
    	dm.addForumEntry((new ForumEntry("subject","test 1","author1")));
    	dm.addForumEntry((new ForumEntry("subject","test 2","author2")));
    	dm.addForumEntry((new ForumEntry("subject","test 3","author3")));
    	
		posts.get(0).setUpVote(1);
		posts.get(1).setUpVote(5);
		posts.get(2).setUpVote(3);
		
		BrowseController bc = new BrowseController();
		bc.sortByRating();
		ArrayList<ForumEntry> testList = (new DataManager()).loadGlobal();
		
		ArrayList<ForumEntry> sortedList = new ArrayList<ForumEntry>();
		sortedList.add(new ForumEntry("subject","test 2","author2"));
		sortedList.add(new ForumEntry("subject","test 3","author3"));
		sortedList.add(new ForumEntry("subject","test 1","author1"));
		
		assertEquals(sortedList,testList);
		
		}
	
	public void sortByHasPictureTest(){
		DataManager dm = new DataManager();
    	dm.addForumEntry((new ForumEntry("subject","test 1","author1")));
    	dm.addForumEntry((new ForumEntry("subject","test 2","author2")));
    	dm.addForumEntry((new ForumEntry("subject","test 3","author3")));
		
		posts.get(1).addPicture(new Picture());
		
		BrowseController bc = new BrowseController();
		bc.sortByHasPicture();
		ArrayList<ForumEntry> testList = (new DataManager()).loadGlobal();
		

		ArrayList<ForumEntry> sortedList = new ArrayList<ForumEntry>();
		sortedList.add(new ForumEntry("subject","test 2","author2"));
		sortedList.add(new ForumEntry("subject","test 1","author1"));
		sortedList.add(new ForumEntry("subject","test 3","author3"));
		
		assertEquals(sortedList,testList);
		
		}
}


