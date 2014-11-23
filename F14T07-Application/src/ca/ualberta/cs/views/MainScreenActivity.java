package ca.ualberta.cs.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.cs.controllers.AuthorController;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.intent_singletons.BrowseRequestSingleton;
import ca.ualberta.cs.intent_singletons.ContextSingleton;
import ca.ualberta.cs.intent_singletons.ForumEntrySingleton;
import ca.ualberta.cs.models.AuthorModel;
/**
 * This class is the view for the first screen that the user sees.
 * It is an observer to the authorModel
 * It has an instance of the AuthorController
 * From this class, many other screens can be reached.
 * 
 * @author Dayna
 * @author Brendan
 *
 */
public class MainScreenActivity extends Activity implements Observer<AuthorModel>
{

	private AuthorController authorController;
	public static final String TEXT_KEY = "TEXT";
	public static final String NEW_QUESTION_KEY = "NEW_QUESTION";
	public Intent intent2; //FOR TESTING
	public AlertDialog alert2;//FOR TESTING
	public EditText name;
	public TextView nameDisplayed;

/**
 * This function contains all the main screen on click listeners.
 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_screen);

		this.authorController = new AuthorController(this);
		this.authorController.setSessionAuthor(AuthorModel.NO_AUTHOR);

		/*
		 * Set the context in the context singleton to be this activity. Since
		 * this is the first activity that starts up when this app starts
		 * this will ensure that the context singleton has a valid context
		 * in it for doing context stuff.
		 */
		ContextSingleton.getInstance().setContext(this.getApplicationContext());
		
		/* Ask Button on click listener */
		Button ask_button = (Button) findViewById(R.id.askButton);
		ask_button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				askButton();
			}
		});
		
		/* Browse Button on click listener*/
		Button browse_button = (Button) findViewById(R.id.browseButton);
		browse_button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				browseButton();
			}
		});
		
		/* Search Button on click listener */
		Button search_button = (Button) findViewById(R.id.searchButton);
		search_button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				searchButton();
			}
		});
		
		/* Sign In Button on click listener */
		Button sign_in_button = (Button) findViewById(R.id.signInButton);
		sign_in_button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				signInButton();
			}
		});
		
		/* Sign Out Button on click listener*/
		Button sign_out_button = (Button) findViewById(R.id.signOutButton);
		sign_out_button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				signOutButton();
			}
		});
		/* Set location button on click listener*/
		Button set_location_button = (Button) findViewById(R.id.setLocationButton);
		set_location_button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				setLocationButton();
			}
		});
	}
