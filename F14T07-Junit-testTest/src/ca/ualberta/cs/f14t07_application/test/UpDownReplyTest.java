package ca.ualberta.cs.f14t07_application.test;

import ca.ualberta.cs.f14t07_application.ForumEntry;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

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
/* TODO: More extensive tests of the chain of events that unfold when a button is pushed
 */
public class UpDownReplyTest extends ActivityInstrumentationTestCase2<VoteAndReplyActivity> {

	private VoteAndReplyActivity testActivity;
	private Button testButton;
	
	public UpDownReplyTest()
	{
		super(VoteAndReplyActivity.class);
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
		String question = "This is the question";
		String author = "This is the author";
		ForumEntry testEntry = new ForumEntry(question, author);
		int initialVote = testEntry.getQuestion().getUpVote();
		DataManager dataM = new DataManager();
		
		testActivity.forumEntryController.addForumEntry(testEntry);
		
		Button upVoteButton = (Button) testActivity.findViewById(
					com.example.f14t07_application.activity_voteandreply.R.id.UpVoteQuestion);
		upVoteButton.performClick();
		
		ArrayList<ForumEntry> testList = dataM.load();
		Iterator iter = testList.iterator();
		while(iter.hasNext())
		{
			ForumEntry temp = iter.next();
			if(temp.getQuestion().getAuthorsName() == author && temp.getQuestion().getPost() == question)
			{
				assertTrue(temp.getQuestion().getUpVote(), initialVote+1);
				break;
			}
		}
		
		/*ForumEntry TestEntry=ForumEntry();
		TestEntry.addAnswer(new Entry testEntry);
		upVoteButton = (Button) testActivity.findViewById(
					com.example.f14t07_application.activity_voteandreply.R.id.UpVoteQuestion);
		
		int initialValue=TestEntry.getQuestion().getUpVote();
		upVoteButton.performClick();
		int afterClick=TestEntry.getQuestion().getUpVote();
		assertTrue(initialValue,afterClick+1);*/
	}
	
	/**
	 * Test upvoting an answer
	 */
	public void downVoteTest()
	{
		upVoteButton = (Button) testActivity.findViewById(
				com.example.f14t07_application.activity_voteandreply.R.id.upVoteAnswer);
		
		assertTrue(testButton != null);
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
				com.example.f14t07_application.activity_voteandreply.R.id.ReplyToForumPost);
	
		assertTrue(testButton != null);
	}
}
