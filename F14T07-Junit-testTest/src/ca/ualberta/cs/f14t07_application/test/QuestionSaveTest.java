package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.views.BrowseActivity;

public class QuestionSaveTest extends ActivityInstrumentationTestCase2<BrowseActivity> {

	private DataManager datamanager;
	private Context ctx;
	private BrowseActivity testActivity;
	
	public QuestionSaveTest() {
		super(BrowseActivity.class);

	}

	protected void setUp() throws Exception {
		super.setUp();
		datamanager = new DataManager();
		setActivityInitialTouchMode(false);
		testActivity = getActivity();
		ctx = testActivity.getApplicationContext();

		
	}
	
	// Test for u28
	public void testSaveFavourite() {
		ForumEntry exampleEntry = new ForumEntry("subject","What is life?","Kibbles");
		exampleEntry.setId("A2930458034");
    	ArrayList<ForumEntry> testQuestions = new ArrayList<ForumEntry>();
    	ArrayList<ForumEntry> compareList = new ArrayList<ForumEntry>();
    	testQuestions.add(exampleEntry);
    	
    	datamanager.setFavourites(testQuestions);
		compareList = datamanager.getFavourites();
		assertEquals(testQuestions, compareList);
	}
	
	// Test for u26
	public void testSaveReadLater() {
		ForumEntry exampleEntry = new ForumEntry("subject","What is life?","Kibbles");
		exampleEntry.setId("A2930458034");
    	ArrayList<ForumEntry> testQuestions = new ArrayList<ForumEntry>();
    	ArrayList<ForumEntry> compareList = new ArrayList<ForumEntry>();
    	testQuestions.add(exampleEntry);
    	
    	datamanager.setReadLater(testQuestions);
		compareList = datamanager.getReadLater();
		assertEquals(testQuestions, compareList);
	}

}
