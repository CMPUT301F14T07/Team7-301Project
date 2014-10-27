package ca.ualberta.cs.f14t07_application.test;

import ca.ualberta.cs.f14t07_application.MainScreenActivity;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.view.ContextMenu;
import android.widget.Button;
import junit.framework.TestCase;

public class ButtonTest extends ActivityUnitTestCase<MainScreenActivity> {
	
	private Button AskButton;
	private Button SearchButton;
	private Button BrowseButton;
	private MainScreenActivity HomeActivity; 
	
	public ButtonTest()
	{
		super(MainScreenActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception
	{
	super.setUp();
	setActivityInitialTouchMode(true);
	HomeActivity = getActivity();
	}
	
	//Checks to see if AskActivity is opened when the ask button is clicked
	public void askButtonTest()
	{
		AskButton = (Button) HomeActivity.findViewById(
				com.example.f14t07_application.activity_homeactivity.R.id.AskButton);	
		AskButton.performClick();
		Intent newintent = getStartedActivityIntent();
		Intent AskIntent= new Intent(ButtonTest.this, AskActivity.class);
		assertTrue(newintent.filterEquals(AskIntent));
	}
	
	//Checks to see if BrowseActivity is opened when the browse button is clicked
	public void browseButtonTest()
	{
		BrowseButton = (Button) HomeActivity.findViewById(
				com.example.f14t07_application.activity_homeactivity.R.id.BrowseButton);	
		BrowseButton.performClick();
		Intent newintent = getStartedActivityIntent();
		Intent BrowseIntent= new Intent(ButtonTest.this, BrowseActivity.class);
		assertTrue(newintent.filterEquals(BrowseIntent));
	}
	
	//This one just tests to see that the button actually works when clicked because we don't know
	//what we want to do for the search button.
	public void searchButtonTest()
	{
		SearchButton = (Button) HomeActivity.findViewById(
				com.example.f14t07_application.activity_homeactivity.R.id.SearchButton);	
		//Check if search bar is empty
		boolean notEmpty = false;
		searchString = (String) HomeActivity.findViewById(
				com.example.f14t07_application.activity_homeactivity.R.id.SearchString);
		if(!searchString.isEmpty() && searchString.trim().length() > 0)
		{
			notEmpty = true;
		}
		assertTrue("The name string is empty", notEmpty == true);
		
		SearchButton.performClick();
		Intent newintent = getStartedActivityIntent();
		assertNotNull(newintent);
	}
	
	//Tests to see if the context menu items work when clicked (not if activity is opened)
	public void contextMenuTest()
	{
		ContextMenu contextMenu = HomeActivity.getContextMenu();
		assertTrue(contextMenu.performIdentifierAction(R.id.ViewMyQuestions, 0));
		assertTrue(contextMenu.performIdentifierAction(R.id.ViewReadLater, 0));
	}
}
