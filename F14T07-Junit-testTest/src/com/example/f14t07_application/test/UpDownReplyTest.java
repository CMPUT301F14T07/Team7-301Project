package com.example.f14t07_application.test;

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
	 * Tests that pushing the up vote button will trigger a chain of events which result
	 * in the particular answer / main question being voted up.
	 * 
	 * This test assumes the name of the up vote button and activity.xml it is in.
	 */
	public void upVoteTest()
	{
		setUp();
		testButton = (Button) testActivity.findViewById(
					com.example.f14t07_application.activity_voteandreply.R.id.UpVote);
		
		assertTrue(testButton != null);
	}
	
	/**
	 * Tests that pushing the down vote button will trigger a chain of events which result
	 * in the particular answer / main question being voted down.
	 * 
	 * This test assumes the name of the down vote button and activity.xml it is in
	 */
	public void downVoteTest()
	{
		testButton = (Button) testActivity.findViewById(
				com.example.f14t07_application.activity_voteandreply.R.id.DownVote);
	
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
