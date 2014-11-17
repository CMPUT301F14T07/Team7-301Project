package ca.ualberta.cs.views;

import java.util.ArrayList;
import java.util.List;

import ca.ualberta.cs.controllers.BrowseController;
import ca.ualberta.cs.controllers.ForumEntryController;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.f14t07_application.R.layout;
import ca.ualberta.cs.intent_singletons.BrowseRequestSingleton;
import ca.ualberta.cs.intent_singletons.ForumEntrySingleton;
import ca.ualberta.cs.models.Entry;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.views.BrowseActivity.SearchThread;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity displays a ForumEntry to the user. It displays the question, its answers, and their replies.
 * It also allows the user to create an answer to the question
 * @author bbruner
 *
 */
public class QuestionActivity extends Activity implements Observer<ForumEntryList>
{
	/*
	 * TODO: Allow user to save the question as favourite
	 * TODO: Display an upvote count for question and answers
	 * TODO: Display a place to show/hide comments
	 * TODO: User can sort answer by upvotes
	 * TODO: Home screen button
	 * TODO: Display Author's name by the Entry
	 * TODO: Back button takes you somewhere definitive (ask Brendan what this means)
	 */
	private ForumEntryController forumEntryController;
	private ArrayAdapter<Entry> answerListAdapter;
	private List<Entry> answerList;
	private ListView answerListView;
	
	
	/**
	 * Called when this activity is first created. Instantiate class variables here and
	 * create on click listeners here.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		
		this.answerList = new ArrayList<Entry>();
		this.forumEntryController = new ForumEntryController(this);

		
		this.answerListAdapter = new ArrayAdapter<Entry>(QuestionActivity.this, R.layout.list_item, this.answerList);
		this.answerListView = (ListView) findViewById(R.id.QuestionAnswerList);
		this.answerListView.setAdapter(this.answerListAdapter);
		
		/*
		 * On click listener for when the save button is pushed.
		 */
		Button save = (Button) findViewById(R.id.QuestionSaveButton);
		save.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				saveButton();
			}
		});
		
		/*
		 * On click listener for answer button.
		 */
		Button answerButton = (Button) findViewById(R.id.AddButton);
		answerButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				answerQuestion();
			}
		});
	}
	
	/**
	 * This function is called everytime this activity starts.
	 */
	@Override
	public void onStart()
	{
		super.onStart();
		
		/*
		 * Get the instance of the ForumEntrySingleton. Then, see what ForumEntry is being focused
		 * on and use the controller to set that in the view.
		 */
		ForumEntrySingleton fes = ForumEntrySingleton.getInstance();
		this.forumEntryController.setView(fes.getForumEntry());
		this.forumEntryController.saveReadLaterCopy();
	}

	/**
	 * Creates a menu when the ... is clicked in the view.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question, menu);
		return true;
	}

	/**
	 * Handles selection of items in the options menu.
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
			
		case R.id.switchToReadLaters:
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
			
		case R.id.switchToHome:
			
			Intent homeIntent = new Intent(this, MainScreenActivity.class);
			startActivity(homeIntent);
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * This function will add the ForumEntry being view to a favourites cache.
	 */
	private void saveButton()
	{
		/*
		 * TODO: prompts user if they want to save as favourite, or other thing and uses controller
		 * to do so.
		 */
		Toast.makeText(this, "Save not implemented", Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * This function will start the AskActivity so that the user can answer the 
	 * question (ie, append an Answer to the ForumEntry).
	 */
	private  void answerQuestion()
	{
		/*
		 * Start the AskActivity to enter an answer to the forum entry.
		 * Note that the ForumEntrySingleton does not have to be set, because there is no way
		 * it could have been changed by another activity.
		 */
		Intent intent = new Intent(this, AskActivity.class);
		startActivity(intent);
	}
	
	/**
	 * This function is called by the model. The view should be updated in this function.
	 */
	@Override
	public void update(ForumEntryList model)
	{
		ForumEntry focus = model.getView().get(ForumEntryList.FIRST_FORUM_ENTRY);
		
		/*
		 * Set the answer list first.
		 * Cannot use assignment (ie answerList = model.getView()) because then
		 * we would have to make a new adapter and then set that new adapter (due
		 * to java being call by value).
		 */
		this.answerList.clear();
		this.answerList.addAll(focus.getAnswers());
		this.answerListAdapter.notifyDataSetChanged();
		
		/*
		 * Set the questions subject in the view.
		 */
		TextView questionSubject = (TextView) findViewById(R.id.QuestionSubject);
		questionSubject.setText(focus.getQuestion().getSubject());
		
		/*
		 * Set the questions main body of text in the view.
		 */
		TextView questionText = (TextView) findViewById(R.id.QuestionText);
		questionText.setText(focus.getQuestion().getPost());
		
	
	}
}
