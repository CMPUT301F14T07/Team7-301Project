package ca.ualberta.cs.views;

import ca.ualberta.cs.controllers.AuthorController;
import ca.ualberta.cs.controllers.BrowseController;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.f14t07_application.R.id;
import ca.ualberta.cs.f14t07_application.R.layout;
import ca.ualberta.cs.f14t07_application.R.menu;
import ca.ualberta.cs.intent_singletons.BrowseRequestSingleton;
import ca.ualberta.cs.intent_singletons.ForumEntrySingleton;
import ca.ualberta.cs.models.AuthorModel;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
	}
/**
 * Refreshes the view to reflect the author name
 */
	@Override
	protected void onRestart()
	{
		super.onRestart();

		/* Refresh the view, the authors name may have changed you know :) */
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
		switch (id)
		{
		
		case R.id.switchToMyQuestions:
			
			Intent myQsIntent = new Intent(this, MyQuestionsActivity.class);
			startActivity(myQsIntent);
			return true;
			
		case R.id.switchToReadLater:
			
			Intent readLaterIntent = new Intent(this, ReadLaterActivity.class);
			startActivity(readLaterIntent);
			return true;
			
		case R.id.switchToFavourites:
			
			Intent favoritesIntent = new Intent(this, FavouriteActivity.class);
			startActivity(favoritesIntent);
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
		Intent intent = new Intent(this, SearchActivity.class);
		intent2 = intent;
		intent.putExtra(MainScreenActivity.TEXT_KEY, term);
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
			text.setVisibility(4);
			signInButton.setVisibility(0);
			signOutButton.setVisibility(4);
		}
	}
}
