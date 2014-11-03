package ca.ualberta.cs.views;

import ca.ualberta.cs.controllers.AuthorController;
import ca.ualberta.cs.controllers.BrowseController;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.f14t07_application.R.id;
import ca.ualberta.cs.f14t07_application.R.layout;
import ca.ualberta.cs.f14t07_application.R.menu;
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

public class MainScreenActivity extends Activity implements Observer<AuthorModel>
{

	private AuthorController authorController;
	public static final String TEXT_KEY = "TEXT";
	public static final String NEW_QUESTION_KEY = "NEW_QUESTION";

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
	}

	@Override
	protected void onRestart()
	{
		super.onRestart();

		/* Refresh the view, the authors name may have changed you know :) */
		this.authorController.refresh();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	// option menu to move to different activities
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
			
		default:
			
			return super.onOptionsItemSelected(item);
		}
	}

	public void signInButton()
	{
		final Button signInButton = (Button) findViewById(R.id.signInButton);
		final TextView text = (TextView) findViewById(R.id.signedInAs);

		// adopted from
		// http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
		// according to http://www.androidsnippets.com/about I am free to reuse
		// and modify this
		// code without any restrictions
		AlertDialog.Builder alert = new AlertDialog.Builder(
				MainScreenActivity.this);
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
			}
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// TODO Auto-generated method stub

			}
		});
		alert.show();

		// ^adopted from
		// http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog

	}

	public void askButton()
	{
		long key = 0;
		Intent intent = new Intent(this, AskActivity.class);
		
		intent.putExtra(MainScreenActivity.NEW_QUESTION_KEY, key);
		startActivity(intent);
	}

	public void browseButton()
	{
		Intent intent = new Intent(this, BrowseActivity.class);
		startActivity(intent);
	}

	public void searchButton()
	{
		EditText editableTerm = (EditText) findViewById(R.id.searchTerm);
		String term = (String) editableTerm.getText().toString();
		Intent intent = new Intent(this, SearchActivity.class);
		
		intent.putExtra(MainScreenActivity.TEXT_KEY, term);
		startActivity(intent);
	}

	@Override
	public void update(AuthorModel model)
	{
		Button signInButton = (Button) findViewById(R.id.signInButton);
		TextView text = (TextView) findViewById(R.id.signedInAs);

		String author = model.getSessionAuthor();
		if (author != null)
		{
			text.setText("Signed in as: " + author);
			text.setVisibility(0);
			signInButton.setText("Change User");
		}
	}
}
