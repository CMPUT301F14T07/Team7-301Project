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
	private ArrayAdapter<Reply> replyListAdapter;
	private List<Reply> replyList;
	private ListView replyListView;
	private ImageView showPicture;
	
	/**
	 * Called when this activity is first created. Instantiate class variables here and
	 * create on click listeners here.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		
		this.replyList = new ArrayList<Reply>();
		this.forumEntryController = new ForumEntryController(this);

		
		this.replyListAdapter = new ArrayAdapter<Reply>(QuestionActivity.this, R.layout.list_item, this.replyList);
		this.replyListView = (ListView) findViewById(R.id.QuestionReplyList);
		this.replyListView.setAdapter(this.replyListAdapter);
		showPicture = (ImageView)findViewById(R.id.picture);
        
		
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
		Button replyButton = (Button) findViewById(R.id.QuestionReplyButton);
		replyButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				replyQuestion();
			}
		});
		
		/*
		 * On click listener for upvote button.
		 */
		Button upvoteButton = (Button) findViewById(R.id.QuestionUpvoteButton);
		upvoteButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				upVoteEntry();
			}
		});
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
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(homeIntent);
			return true;
		case R.id.help:
			String helpText = "This is what we refer to as a Question Screen.\n\n" +
					"Here you can view a question, it's replies, it's answers, and the answer's replies. \n\n" +
					"If you would like to save this question as a favourite or simply to read offline, please press the Save button. " +
					"This will prompt you to choose specifically how you would like the question to be saved. Choose whichever you prefer. \n\n" +
					"If you would like to answer this question, press the Add An Answer button located at the bottom of the screen.  " +
					"This will open a new screen which will prompt you for your answer." +
					"You can navigate to other screens either by clicking the " +
					"back button, or by using the menu found by clicking the ellipsis in the corner.";
			Intent helpIntent = new Intent(QuestionActivity.this, HelpActivity.class);
			helpIntent.putExtra("HELP_TEXT", helpText);
			startActivity(helpIntent);
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}


	public Dialog onCreateDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setMessage("Select a save method");
	    builder.setCancelable(true);
		builder.setPositiveButton(R.string.savefave, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   forumEntryController.saveFavouritesCopy();
	        	   Toast.makeText(getApplicationContext(), "Saved to Favourites", Toast.LENGTH_SHORT).show();
	           }
	       });
		builder.setNegativeButton(R.string.savereadlater, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               forumEntryController.saveReadLaterCopy();
	               Toast.makeText(getApplicationContext(), "Saved to read later", Toast.LENGTH_SHORT).show();
	           }
		});

		// Create the AlertDialog
		AlertDialog dialog = builder.create();
		dialog.show();
		return dialog;
	}
	
	
	
	
	/**
	 * This function will prompt the user to save to favourites or save to read later
	 */
	private void saveButton()
	{
		onCreateDialog();
	}
	
	/**
	 * This function will start the AskActivity so that the user can answer the 
	 * question (ie, append an Answer to the ForumEntry).
	 */
	private  void replyQuestion()
	{
		/*
		 * Start the AskActivity to enter an answer to the forum entry.
		 * Note that the ForumEntrySingleton does not have to be set, because there is no way
		 * it could have been changed by another activity.
		 */

		ForumEntrySingleton.getInstance().setReplyFlag();

		Intent intent = new Intent(this, AskActivity.class);
		startActivity(intent);
	}
	
	/**
	 * This function will upvote the question (the input 0 indicates to upvote the question).
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
		this.replyList.clear();
		this.replyList.addAll(focus.getQuestionReplies());
		this.replyListAdapter.notifyDataSetChanged();
		/*
		if(focus.getQuestion().getPicture()!=null){
		showPicture.setImageBitmap(focus.getQuestion().getPicture());
		}*/
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
		
		/*
		 * Set the questions author in the view.
		 */
		TextView authorText = (TextView) findViewById(R.id.QuestionAuthor);
		authorText.setText(focus.getQuestion().getAuthorsName());
		
		/*
		 * Set the questions date in the view.
		 */
		TextView dateText = (TextView) findViewById(R.id.QuestionDate);
		dateText.setText(focus.getQuestion().getDate().toString());
		
		/*
		 * Set the upvote number in the view.
		 */
		TextView voteText = (TextView) findViewById(R.id.QuestionUpvoteNumber);
		String vote = String.valueOf(focus.getQuestion().getUpVote());
		voteText.setText(vote);
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
