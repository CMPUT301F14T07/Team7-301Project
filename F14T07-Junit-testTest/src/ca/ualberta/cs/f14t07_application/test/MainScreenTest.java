package ca.ualberta.cs.f14t07_application.test;

import java.util.concurrent.TimeUnit;

import android.app.AlertDialog;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import ca.ualberta.cs.controllers.AuthorController;
import ca.ualberta.cs.models.AuthorModel;
import ca.ualberta.cs.views.MainScreenActivity;

public class MainScreenTest extends ActivityInstrumentationTestCase2<MainScreenActivity> {
	private MainScreenActivity testActivity;
	private Context ctx;
	private Button testButton;
	private Button signInButton;
	
	
	public MainScreenTest()
	{
		super(MainScreenActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();		
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
	
	
	public void testsignInandOut(){
		MainScreenActivity activity = getActivity();
		final String authorName="User123";
		final Button signInButton=(Button) testActivity.findViewById(ca.ualberta.cs.f14t07_application.R.id.signInButton);
		final Button signOutButton=(Button) testActivity.findViewById(ca.ualberta.cs.f14t07_application.R.id.signOutButton);

		//final Button positiveButton=testActivity.alert2.getButton(AlertDialog.BUTTON_POSITIVE);

		activity.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
				signInButton.performClick();
				testActivity.name.setText(authorName);
				final Button positiveButton=testActivity.alert2.getButton(AlertDialog.BUTTON_POSITIVE);
				positiveButton.performClick();
			}
		});
		
		
		assert(testActivity.nameDisplayed.getText().toString().contains(authorName));
		assertEquals(signInButton.getVisibility(), 0);
		assertEquals(signOutButton.getVisibility(),4);
	
		activity.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
				signOutButton.performClick();
				final Button positiveSignOut=testActivity.alert2.getButton(AlertDialog.BUTTON_POSITIVE);

				positiveSignOut.performClick();

			}
		});		
		
		assertEquals(testActivity.nameDisplayed, null);
	}

	public void testSetUsername(){
				MainScreenActivity activity = getActivity();
				final String author="John";
				final AuthorController ac= new AuthorController(getActivity());
				
				activity.runOnUiThread(new Runnable (){ 
					@Override 
					public void run(){ 
						ac.setSessionAuthor(author);
					}
				});
				
				try{TimeUnit.SECONDS.sleep(1);}
				catch (InterruptedException e){}
				assertEquals((new AuthorModel()).getSessionAuthor(), author);
			}
	
	public void testSetLocationByText(){
		MainScreenActivity activity = getActivity();
		final String location="Edmonton";
		final AuthorController ac= new AuthorController(getActivity());
		
		activity.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
				ac.setSessionLocation(location);
				ac.setLocationBool(true);
			}
		});
		
		try{TimeUnit.SECONDS.sleep(1);}
		catch (InterruptedException e){}
		assertEquals((new AuthorModel()).getSessionLocation(), location);
		assertEquals((new AuthorModel()).isSet(), true);
	}
	
	public void testSetLocationByGPS(){
		MainScreenActivity activity = getActivity();
		final double latitude = 123.0;
		final double longitude = 18.0;
		final AuthorController ac= new AuthorController(getActivity());
		
		activity.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
				ac.setSessionLatitude(latitude);
				ac.setSessionLongitude(longitude);
				ac.setLocationBool(true);
			}
		});
		
		try{TimeUnit.SECONDS.sleep(1);}
		catch (InterruptedException e){}
		assertEquals((new AuthorModel()).getSessionLongitude(), longitude);
		assertEquals((new AuthorModel()).getSessionLatitude(), latitude);
		assertEquals((new AuthorModel()).isSet(), true);
	}
	
	public void testUnsetLocation(){
		MainScreenActivity activity = getActivity();
		final AuthorController ac= new AuthorController(getActivity());
		
		activity.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
				ac.setLocationBool(false);
			}
		});
		
		try{TimeUnit.SECONDS.sleep(1);}
		catch (InterruptedException e){}
		assertEquals((new AuthorModel()).isSet(), false);
		

	}
}
