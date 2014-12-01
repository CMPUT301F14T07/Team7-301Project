package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import ca.ualberta.cs.views.BrowseActivity;
import ca.ualberta.cs.controllers.BrowseController;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import android.graphics.Picture;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;

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
		ForumEntry f1 = new ForumEntry("subject","test 1","author1",null);
    	ForumEntry f2 = new ForumEntry("subject","test 2","author2",null);
    	ForumEntry f3 = new ForumEntry("subject","test 3","author3",null);
    	
    	final ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();

    	posts.add(f2);
    	posts.add(f3);
    	posts.add(f1);

    	
        	final BrowseController bc = new BrowseController(testActivity);
        	DataManager dm= new DataManager();
        	
        	
        	try {
    			runTestOnUiThread(new Runnable() {
    				@Override
    				public void run() {
    
    					bc.sortByTimeLocal(posts, "M");
    					
    				}
    			});
    		} catch (Throwable e) {
    			e.printStackTrace();
    		}
    		
        	
        	
        	ArrayList<ForumEntry> afterSort= dm.getMyAuthored();
        	
        	ArrayList<ForumEntry> sortedList= new ArrayList<ForumEntry>();
    
    		sortedList.add(f2);
    		sortedList.add(f3);
    		sortedList.add(f1);
    		for(int i = 0; i < bc.result.size(); i++){
    			assertEquals(bc.result.get(i).getQuestion(),sortedList.get(i).getQuestion());
    		}
        	
    	}
//	
    	public void testSortByRating(){
    		ForumEntry f1 = new ForumEntry("subject","test 1","author1",null);
        	ForumEntry f2 = new ForumEntry("subject","test 2","author2",null);
        	ForumEntry f3 = new ForumEntry("subject","test 3","author3",null);
        	
        	final ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
    
        	f1.getQuestion().setUpVote(35);
        	f3.getQuestion().setUpVote(50);
        	
        	posts.add(f1);
        	posts.add(f2);
        	posts.add(f3);
        	
        	final BrowseController bc = new BrowseController(testActivity);
        	DataManager dm= new DataManager();
        	
        	
        	try {
    			runTestOnUiThread(new Runnable() {
    				@Override
    				public void run() {
    
    					bc.sortByRatingLocal(posts, "M");
    					
    				}
    			});
    		} catch (Throwable e) {
    			e.printStackTrace();
    		}
        	
        	
        	ArrayList<ForumEntry> afterSort= dm.getMyAuthored();
        	
        	ArrayList<ForumEntry> sortedList= new ArrayList<ForumEntry>();
    
    		sortedList.add(f3);
    		sortedList.add(f1);
    		sortedList.add(f2);
    		for(int i = 0; i < bc.result.size(); i++){
    			assertEquals(bc.result.get(i).getQuestion(),sortedList.get(i).getQuestion());
    		}
    		
    	}

	public void testSortByPicture(){
		ForumEntry f1 = new ForumEntry("subject","test 1","author1",null);
    	ForumEntry f2 = new ForumEntry("subject","test 2","author2","pic");
    	ForumEntry f3 = new ForumEntry("subject","test 3","author3","pic2");
    	
    	final ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
    	posts.add(f1);
    	posts.add(f2);
    	posts.add(f3);
    	
    	final BrowseController bc = new BrowseController(testActivity);
    	DataManager dm= new DataManager();
    	
    	
    	try {
			runTestOnUiThread(new Runnable() {
				@Override
				public void run() {

					bc.sortByHasPictureLocal(posts, "M");
					
				}
			});
		} catch (Throwable e) {
			e.printStackTrace();
		}
    	
    	
		int prev=1;
		for(int i = 0; i < bc.result.size(); i++){
			assertEquals(true,(prev >= checker(bc.result.get(i).getQuestion().getPicture())));
			prev=checker(bc.result.get(i).getQuestion().getPicture());
		}
		
	}
	public void testSortByLocation(){
		ForumEntry f1 = new ForumEntry("subject","test 1","author1",null);
    	ForumEntry f2 = new ForumEntry("subject","test 2","author2",null);
    	ForumEntry f3 = new ForumEntry("subject","test 3","author3",null);
    	
    	final ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();

    	f1.setLocation("Edmonton");
    	f3.setLocation("Edmonton");
    	f2.setLocation("Hogwarts");
    	
    	posts.add(f1);
    	posts.add(f2);
    	posts.add(f3);
    	
    	final BrowseController bc = new BrowseController(testActivity);
    	
    	try  {
			runTestOnUiThread(new Runnable() {
				@Override
				public void run() {

					bc.sortByLocation(posts,"Edmonton");
					
				}
			});
		} catch (Throwable e) {
			
		}
    	
    	
    	ArrayList<ForumEntry> sortedList= new ArrayList<ForumEntry>();

		sortedList.add(f3);
		sortedList.add(f1);
		sortedList.add(f2);
		for(int i = 0; i < bc.result.size(); i++){
			assertEquals(bc.result.get(i).getQuestion(),sortedList.get(i).getQuestion());
		}
		
	}
	
	public int checker(String s){
		if (s==null){
			return 0;
		}
		return 1;
	}
}

