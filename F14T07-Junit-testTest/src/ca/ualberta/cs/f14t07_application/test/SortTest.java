package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import ca.ualberta.cs.views.BrowseActivity;
import ca.ualberta.cs.controllers.BrowseController;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import android.graphics.Picture;
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
	
	public void testSortByTime(){
		//Elastic Search needs to be figured otu before we can effectively write tests
		ForumEntry f1 = new ForumEntry("subject","test 1","author1");
    	ForumEntry f2 = new ForumEntry("subject","test 2","author2");
    	ForumEntry f3 = new ForumEntry("subject","test 3","author3");

    	ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
    	posts.add(f2);
    	posts.add(f3);
    	posts.add(f1);
    	
    	
		
		//set time using index, day month year hour minute
    	// Don't know how to set dates yet so we haven't figured this fully out
		
		BrowseController bc = new BrowseController();
		bc.sortByTime(posts);
		
		ArrayList<ForumEntry> sortedList= new ArrayList<ForumEntry>();

		sortedList.add(new ForumEntry("subject","test 1","author3"));
		sortedList.add(new ForumEntry("subject","test 2","author2"));
		sortedList.add(new ForumEntry("subject","test 3","author1"));
		
		assertEquals(sortedList, posts);
		
		}
	
	public void testSortByRating(){
    	ForumEntry fm1 = new ForumEntry("subject","test 1","author1");
    	ForumEntry fm2 = new ForumEntry("subject","test 2","author2");
    	ForumEntry fm3 = new ForumEntry("subject","test 3","author3");
    	ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
    	posts.add(fm1);
    	posts.add(fm2);
    	posts.add(fm3);
    	
		posts.get(0).getQuestion().setUpVote(1);
		posts.get(1).getQuestion().setUpVote(5);
		posts.get(2).getQuestion().setUpVote(3);
		
		BrowseController bc = new BrowseController();
		bc.sortByRating(posts);
		
		
		ArrayList<ForumEntry> sortedList = new ArrayList<ForumEntry>();
		sortedList.add(new ForumEntry("subject","test 2","author2"));
		sortedList.add(new ForumEntry("subject","test 3","author3"));
		sortedList.add(new ForumEntry("subject","test 1","author1"));
		
		assertEquals(sortedList,posts);
		
		}
	
	public void testSortByHasPicture(){
    	ForumEntry f1 = new ForumEntry("subject","test 1","author1");
    	ForumEntry f2 = new ForumEntry("subject","test 2","author2");
    	ForumEntry f3 = new ForumEntry("subject","test 3","author3");
		
    	ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
    	posts.add(f1);
    	posts.add(f2);
    	posts.add(f3);
    	
		posts.get(1).getQuestion().setPicture(new Picture());
		
		BrowseController bc = new BrowseController();
		bc.sortByHasPicture(posts);
		

		ArrayList<ForumEntry> sortedList = new ArrayList<ForumEntry>();
		sortedList.add(new ForumEntry("subject","test 2","author2"));
		sortedList.add(new ForumEntry("subject","test 1","author1"));
		sortedList.add(new ForumEntry("subject","test 3","author3"));
		
		assertEquals(sortedList,posts);
		
		}
}

