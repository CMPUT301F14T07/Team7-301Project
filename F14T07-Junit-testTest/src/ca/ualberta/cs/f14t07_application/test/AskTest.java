package ca.ualberta.cs.f14t07_application.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Picture;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.views.AskActivity;


public class AskTest extends ActivityInstrumentationTestCase2<AskActivity> {
	
	private AskActivity testActivity;
	private Button testPostButton;
	private Button testMainMenuButton;
	private DataManager dm;
	private Context ctx;
	
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
	ctx = testActivity.getApplicationContext();
	dm = new DataManager(ctx);
	dm.addForumEntry((new ForumEntry("subject","What is life?", "Kibbles")));
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
	public void testName()//should this be testName() of testGetName()??
	{
		//Will we be saving user names? So should we check if the name exists?
		//Will users have to sign in?
		
		boolean notEmpty = false;

    	ForumEntry testForumEntry; 
    	testForumEntry = dm.getForumEntry();

		String expectedName = "Kibbles";
		String name = testForumEntry.getQuestion().getAuthorsName();

		assertEquals(name, expectedName);
		
	}
	
	public void testGetQuestion()//should this be testQuestion() of testGetQuestion()??
	{


    	ForumEntry testForumEntry = dm.getForumEntry();
    	
		String expectedQuestion = "What is life?";
		String question = testForumEntry.getQuestion().getPost();


		assertEquals(question, expectedQuestion);
	}
	
	public void testGetSubject() //should this be testSubject() of testGetSubject()??
	{
		boolean notEmpty = false;

		ForumEntry testForumEntry = dm.getForumEntry();
		
		String expectedSubject = "subject";
		String subject = testForumEntry.getSubject(); 


		assertEquals(subject, expectedSubject);
	}

	public void testifPicture()  //test picture() or test ifPicture()?
	{
		//Need more tests in here once we determine more about how pictures will work
	  
	   //add a picture called picture.png
	   Picture pictureFile= null;
	   ForumEntry testForumEntry = dm.getForumEntry();
	        
	   testForumEntry.getQuestion().setPicture(pictureFile);
	   Picture thePictureAdded = testForumEntry.getQuestion().getPicture();
	        
		assertEquals(thePictureAdded,pictureFile);
		//Check if the pictures are the same (it was loaded properly)
		
		//this will be a picture bigger than 64kb
		Picture bigPictureFile=null;
		testForumEntry.getQuestion().setPicture(bigPictureFile);
	
		thePictureAdded = testForumEntry.getQuestion().getPicture();
		assertNotNull(thePictureAdded);
		assertEquals(pictureFile, thePictureAdded);
		
		
	}
	

	//Checks if a new ForumEntry has been added and if it matches what was put in by the user
	public void testPost()
	{
		AskActivity activity = getActivity();
		
		EditText QuestionEdit=(EditText) activity.findViewById(R.id.question);
		EditText SubjectEdit=(EditText) activity.findViewById(R.id.subject);
		EditText AuthorEdit =(EditText) activity.findViewById(R.id.name);

		String Question=QuestionEdit.getText().toString();
		String Subject=SubjectEdit.getText().toString();
		String Author= AuthorEdit.getText().toString(); 
		
		//testPostButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.askButton);	
		//testPostButton.performClick();
		

		ForumEntry question=dm.getForumEntry();
		///assert(arraylist.size() > 0);
		
		
		assertEquals(question.getQuestion().getAuthorsName(),Author);
		assertEquals(question.getSubject(),Subject);  //Currently does not exist
		assertEquals(question.getQuestion().getPost(),Question);
	}
	
	public void testPosttWithPicture()
	{
		AskActivity activity = getActivity();
		
		EditText QuestionEdit=(EditText) activity.findViewById(R.id.question);
		EditText SubjectEdit=(EditText) activity.findViewById(R.id.subject);
		EditText AuthorEdit =(EditText) activity.findViewById(R.id.name);

		String Question=QuestionEdit.getText().toString();
		String Subject=SubjectEdit.getText().toString();
		String Author= AuthorEdit.getText().toString(); 
		
		//Add a picture this won't work because 
		// we haven't added pictures yet, involves figuring out 
		// how to add pictures 
		
		//testPostButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.attachButton);	
		//testPostButton.performClick();
		
		//Post the question
		testPostButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.askButton);
		testPostButton.performClick();
		
		ForumEntry question = dm.getForumEntry();
		
		
		assertEquals(question.getQuestion().getAuthorsName(),Author);
		assertEquals(question.getSubject(),Subject); 
		assertEquals(question.getQuestion().getPost(),Question);
		assertNotNull(question.getQuestion().getPicture());
	}

}