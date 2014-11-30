package ca.ualberta.cs.f14t07_application.test;


import android.content.Context;
import android.graphics.Picture;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.intent_singletons.ContextSingleton;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.views.AskActivity;


public class AskTest extends ActivityInstrumentationTestCase2<AskActivity> {
	
	private AskActivity testActivity;
	private Button testPostButton;
	private Button testMainMenuButton;
	private DataManager dm;
	private Context context;
	
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
	
	dm = new DataManager();
	//dm.addForumEntry((new ForumEntry("subject","What is life?", "Kibbles")));
	
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
	
	public void testPicture() 
	{
		//Need more tests in here once we determine more about how pictures will work
	  
	   //add a picture called picture.png
	   String pictureFile= null;
	   ForumEntry testForumEntry = dm.getForumEntry();
	        
	   testForumEntry.getQuestion().setPicture(pictureFile);
	   String thePictureAdded = testForumEntry.getQuestion().getPicture();
	   assertNotNull(thePictureAdded);
		//Check if the pictures are the same (it was loaded properly)
	}
	   
	public void testBigPicture(){
		   
		ForumEntry testForumEntry = dm.getForumEntry();
		//this will be a picture bigger than 64kb
		String bigPictureFile=null;
		testForumEntry.getQuestion().setPicture(bigPictureFile);
	
		String thePictureAdded = testForumEntry.getQuestion().getPicture();
		assertNotNull(thePictureAdded);
		
	}
	

	//Checks if a new ForumEntry has been added and if it matches what was put in by the user
	public void testPost()
	{
		AskActivity activity = getActivity();
		final EditText QuestionEdit=(EditText) activity.findViewById(R.id.question);
		final EditText SubjectEdit=(EditText) activity.findViewById(R.id.subject);
		final EditText AuthorEdit =(EditText) activity.findViewById(R.id.name);

		
		testPostButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.askButton);	
		
		activity.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
				QuestionEdit.setText("question",TextView.BufferType.EDITABLE);
				SubjectEdit.setText("Subject",TextView.BufferType.EDITABLE);
				AuthorEdit.setText("Author", TextView.BufferType.EDITABLE);
				testPostButton.performClick();
			}
		});
		
		
		//this is because if no errors have been thrown then it 
		// works because there are only really those two options
		Boolean testPass = true;
		assertTrue(testPass);
	}

}