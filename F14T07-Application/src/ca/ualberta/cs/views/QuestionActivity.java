package ca.ualberta.cs.views;

import java.util.ArrayList;
import java.util.List;

import ca.ualberta.cs.controllers.ForumEntryController;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.f14t07_application.R.layout;
import ca.ualberta.cs.intent_singletons.ForumEntrySingleton;
import ca.ualberta.cs.models.Entry;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
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
	 * TODO: Allow user to save the question as favourite/read later
	 * TODO: Display an upvote count for question and answers
	 * TODO: Display a place to show/hide comments
	 * TODO: User can sort answer by upvotes
	 * TODO: Home screen button
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
		/*
		 * TODO: This ForumEntry needs to be added to the read later cache. This is because
		 * every ForumEntry viewed needs to be cached as a read later. Unless I am misunderstanding
		 * the requirements. 
		 */
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
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
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
		 * Update the focus of the ForumEntrySingleton. I do not see any reason for doing this, nor do 
		 * I see any reason for not doing this. That is why I am doing this. I don't know if it is going to
		 * help (because I don't know android well enough), but I know this is not going to interfere
		 * with proper operation. Remove this if you know it can be removed safely.
		 */
		ForumEntrySingleton.getInstance().setForumEntry(focus);
		
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
