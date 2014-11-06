package ca.ualberta.cs.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;

/**
 * This is the view that is shown to the user when they would 
 * like to ask a question. It includes setting a subjected and 
 * being able to ask questions
 */
public class AskActivity extends Activity
{
	public Intent intent;
	public Intent intent2;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ask_activity_screen);

		/* Submit Button on click listener */
		Button submitButton = (Button) findViewById(R.id.askButton);
		submitButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// new edit text boxes
				EditText newEntryEdit = (EditText) findViewById(R.id.question);
				EditText newSubjectEdit = (EditText) findViewById(R.id.subject);
				EditText newAuthorEdit = (EditText) findViewById(R.id.name);

				// set strings from edit text boxes
				String newQuestion = newEntryEdit.getText().toString();
				String newSubject = newSubjectEdit.getText().toString();
				String newAuthor = newAuthorEdit.getText().toString();

				// create a new ForumEntry
				ForumEntry newForumEntry = new ForumEntry(newSubject,
						newQuestion, newAuthor);
				Thread thread = new AddThread(newForumEntry);
				// Pass it to the controller
				thread.start();
				newEntryEdit.setText("");
				newSubjectEdit.setText("");
				newAuthorEdit.setText("");
				Toast.makeText(AskActivity.this, "Question Added",
						Toast.LENGTH_SHORT).show();

				// then an intent needs to open the question screen for the new
				// forum entry
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

}

class AddThread extends Thread
{
	private ForumEntry forumEntry;
	private DataManager dataManager = new DataManager();

	public AddThread(ForumEntry forumEntry_)
	{
		forumEntry = forumEntry_;
	}

	@Override
	public void run()
	{
		super.run();
		dataManager.addForumEntry(forumEntry);

		try
		{
			Thread.sleep(500);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
