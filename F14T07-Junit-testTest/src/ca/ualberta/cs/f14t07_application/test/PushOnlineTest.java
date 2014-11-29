package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.models.Answer;
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
	
	public void testAskOffline(){ 
		AskActivity askActivity = getActivity();
		Context context = askActivity.getContext();
		
		NetworkChecker networkChecker = new NetworkChecker();
		networkChecker.setContext(context);
		assertFalse(networkChecker.getIsOnline());
		
		ForumEntry testEntry = new ForumEntry("SUbject", "This is only a test", "lexie");
		
		dataManager.addForumEntry(testEntry);
		BrowseActivity ba = new BrowseActivity();
		
		Boolean testWorks = false;
		
		for (int i = 0; i<ba.forumEntries.size(); i++) { 
			if (ba.forumEntries.get(i)==testEntry){
				testWorks = true;
			}
		}
		
		assertTrue(testWorks);
		
	}
	public void testAnswerOffline(){
		AskActivity askActivity = getActivity();
		Context context = askActivity.getContext();
		
		NetworkChecker networkChecker = new NetworkChecker();
		networkChecker.setContext(context);
		assertFalse(networkChecker.getIsOnline());
		
		ForumEntry testEntry = new ForumEntry("PushOnlineTest", "This is part of the pushOnline test", "Lexie");
		Answer testAnswer = new Answer("PushOnlineTestAnswerSubject","This answer means its working");
		ArrayList<Answer> testAnswers = new ArrayList<Answer>();
		
		testAnswers.add(testAnswer);
		testEntry.setAnswer(testAnswers);
		dataManager.addForumEntry(testEntry);
		
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
