package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;
import java.util.Iterator;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.Entry;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.controllers.BrowseController;
import ca.ualberta.cs.controllers.ForumEntryController;
import ca.ualberta.cs.views.QuestionActivity;
import ca.ualberta.cs.models.Reply;

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
		String subject = "This is the subject";
		String question = "This is the question";
		String author = "This is the author";
		ForumEntry testEntry = new ForumEntry(subject, question, author);
		int initialVote = testEntry.getQuestion().getUpVote();
		DataManager dataM = new DataManager();
		
		dataM.addForumEntry(testEntry);
		
		//Button upVoteButton = (Button) testActivity.findViewById(ca.ualberta.cs.f14t07_application.R.id.UpVoteButton);
		//upVoteButton.performClick();
		
		
		assertEquals(dataM.getForumEntry().getQuestion().getUpVote(),1);
		dataM.deleteLocalAll();
	}
	
	/**
	 * Test upvoting an answer
	 */
	public void upVoteAnswerTest() //NEEDS TO BE CHANGED - WE'RE DOING A NEW SCREEN NOW
	{
		String subject = "This is the subject";
		String answer = "This is an answer";
		String author = "This is the author";
		ForumEntry testEntry = new ForumEntry(subject, answer,author);
		int initialVote = testEntry.getAnswers().get(0).getUpVote();
		DataManager dataM = new DataManager();
		
		dataM.addForumEntry(testEntry);
		// This isn't set up yet, hence commented out
	//	Button upVoteButton = (Button) testActivity.findViewById(
			//	ca.ualberta.cs.f14t07_application.R.id.UpVoteButton);
	//	upVoteButton.performClick();
		
	
		assertEquals(dataM.getForumEntry().getAnswers().get(0), 1);
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
		//button not set up
		/*
		testButton = (Button) testActivity.findViewById(
				ca.ualberta.cs.f14t07_application.R.id.ReplyToForumPost);*/
		Reply testReply = new Reply("This is the reply");

		String subject = "This is the subject";
		String question = "This is the question";
		String author = "This is the author";
		ForumEntry testEntry = new ForumEntry(subject, question, author);
		DataManager dataM = new DataManager();
		dataM.addForumEntry(testEntry);
		/* Add this reply to the 0'th entry in the forum entry - aka - the main question */
		dataM.addReplyToEntry(testEntry, "This is the reply");
		
		assertEquals(dataM.getForumEntry().getQuestion().getReplies(), "This is a reply");
	}
}
