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
	public void NameTest()//should this be testName() of testGetName()??
	{
		//Will we be saving user names? So should we check if the name exists?
		//Will users have to sign in?
		
		boolean notEmpty = false;

                ArrayList<ForumEntry> questions = new ArrayList<ForumEntry>();
                questions.add(new ForumEntry(new Entry("What is life?", "Kibbles")));
                questions.get(0).setAuthorsName("Timothy");


		String expectedName = "Timothy";
		String name = questions.get(0).getAuthorsName();

		if(!name.isEmpty() && name.trim().length() > 0)
		{
			notEmpty = true;
		}


		assertEquals(name, expectedName);
		assertTrue("The name string is empty", notEmpty == true);
	}
	
	public void getQuestionTest()//should this be testQuestion() of testGetQuestion()??
	{
		boolean notEmpty = false;

                ArrayList<ForumEntry> questions = new ArrayList<ForumEntry>();
                questions.add(new ForumEntry(new Entry("What is life?", "Kibbles")));

		String expectedQuestion = "What is life?";
		String question = questions.get(0).getPost();

		if(!question.isEmpty() && question.trim().length() > 0)
		{
			notEmpty = true;
		}

		assertEquals(question, expectedQuestion);

		assertTrue("The question string is empty", notEmpty == true);
	}
	
	public void getSubjectTest() //should this be testSubject() of testGetSubject()??
	{
		boolean notEmpty = false;

		ArrayList<ForumEntry> questions = new ArrayList<ForumEntry>();
                questions.add(new ForumEntry(new Entry("What is life?", "Kibbles")));
                questions.get(0).setSubject("Life");

		String expectedSubject = "Life";
		String subject = questions.get(0).getSubject(); 

		if(!subject.isEmpty() && subject.trim().length() > 0)
		{
			notEmpty = true;
		}

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
