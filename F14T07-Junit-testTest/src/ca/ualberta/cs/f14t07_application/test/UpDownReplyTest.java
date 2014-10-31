package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;
import java.util.Iterator;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import ca.ualberta.cs.f14t07_application.DataManager;
import ca.ualberta.cs.f14t07_application.Entry;
import ca.ualberta.cs.f14t07_application.ForumEntry;
import ca.ualberta.cs.f14t07_application.ForumEntryController;
import ca.ualberta.cs.f14t07_application.QuestionActivity;
import ca.ualberta.cs.f14t07_application.Reply;

/**
 * Test the fragment that displays options to up vote, down vote, or reply to an
 * answer or main question. The 'VoteAndReplyActivity' is the theoretical name for the 
 * activity class that will display these options. I am assuming that the options are
 * buttons.
 * 
 * The code in this test class has been heavily influenced by the android junit testing tutorial
 * provided at http://developer.android.com/tools/testing/activity_test.html
 * @author Brendan
 *
 */

public class UpDownReplyTest extends ActivityInstrumentationTestCase2<QuestionActivity> {

	private QuestionActivity testActivity;
	private Button testButton;
	
	public UpDownReplyTest()
	{
		super(QuestionActivity.class);
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
		
		/* Reset the testButton - do this so consecutive tests don't accidentally test
		 * the same button.
		 */
		testButton = null;
	}
	
	/**
	 *Test upvoting a question
	 */
	public void upVoteQuestionTest()
	{
		setUp();
		String subject = "This is the subject";
		String question = "This is the question";
		String author = "This is the author";
		ForumEntry testEntry = new ForumEntry(subject, question, author);
		int initialVote = testEntry.getQuestion().getUpVote();
		long testEntryId = testEntry.getId();
		DataManager dataM = new DataManager();
		
		(new ForumEntryController()).addForumEntry(testEntry);
		
		Button upVoteButton = (Button) testActivity.findViewById(
				ca.ualberta.cs.f14t07_application.R.id.UpVoteButton);
		upVoteButton.performClick();
		
		ArrayList<ForumEntry> testList = dataM.load();
		Iterator iter = testList.iterator();
		while(iter.hasNext())
		{
			ForumEntry temp = iter.next();
			if(temp.getId() == testEntryId)
			{
				assertTrue(temp.getQuestion().getUpVote(), initialVote+1);
				break;
			}
		}
		dataM.deleteLocalAll();
	}
	
	/**
	 * Test upvoting an answer
	 */
	public void upVoteAnswerTest() //NEEDS TO BE CHANGED - WE'RE DOING A NEW SCREEN NOW
	{
		setUp();
		String subject = "This is the subject";
		String answer = "This is an answer";
		String author = "This is the author";
		ForumEntry testEntry = new ForumEntry(subject, answer,author);
		long testEntryId = testEntry.getId();
		int initialVote = testEntry.getAnswers().get(0).getUpVote();
		DataManager dataM = new DataManager();
		
		(new ForumEntryController()).addForumEntry(testEntry);
		
		Button upVoteButton = (Button) testActivity.findViewById(
				ca.ualberta.cs.f14t07_application.R.id.UpVoteButton);
		upVoteButton.performClick();
		
		ArrayList<ForumEntry> testList = dataM.load();
		Iterator iter = testList.iterator();
		while(iter.hasNext())
		{
			ForumEntry temp = iter.next();
			if(temp.getId() == testEntryId)
			{
				assertTrue(temp.getAnswers().get(0).getUpVote(), initialVote+1);
				break;
			}
		}
		/* Clean up the local memory after testing. */
		dataM.deleteLocalAll();
	}

	/**
	 * Tests that pushing the reply button will trigger a chain of events which
	 * results in the particular answer / main question having a reply logged.
	 * 
	 * This test assumes the name of the reply button and activity.xml it is in
	 */
	public void replyTest()
	{
		testButton = (Button) testActivity.findViewById(
				ca.ualberta.cs.f14t07_application.R.id.ReplyToForumPost);
		Reply testReply = new Reply("This is the reply");

		setUp();
		String subject = "This is the subject";
		String question = "This is the question";
		String author = "This is the author";
		ForumEntry testEntry = new ForumEntry(subject, question, author);
		long testEntryId = testEntry.getId();
		DataManager dataM = new DataManager();
		ForumEntryController fec = new ForumEntryController();
		fec.addForumEntry(testEntry);
		/* Add this reply to the 0'th entry in the forum entry - aka - the main question */
		fec.addReplyToEntry(0, new Reply("This is the reply"));
		
		ArrayList<ForumEntry> testList = dataM.load();
		Iterator iter = testList.iterator();
		while(iter.hasNext())
		{
			ForumEntry temp = iter.next();
			if(temp.getId() == testEntryId)
			{
			    assertTrue(temp.getQuestion().getReplies().get(0).getReply(), "This is the reply");
			    break;
			}
		}
		dataM.deleteLocalAll();
		
	}
}
