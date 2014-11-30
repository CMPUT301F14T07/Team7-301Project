package ca.ualberta.cs.f14t07_application.test;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.controllers.BrowseController;
import ca.ualberta.cs.controllers.ForumEntryController;
import ca.ualberta.cs.controllers.SearchController;
import ca.ualberta.cs.models.Answer;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.views.BrowseActivity;
import ca.ualberta.cs.views.MainScreenActivity;

public class SearchTest extends ActivityInstrumentationTestCase2<BrowseActivity> {

	private BrowseActivity testActivity;
	private Context ctx;
	
	   public SearchTest(){
	    	super(BrowseActivity.class);
	    }
	   
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
		ctx = testActivity.getApplicationContext();
		
		/* Reset the testButton - do this so consecutive tests don't accidentally test
		 * the same button.
		 */
		//testButton = null;
	}
	
    public void testSearchQuestion(){
    	ForumEntry forumEntry = new ForumEntry("This is a searchTest","This is a search test, it has the word Kazoo","Lexie");
        DataManager dataManager = new DataManager();
        
        dataManager.addForumEntry(forumEntry);
        
        SearchController searchController = new SearchController();
		ArrayList<ForumEntry> forumEntryList = new ArrayList<ForumEntry>();
		try {
			forumEntryList = searchController.searchForumEntries("Kazoo",null);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boolean testPass = false;
    	if(forumEntryList.size()>1){
				testPass = true;
			}
		assertTrue(testPass);
    }
    public void testSearchAnswer(){
    	ForumEntry forumEntry = new ForumEntry("This is a searchTest","This is a search test","Lexie");
        Answer answer = new Answer ("This is a test Answer","This has the word huckleberry in it");
        ArrayList<Answer> answerList = new ArrayList<Answer>();
        answerList.add(answer);
        forumEntry.setAnswer(answerList);
    	DataManager dataManager = new DataManager();
        
        dataManager.addForumEntry(forumEntry);
        
        SearchController searchController = new SearchController();
		ArrayList<ForumEntry> forumEntryList = new ArrayList<ForumEntry>();
		try {
			forumEntryList = searchController.searchForumEntries("huckleberry",null);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boolean testPass = false;
    	if(forumEntryList.size()>1){
				testPass = true;
			}
		assertTrue(testPass);
    }
} 