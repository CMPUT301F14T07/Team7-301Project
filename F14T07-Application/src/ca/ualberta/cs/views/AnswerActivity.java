package ca.ualberta.cs.views;

import java.util.ArrayList;
import java.util.List;

import ca.ualberta.cs.controllers.ForumEntryController;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.intent_singletons.BrowseRequestSingleton;
import ca.ualberta.cs.intent_singletons.ForumEntrySingleton;
import ca.ualberta.cs.models.Answer;
import ca.ualberta.cs.models.Entry;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.models.Reply;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity displays a ForumEntry to the user. It displays the question, its answers, and their replies.
 * It also allows the user to create an answer to the question
 * @author bbruner
 * @author jfryan
 *
 */
public class AnswerActivity extends Activity implements Observer<ForumEntryList>
{

	private ForumEntryController forumEntryController;
	private ArrayAdapter<Answer> answerListAdapter;
	private List<Answer> answerList;
	private ListView answerListView;
	
	/**
	 * Called when this activity is first created. Instantiate class variables here and
	 * create on click listeners here.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answers);
		
		this.answerList = new ArrayList<Answer>();
		this.forumEntryController = new ForumEntryController(this);

		this.answerListAdapter = new ArrayAdapter<Answer>(AnswerActivity.this, R.layout.list_item, this.answerList);
		this.answerListView = (ListView) findViewById(R.id.QuestionAnswerList);
		this.answerListView.setAdapter(this.answerListAdapter);
		
		/*
		 * On click listener for answer button.
		 */
		Button answerButton = (Button) findViewById(R.id.AddAnswerButton);
		answerButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				answerQuestion();
			}
		});
		
		/*
		 * On click listener for upvote button.
		 */
/*		Button upvoteButton = (Button) findViewById(R.id.AnswerUpvoteButton);
		upvoteButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				upVoteEntry();
			}
		});*/
	}
	
	/**
	 * This function is called every time this activity starts.
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
	}
	/**
	 * This is for testing purposes
	 * @param forumEntry
	 */
	public void setForumEntry(ForumEntry forumEntry){
		this.forumEntryController.setView(forumEntry);
	}
	/**
	 * Creates a menu when the ... is clicked in the view.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.answers, menu);
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
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(homeIntent);
			return true;
		case R.id.help:
			String helpText = "This is what we refer to as a Question Screen.\n\n" +
					"Here you can view a questions answers, and their replies. \n\n" +
					"If you would like to answer this question, press the Add An Answer button located at the bottom of the screen.  " +
					"This will open a new screen which will prompt you for your answer." +
					"You can navigate to other screens either by clicking the " +
					"back button, or by using the menu found by clicking the ellipsis in the corner.";
			Intent helpIntent = new Intent(AnswerActivity.this, HelpActivity.class);
			helpIntent.putExtra("HELP_TEXT", helpText);
			startActivity(helpIntent);
			
		default:
			return super.onOptionsItemSelected(item);
		}
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

		ForumEntrySingleton.getInstance().clearReplyFlag();

		Intent intent = new Intent(this, AskActivity.class);
		startActivity(intent);
	}
	
	/**
	 * This function will upvote the answer (the input 0 indicates to upvote the question).
	 */
	private void upVoteEntry()
	{
		UpVoteThread uThread = new UpVoteThread(0);
		uThread.start();
		forumEntryController.updateView();
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

	}

	class UpVoteThread extends Thread
	{
		private int index;
		
		public UpVoteThread(int index_)
		{
			index = index_;
		}
		
		@Override
		public void run()
		{
			forumEntryController.upVoteEntry(index);
		}
	}
}
