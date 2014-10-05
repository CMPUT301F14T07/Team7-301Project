package com.example.f14t07_application.test;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Test the fragment that displays options to up vote, down vote, or reply to an
 * answer or main question. The 'VoteAndReplyActivity' is the theoritcal name for the 
 * activity that will display these options.
 * @author Brendan
 *
 */
public class UpDownReplyTest extends ActivityInstrumentationTestCase2<VoteAndReplyActivity> {

	public UpDownReplyTest()
	{
		super(VoteAndReplyActivity.class);
	}
	
	/**
	 * Tests that pushing the up vote button will trigger a chain of events which result
	 * in the particular answer / main question being voted up.
	 */
	public void upVoteTest()
	{
		
	}
	
	/**
	 * Tests that pushing the down vote button will trigger a chain of events which result
	 * in the particular answer / main question being voted down.
	 */
	public void downVoteTest()
	{
		
	}
	
	/**
	 * Tests that pushing the reply button will trigger a chain of events which
	 * results in the particular answer / main question having a reply logged.
	 */
	public void replyTest()
	{
		
	}
}
