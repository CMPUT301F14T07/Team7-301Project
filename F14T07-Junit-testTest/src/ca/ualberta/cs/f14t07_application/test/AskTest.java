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
	protected void setUpTest() throws Exception
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
	public void NameTest()//should this be testName() of testGetName()??
	{
		//Will we be saving user names? So should we check if the name exists?
		//Will users have to sign in?
		
		boolean notEmpty = false;
		nameString = (EditText) testActivity.findViewById(
				com.example.f14t07_application.activity_testactivity.R.id.NameString);
		nameString.setText("Timothy");
		
		Intent newIntent = getStartedActivityIntent();
		Intent nameIntent = new Intent(AskTest.this, AskActivity.class);
		assertTrue(newIntent.filterEquals(nameIntent)); //Makes sure the intent is the intent we just made

		if(!nameString.isEmpty() && nameString.trim().length() > 0)
		{
			notEmpty = true;
		}

		String expectedName = "Timothy";
		String name = Name();
		assertEquals(name, expectedName);
		assertTrue("The name string is empty", notEmpty == true);
	}
	
	public void getQuestionTest()//should this be testQuestion() of testGetQuestion()??
	{
		boolean notEmpty = false;
		questionString = (EditText) testActivity.findViewById(
				com.example.f14t07_application.activity_testactivity.R.id.QuestionString);
		questionString.setText("What's your name???");
		
		Intent newIntent = getStartedActivityIntent();
		Intent questionIntent = new Intent(AskTest.this, AskActivity.class);
		assertTrue(newIntent.filterEquals(questionIntent));

		if(!questionString.isEmpty() && questionString.trim().length() > 0)
		{
			notEmpty = true;
		}

		String expectedQuestion = "What's your name???";
		String question = Question();
		assertEquals(question, expectedQuestion);
		assertTrue("The question string is empty", notEmpty == true);
	}
	
	public void getSubjectTest() //should this be testSubject() of testGetSubject()??
	{
		boolean notEmpty = false;
		subjectString = (EditText) testActivity.findViewById(
				com.example.f14t07_application.activity_testactivity.R.id.SubjectString);
		subjectString.setText("I accidentally clicked insert");

		Intent newIntent = getStartedActivityIntent();
		Intent subjectIntent = new Intent(AskTest.this, AskActivity.class);
		assertTrue(newIntent.filterEquals(subjectIntent));

		if(!subjectString.isEmpty() && subjectString.trim().length() > 0)
		{
			notEmpty = true;
		}

		String expectedSubject = "I accidentally clicked insert";
		String subject = Subject(); 
		assertEquals(subject, expectedSubject);
		assertTrue("The subject string is empty", notEmpty == true);
	}

	public void ifPictureTest()  //test picture() or test ifPicture()?
	{
		//Need more tests in here once we determine more about how pictures will work
	        ForumEntry test=new ForumEntry("this is a forum entry");
	        
	        //add a picture called picture.png
	        File pictureFile= picture.png;
	        test.addPicture(pictureFile)
	        
		assertEquals(test.getPicture(),pictureFile);
		//Check if the pictures are the same (it was loaded properly)
		
		//this will be a picture bigger than 64kb
		File bigPictureFile=bigPicture.png;
		test.removePicture();
		test.addPicture(bigPictureFile);
		assertEquals(null, test.getPicture());
		
		
	}
	
	public void postButtonTest()
	{
		testPostButton = (Button) AskActivity.findViewById(
				com.example.f14t07_application.activity_askactivity.R.id.PostButton);	
		testPostButton.performClick();
		Intent newintent = getStartedActivityIntent();
		Intent AskIntent= new Intent(AskTest.this, AskActivity.class); //where should this go? Some view activity?
		assertTrue(newintent.filterEquals(AskIntent));
	}
	
	public void mainMenuButtonTest()
	{
		testMainMenuButton = (Button) AskActivity.findViewById(
				com.example.f14t07_application.activity_askactivity.R.id.MainMenuButton);	
		testMainMenuButton.performClick();
		Intent newintent = getStartedActivityIntent();
		Intent AskIntent= new Intent(AskTest.this, HomeActivity.class); 
		assertTrue(newintent.filterEquals(AskIntent));
	}

}
