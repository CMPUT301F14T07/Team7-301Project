package ca.ualberta.cs.f14t07_application.test;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.remote_server.NetworkChecker;
import ca.ualberta.cs.views.AskActivity;
import ca.ualberta.cs.views.BrowseActivity;

public class PushOnlineTest extends ActivityInstrumentationTestCase2<AskActivity> {
	private DataManager dataManager;
	private Context ctx;
	
	public PushOnlineTest() {
		super(AskActivity.class);
	}

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		dataManager = new DataManager();
	}
	
	public void testPushOnline(){ 
		
		NetworkChecker networkChecker = new NetworkChecker();
		
		assertFalse(networkChecker.getIsOnline());
		
		ForumEntry testEntry = new ForumEntry("SUbject", "This is only a test", "lexie");
		
		dataManager.addForumEntry(testEntry);
		//need to find a way to toggle internet on/off
		
		assertTrue(networkChecker.getIsOnline());
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
