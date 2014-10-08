package ca.ualberta.cs.f14t07_application.test;

import ca.ualberta.cs.f14t07_application.Ask;
import ca.ualberta.cs.f14t07_application.LogoActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import ca.ualberta.cs.f14t07_application.Ask;;


public class AskTest extends ActivityInstrumentationTestCase2<AskActivity> {
	
	private Button testButton;
	
	public AskTest()
	{
		super(AskActivity.class);
	}
	
	/* The current functionality after clicking the "ask" button from the main menu 
	 * is as follows:
	 * enter a name
	 * enter a question
	 * enter a subject
	 * attach a picture
	 * click an "ask" or "submit" button
	 * click on a "main menu" button
	 */
	
	//Test if there is a name
	public void testName()
	{
		//Will we be saving user names? So should we check if the name exists?
		//Will users have to sign in?
		
		// Need to replace "something" with however we will be creating user names
		String name = new String("Testname");
		//name = something.getName();
		assert.assertNotNull("There is a user name object", name);
		
		//**Is there a better way to test that it is not only not null but also acutally a string?
	}
	
	public void testQuestion()
	{
		String question = new String("test question");
		//question = something.getQuestion();
		assert.assertNotNull("There is a question object", question);
	}
	
	public void testSubject()
	{
		String subject = new String("test subject");
		//subject = something.getSubject();
		assert.assertNotNull("There is a subject object", subject);
	}

	public void testPicture()
	{
		//Need more tests in here once we determine more about how pictures will work
		
		//Possible a boolean value in the questions attributes indicating if there is a picture?
		
		if( ...something.question.picture() == true)
		{
			Object picture = ...picture; //Need to figure out how we will deal with pictures
			//Check if the pictures are the same (it was loaded properly)
			
			//Maybe check picture size?
		}
	}
	
	public void testAskButton()
	{
		
	}
	
	public void testMainMenuButton()
	{
		
	}

}
