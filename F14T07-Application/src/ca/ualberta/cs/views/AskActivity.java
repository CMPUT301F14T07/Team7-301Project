package ca.ualberta.cs.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.intent_singletons.ForumEntrySingleton;
import ca.ualberta.cs.models.Answer;
import ca.ualberta.cs.models.AuthorModel;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;

/**
 * This is the view that is shown to the user when they would like to ask a
 * question. It includes setting a subject and being able to ask questions
 * 
 * @author lexie
 */
public class AskActivity extends Activity implements Observer<ForumEntryList>
{
	public Intent intent;
	public Intent intent2;
	public Context ctx;

	private AuthorModel authorModel;

	private Runnable doFinishAdd = new Runnable()
	{
		public void run()
		{
			finish();
		}
	};

	/**
	 * lays out the screen and creates onClickListeners
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ask_activity_screen);

		this.authorModel = new AuthorModel();

		/* Submit Button on click listener */
		Button submitButton = (Button) findViewById(R.id.askButton);
		submitButton.setOnClickListener(new View.OnClickListener()
		{
			/**
			 * gets necessary info and opens question screen
			 */
			@Override
			public void onClick(View v)
			{
				/*
				 * Get an instance of the forum entry singleton so that we can check what ForumEntry it
				 * is focusing on.
				 */
				ForumEntrySingleton forumEntryFocus = ForumEntrySingleton.getInstance();
				
				// new edit text boxes
				EditText newEntryEdit = (EditText) findViewById(R.id.question);
				EditText newSubjectEdit = (EditText) findViewById(R.id.subject);
				EditText newAuthorEdit = (EditText) findViewById(R.id.name);

				// set strings from edit text boxes
				String newEntry = newEntryEdit.getText().toString();
				String newSubject = newSubjectEdit.getText().toString();
				String newAuthor = newAuthorEdit.getText().toString();

				/* 
				 * If the forumEntrySingleton is focusing on nothing (ie a null ForumEntry) then we are trying to create a
				 * new question. Do that, then change the focus onto the newly created ForumEntry.
				 */
				if(forumEntryFocus.getForumEntry() == null)
				{
					// create a new ForumEntry
					ForumEntry newForumEntry = new ForumEntry(newSubject, newEntry, newAuthor);
					forumEntryFocus.setForumEntry(newForumEntry);

					Thread thread = new AddThread(newForumEntry);
					thread.start();
				}
				/*
				 * The ForumEntrySingleton is focusing on a ForumEntry. This means we are trying to add an answer to the focused ForumEntry.
				 */
				else
				{
					Answer answer = new Answer(newEntry, newAuthor);
					//TODO Add the answer to the ForumEntry in forumEntryFocus.getForumEntry
				}

				newEntryEdit.setText("");
				newSubjectEdit.setText("");
				newAuthorEdit.setText("");

				/*
				 * Start the question activity to view the new question or appended answer.
				 */
				intent = new Intent(AskActivity.this, QuestionActivity.class);
				intent2 = intent;
				startActivity(intent);

			}
		});

		// the button the user clicks to attach a file
		Button attach_button = (Button) findViewById(R.id.attachButton);
		attach_button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// in here we need to put file attachment shit
				Toast.makeText(AskActivity.this,
						"Picture Attachment still needs to be added",
						Toast.LENGTH_SHORT).show();

			}
		});
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		ctx = this.getApplicationContext();
		
		/*
		 * Set the name of the author to be the sessionAuthor in the authorModel 
		 */
		EditText newAuthorEdit = (EditText) findViewById(R.id.name);
		newAuthorEdit.setText(this.authorModel.getSessionAuthor());
		
		/*
		 * If the focus of the ForumEntrySingleton is not null then we are answering a
		 * question, not creating one. Therefore, we need to remove the subject text element.
		 */
		if(ForumEntrySingleton.getInstance().getForumEntry() != null)
		{
			EditText newSubjectEdit = (EditText) findViewById(R.id.subject);
			newSubjectEdit.setVisibility(EditText.INVISIBLE);
		}
	}

	class AddThread extends Thread
	{
		private ForumEntry forumEntry;
		private DataManager dataManager;

		public AddThread(ForumEntry forumEntry_)
		{
			forumEntry = forumEntry_;
			dataManager = new DataManager(ctx);
		}

		@Override
		public void run()
		{

			// AddForumEntry afm = new AddForumEntry();

			dataManager.addForumEntry(forumEntry);
			// Give some time to get updated info
			try
			{
				Thread.sleep(500);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			runOnUiThread(doFinishAdd);
		}
	}

	@Override
	public void update(ForumEntryList model)
	{
		// TODO Auto-generated method stub

	}
}