package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.intent_singletons.ForumEntrySingleton;
import ca.ualberta.cs.models.Answer;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.Reply;
import ca.ualberta.cs.views.AskActivity;
import junit.framework.TestCase;

public class AnswerTest extends ActivityInstrumentationTestCase2<AskActivity> {

	AskActivity testActivity;
	DataManager dm;
	
	public AnswerTest() {
		super(AskActivity.class);
		// TODO Auto-generated constructor stub
	}

	public void setUp() throws Exception{
		super.setUp();
		setActivityInitialTouchMode(true);
		testActivity = getActivity();
	}
	public void testAnswerQuestion(){
		dm = new DataManager();
		AskActivity activity = getActivity();
		
		ForumEntry forumEntry = new ForumEntry("This is an answer test","This is an answer question","Lexie");
		Answer answer = new Answer("This is an answer","This is an answer");
		ArrayList <Answer> answerlist = new ArrayList<Answer>();
		answerlist.add(answer);
		forumEntry.setAnswer(answerlist);

		dm.addForumEntry(forumEntry);
		ForumEntry result = dm.getForumEntry();
		
		assertEquals(forumEntry,result);
	}
	public void testReplyAnswerQuestion(){
		dm = new DataManager();
		AskActivity activity = getActivity();
		
		ForumEntry forumEntry = new ForumEntry("This is an answer test","This is an answer question","Lexie");
		Answer answer = new Answer("This is an answer","This is an answer");
		ArrayList <Answer> answerlist = new ArrayList<Answer>();
		answerlist.add(answer);
		forumEntry.setAnswer(answerlist);
		Reply reply = new Reply("This is a reply");
		ArrayList<Reply> replyList = new ArrayList<Reply>();
		forumEntry.getAnswers().get(0).setReplies(replyList);
		dm.addForumEntry(forumEntry);
		ForumEntry result = dm.getForumEntry();
		
		assertEquals(forumEntry,result);
	}
}