/**
 * Refreshes the view to reflect the author name
 */
	@Override
	protected void onRestart()
	{
		super.onRestart();

		/* Refresh the view, the authors name may have changed you know :) */
		/* no... no... no.... They can only change their name on this screen! >:-|*/
		this.authorController.refresh();
	}

	/**
	 * adds items to the menu bar
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	/**
	 * Allows user to navigate through some screens using the menu bar
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		
		int id = item.getItemId();
		Intent intent;
		intent = new Intent(this, BrowseActivity.class);
		BrowseRequestSingleton.getInstance().setSearchToken(BrowseRequestSingleton.SEARCH_EVERYTHING);
		switch (id)
		{
		
		case R.id.switchToMyQuestions:
			/*
			 * Set the search and view tokens in the BrowseRequestSingleton, this way, the browse activity
			 * knows what to search for and what view to present when starting up.
			 */
			BrowseRequestSingleton.getInstance().setViewToken(BrowseRequestSingleton.MY_AUTHORED_VIEW);
			startActivity(intent);
			return true;
			
		case R.id.switchToReadLater:
			/*
			 * Set the search and view tokens in the BrowseRequestSingleton, this way, the browse activity
			 * knows what to search for and what view to present when starting up.
			 */
			BrowseRequestSingleton.getInstance().setViewToken(BrowseRequestSingleton.READ_LATER_VIEW);
			startActivity(intent);
			return true;
			
		case R.id.switchToFavourites:
			/*
			 * Set the search and view tokens in the BrowseRequestSingleton, this way, the browse activity
			 * knows what to search for and what view to present when starting up.
			 */
			BrowseRequestSingleton.getInstance().setViewToken(BrowseRequestSingleton.FAVOURITES_VIEW);
			startActivity(intent);
			return true;
			
		case R.id.help:
			String helpText = "This is the home screen. \n\n Here you can sign in using the Sign In button on the bottom right of the screen. " +
					"\n\n You can also set your location using GPS or by typing in a location by pressing the Set Location Button. \n\n" +
					"Using the three buttons in the centre of the screen, you can ask a question, browse through already posted questions, and search through our questions. \n\n" +
					"To search, simply type a search term into the space provided and press the Search button. \n" +
					"\n From this screen you can also access your previously authored questions, your favourited questions, and your saved questions. " +
					"You can do this by pressing the ellipsis button in the corner and clicking on the desired menu option. \n\n" +
					"As you navigate through our app, if you find yourself in need of more information, please click the help button located in the menu of each screen.\n\n" +
					"Thank you, and enjoy your time on our app :)";
			Intent helpIntent = new Intent(MainScreenActivity.this, HelpActivity.class);
			helpIntent.putExtra("HELP_TEXT", helpText);
			startActivity(helpIntent);
			return true;
			
		default:
			
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Called when the signInButton is clicked
	 * This function calls up a dialog with an editText
	 * that allows the user to type in a username
	 * 
	 * The code in this function has been heavily borrowed from this site
	 * http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
	 * Their license states that I am free to reuse and modify their code 
	 * with no restrictions
	 */
	public void signInButton()
	{
		final Button signInButton = (Button) findViewById(R.id.signInButton);
		final TextView text = (TextView) findViewById(R.id.signedInAs);

		// adopted from
		// http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
		// according to http://www.androidsnippets.com/about I am free to reuse
		// and modify this
		// code without any restrictions
		AlertDialog.Builder alert = new AlertDialog.Builder(MainScreenActivity.this);
		alert.setTitle("Sign In");
		alert.setMessage("What would you like your default name to be?");
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{

				String authorName = input.getText().toString();
				authorController.setSessionAuthor(authorName);
				nameDisplayed=(TextView) findViewById(R.id.signedInAs);
				
			}
		});
		
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// nothing

			}
		});
		alert.show();
		//alert2=alert.show();//FOR TESTING !!! Breaks activity
		alert2 = alert.create();
		name=input;

		// ^adopted from
		// http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog

	}

	/**
	 * Called when the sign out button is clicked.
	 * Sets the authorName back to null
	 * */
	public void signOutButton()
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(MainScreenActivity.this);
		alert.setTitle("Sign Out");
		alert.setMessage("Are you sure you want to sign out?");

		alert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				Button signInButton = (Button) findViewById(R.id.signInButton);
				Button signOutButton = (Button) findViewById(R.id.signOutButton);
				TextView text = (TextView) findViewById(R.id.signedInAs);

				authorController.setSessionAuthor(AuthorModel.NO_AUTHOR);
				text.setVisibility(4);
				signInButton.setVisibility(0);
				signOutButton.setVisibility(4);
				nameDisplayed=text;
			}
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// nothing

			}
		});
		alert.show();
		//alert2=alert.show(); //FOR TESTING !!! breaks activity
		alert2 = alert.create();
	}
	public void setLocationButton()
	{
		final Button setLocationButton = (Button) findViewById(R.id.setLocationButton);

		// adapted from
		// http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
		AlertDialog.Builder alert = new AlertDialog.Builder(MainScreenActivity.this);
		alert.setTitle("Set Location");
		alert.setMessage("How would you like to set your location?");
		final Spinner choices = new Spinner(this);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_dropdown_item);
		choices.setAdapter(adapter);
		alert.setView(choices);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				//Figure out what the user chose. If they chose to set their own location, 
				//pop up another dialog that lets them type a location
				String choice = choices.getSelectedItem().toString();
				
				
				if (choice.equals("GPS")){
					setLocationByGPS();
				} else if (choice.equals("Set Myself")) {
					setLocationByText();
				} else {
					Toast.makeText(MainScreenActivity.this, "Problem: " + choice, Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// nothing

			}
		});
		alert.show();
		//alert2=alert.show();//FOR TESTING !!! Breaks activity
		//alert2 = alert.create();
		//name=input;doGPSstuff

		// ^adopted from
		// http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog

	}
	/**
	 * opens the askActivity via an intent
	 */
	public void askButton()
	{
		/*
		 * Set the focus of the ForumEntrySingleton to null, which indicates we are asking a question.
		 * Then, start the AskActivity to ask the question.
		 */
		long key = 0;
		Intent intent = new Intent(this, AskActivity.class);
		intent2 = intent;
		intent.putExtra(MainScreenActivity.NEW_QUESTION_KEY, key);
		ForumEntrySingleton.getInstance().setForumEntry(null);
		startActivity(intent);
	}

	/**
	 * opens the browseActivity via an intent
	 */
	public void browseButton()
	{
		/*
		 * Set the search and view tokens in the BrowseRequestSingleton, this way, the browse activity
		 * knows what to search for and what view to present when starting up.
		 */
		Intent intent = new Intent(this, BrowseActivity.class);
		intent2 = intent;
		BrowseRequestSingleton.getInstance().setSearchToken(BrowseRequestSingleton.SEARCH_EVERYTHING);
		BrowseRequestSingleton.getInstance().setViewToken(BrowseRequestSingleton.ON_LINE_VIEW);
		startActivity(intent);
	}

	/**
	 * opens the searchActivity via an intent
	 * and sends the search term through the intent
	 */
	public void searchButton()
	{
		/*
		 * Set the search and view tokens in the BrowseRequestSingleton, this way, the browse activity
		 * knows what to search for and what view to present when starting up.
		 */
		EditText editableTerm = (EditText) findViewById(R.id.searchTerm);
		String term = (String) editableTerm.getText().toString();
		Intent intent = new Intent(this, BrowseActivity.class);
		intent2 = intent;
		BrowseRequestSingleton.getInstance().setSearchToken(term);
		BrowseRequestSingleton.getInstance().setViewToken(BrowseRequestSingleton.ON_LINE_VIEW);
		startActivity(intent);
	}

	/**
	 * updates the author
	 */
	@Override
	public void update(AuthorModel model)
	{
		Button signInButton = (Button) findViewById(R.id.signInButton);
		Button signOutButton = (Button) findViewById(R.id.signOutButton);
		TextView text = (TextView) findViewById(R.id.signedInAs);

		String author = model.getSessionAuthor();
		if (author != AuthorModel.NO_AUTHOR)
		{
			text.setText("Signed in as: " + author);
			text.setVisibility(0);
			signInButton.setVisibility(4);
			signOutButton.setVisibility(0);
		} else {
			
		}
	}
	
	public void setLocationByText(){
		AlertDialog.Builder alert = new AlertDialog.Builder(MainScreenActivity.this);
		alert.setTitle("Set Location");
		alert.setMessage("What is your location?");
		final EditText location = new EditText(this);
		alert.setView(location);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				String UserLoc = location.getText().toString();
				Toast.makeText(MainScreenActivity.this, UserLoc , Toast.LENGTH_SHORT).show();
				authorController.setSessionLocation(UserLoc);
			}
		});
		
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// nothing
			}
		});
		alert.show();
	}
	
	public void setLocationByGPS() {
		LocationManager locMan = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		/*LocationListener locLis = new LocationListener() {
			public void onLocationChanged(Location location) {
			      // Called when a new location is found by the network location provider.
			      makeUseOfNewLocation(location);
			}

			public void onStatusChanged(String provider, int status, Bundle extras) {}

			public void onProviderEnabled(String provider) {}

			public void onProviderDisabled(String provider) {}

		};
		//The zero parameters set the frequency at which we receive updates (0's mean as often as possible)
	locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locLis); 
	
	*/
	
	}
}











