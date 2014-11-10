package ca.ualberta.cs.f14t07_application.test;

import android.app.AlertDialog;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import ca.ualberta.cs.views.MainScreenActivity;
import ca.ualberta.cs.views.QuestionActivity;
import junit.framework.TestCase;

public class MainScreenTest extends ActivityInstrumentationTestCase2<MainScreenActivity> {
	private MainScreenActivity testActivity;
	private Context ctx;
	private Button testButton;
	private Button signInButton;
	
	
	public MainScreenTest()
	{
		super(MainScreenActivity.class);
		/* Turns off the touch screen in the emulator. This must be done to test features that
		 * would require the user to touch something on the screen.
		 */
		setActivityInitialTouchMode(false);

		/* Get an instance of the activity which is running
		 */
		testActivity = getActivity();
		ctx = testActivity.getApplicationContext();
		/* Reset the testButton - do this so consecutive tests don't accidentally test
		 * the same button.
		 */
		testButton = null;
	}
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void signInTest(){
		final String authorName="User123";
		Button signInButton=(Button) testActivity.findViewById(ca.ualberta.cs.f14t07_application.R.id.signInButton);
		Button signOutButton=(Button) testActivity.findViewById(ca.ualberta.cs.f14t07_application.R.id.signOutButton);
		
		signInButton.performClick();
		
		testActivity.name.setText(authorName);
		
		Button positiveButton=testActivity.alert2.getButton(AlertDialog.BUTTON_POSITIVE);
		
		positiveButton.performClick();
		
		assert(testActivity.nameDisplayed.getText().toString().contains(authorName));
		assertEquals(signInButton.getVisibility(), 4);
		assertEquals(signOutButton.getVisibility(),0);
	}
	
	public void signOutTest(){
		final String authorName="User123";
		Button signInButton=(Button) testActivity.findViewById(ca.ualberta.cs.f14t07_application.R.id.signInButton);
		Button signOutButton=(Button) testActivity.findViewById(ca.ualberta.cs.f14t07_application.R.id.signOutButton);
		
		signInButton.performClick();
		
		testActivity.name.setText(authorName);
		
		Button positiveButton=testActivity.alert2.getButton(AlertDialog.BUTTON_POSITIVE);
		
		positiveButton.performClick();
		
		assert(testActivity.nameDisplayed.getText().toString().contains(authorName));
		assertEquals(signInButton.getVisibility(), 4);
		assertEquals(signOutButton.getVisibility(),0);
		
		signOutButton.performClick();
		
		Button positiveSignOut=testActivity.alert2.getButton(AlertDialog.BUTTON_POSITIVE);
		
		positiveSignOut.performClick();
		
		assertEquals(testActivity.nameDisplayed.getVisibility(),0);
	}

}
