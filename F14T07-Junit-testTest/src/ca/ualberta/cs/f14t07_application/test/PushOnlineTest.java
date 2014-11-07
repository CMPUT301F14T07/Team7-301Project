package ca.ualberta.cs.f14t07_application.test;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.views.AskActivity;
import ca.ualberta.cs.views.BrowseActivity;

public class PushOnlineTest extends ActivityInstrumentationTestCase2<AskActivity> {
	private DataManager dataManager;
	private Context ctx;
	
	public PushOnlineTest(Class<AskActivity> activityClass) {
		super(AskActivity.class);
		dataManager = new DataManager(ctx);
	}

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
	}
	
	public void testPushOnline(){ 
		
		
		assertTrue(dataManager.isOffline());
		
		ForumEntry testEntry = new ForumEntry("SUbject", "This is only a test", "lexie");
		
		dataManager.addForumEntry(testEntry);
		//need to find a way to toggle internet on/off
		
		assertTrue(dataManager.isOnline());
		BrowseActivity ba = new BrowseActivity();
		
		Boolean testWorks = false;
		
		for (int i = 0; i<ba.forumEntries.size(); i++) { 
			if (ba.forumEntries.get(i)==testEntry){
				testWorks = true;
			}
		}
		
		assertTrue(testWorks);
		
	}
}
