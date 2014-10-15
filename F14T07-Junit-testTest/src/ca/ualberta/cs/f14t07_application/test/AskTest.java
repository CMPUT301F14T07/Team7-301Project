package ca.ualberta.cs.f14t07_application.test;

import ca.ualberta.cs.f14t07_application.Ask;
import ca.ualberta.cs.f14t07_application.LogoActivity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;


public class AskTest extends ActivityInstrumentationTestCase2<AskActivity> {
	
	private Button testPostButton;
	private Button testMainMenuButton;
		
	public AskTest()
	{
		super(AskActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception
	{
	super.setUp();
	setActivityInitialTouchMode(true);
	testActivity = getActivity();
	}
	
	
	/* The current functionality after clicking the "ask" button from the main menu is as follows:
	 * -enter a name
	 * -enter a question
	 * -enter a subject
	 * -attach a picture
	 * -click an "ask" or "submit" button
	 * -click on a "main menu" button
	 */
	
	//Test if there is a name
	public void testName()
	{
		//Will we be saving user names? So should we check if the name exists?
		//Will users have to sign in?
		
		boolean notEmpty = false;
		nameString = (String) testActivity.findViewById(
				com.example.f14t07_application.activity_testactivity.R.id.NameString);
		if(!nameString.isEmpty() && nameString.trim().length() > 0)
		{
			notEmpty = true;
		}
		assertTrue("The name string is empty", notEmpty == true);
	}
	
	public void testQuestion()
	{
		boolean notEmpty = false;
		questionString = (String) testActivity.findViewById(
				com.example.f14t07_application.activity_testactivity.R.id.QuestionString);
		if(!questionString.isEmpty() && questionString.trim().length() > 0)
		{
			notEmpty = true;
		}
		assertTrue("The question string is empty", notEmpty == true);
	}
	
	public void testSubject()
	{
		boolean notEmpty = false;
		subjectString = (String) testActivity.findViewById(
				com.example.f14t07_application.activity_testactivity.R.id.SubjectString);
		if(!subjectString.isEmpty() && subjectString.trim().length() > 0)
		{
			notEmpty = true;
		}
		assertTrue("The subject string is empty", notEmpty == true);
	}

	public void testPicture()
	{
		//Need more tests in here once we determine more about how pictures will work
		
		//Possible a boolean value in the questions attributes indicating if there is a picture?

		boolean isPicture = false;
		


		
		
		
		
			//Check if the pictures are the same (it was loaded properly)
			
			//Maybe check picture size?
		}
	}
	
	public void testPostButton()
	{
		testPostButton = (Button) AskActivity.findViewById(
				com.example.f14t07_application.activity_askactivity.R.id.PostButton);	
		testPostButton.performClick();
		Intent newintent = getStartedActivityIntent();
		Intent AskIntent= new Intent(AskTest.this, AskActivity.class); //where should this go? Some view activity?
		assertTrue(newintent.filterEquals(AskIntent));
	}
	
	public void testMainMenuButton()
	{
		testMainMenuButton = (Button) AskActivity.findViewById(
				com.example.f14t07_application.activity_askactivity.R.id.MainMenuButton);	
		testMainMenuButton.performClick();
		Intent newintent = getStartedActivityIntent();
		Intent AskIntent= new Intent(AskTest.this, HomeActivity.class); 
		assertTrue(newintent.filterEquals(AskIntent));
	}

}
