package ca.ualberta.cs.views;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.cs.controllers.BrowseController;
import ca.ualberta.cs.controllers.ForumEntryController;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.intent_singletons.BrowseRequestSingleton;
import ca.ualberta.cs.intent_singletons.ForumEntrySingleton;
import ca.ualberta.cs.models.Answer;
import ca.ualberta.cs.models.AuthorModel;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.models.Question;

/**
 * This view allows the user to enter a new question or enter and answer to a question. The 
 * user does not need to differentiate between this. This view will differentiate between asking
 * a question or answering a question. Then, it will present the user an appropriate interface for doing
 * that.
 * 
 * @author lexie
 */
public class AskActivity extends Activity implements Observer<ForumEntryList>
{
	/*
	 * TODO: Do not let user make a blank question or answer
	 * TODO: Home screen button
	 * TODO: Attach a picture
	 */
	public Intent intent;
	public Intent intent2;
	public Context ctx;

	private AuthorModel authorModel;
	private ForumEntryController feController;
	
	private BrowseController browseController;
	private ForumEntry forumEntry;
	
	private static final String SUBMIT_ANSWER = "Answer";
	private static final String SUBMIT_QUESTION = "Ask";
	private static final String TEXT_HINT_ANSWER = "Your Answer";
	private static final String TEXT_HINT_QUESTION = "Your Question";
	private static final String TITLE_ANSWER = "Answer a Question";
	private static final String TITLE_QUESTION = "Ask a Question";
	private Uri pictureFile;
	public static final int RESULT_GALLERY = 0;
	private Bitmap bitmap = null;
	private byte[] image;

	/**
	 * lays out the screen and creates onClickListeners
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ask_activity_screen);

		this.authorModel = new AuthorModel();
		this.feController = new ForumEntryController(this);
		
		this.browseController = new BrowseController(this);

		/* 
		 * Submit Button's on click listener. 
		 */
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
				 * is focusing on (look about 9 lines below).
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

				if(!isAlphaNumeric(newAuthor)){
					newAuthor = "Anonymous";
					
				}
				
				/* 
				 * If the forumEntrySingleton is focusing on nothing (ie a null ForumEntry) then we are trying to create a
				 * new question. Do that, then change the focus onto the newly created ForumEntry.
				 */
				if(forumEntryFocus.getForumEntry() == null)
				{	
					if(isAlphaNumeric(newSubject) && isAlphaNumeric(newEntry)){
					/*
					 * Create an instance of the new ForumEntry then set the ForumEntrySingletons focus on it.
					 */
					ForumEntry newForumEntry = new ForumEntry(newSubject, newEntry, newAuthor, image);
					forumEntryFocus.setForumEntry(newForumEntry);
					/*
					 * Invoke the AddThread to add this new ForumEntry to the remote server by
					 * calling the controller
					 */
					Thread thread = new AddQuestionThread(newForumEntry);
					thread.start();
					resetEditText(newEntryEdit, newSubjectEdit, newAuthorEdit);
					startQuestionScreen();
					}
					else{
						Toast.makeText(AskActivity.this,
								"Invalid Question or Subject",
								Toast.LENGTH_SHORT).show();
					}
				}
				/*
				 * The ForumEntrySingleton is focusing on a ForumEntry. This means we are trying to add an answer to the focused ForumEntry.
				 */
				else
				{
					Answer answer = new Answer(newEntry, newAuthor);
					/*
					 * Invoke the AddThread to add this answer to the ForumEntry in the remote server
					 * by calling controller
					 */
					Thread thread = new AddAnswerThread(answer);
					thread.start();
					startQuestionScreen();
				
				}

				resetEditText(newEntryEdit, newSubjectEdit, newAuthorEdit);


			}

			private void resetEditText(EditText newEntryEdit,
					EditText newSubjectEdit, EditText newAuthorEdit) {
				/*
				 * Reset the EditText widgets to be blank for the next time the activity starts
				 */
				newEntryEdit.setText("");
				newSubjectEdit.setText("");
				newAuthorEdit.setText("");
			}
		});

		// the button the user clicks to attach a file
		Button attach_button = (Button) findViewById(R.id.attachButton);
		attach_button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				getPicture();

			}
		});
	}
	public void startQuestionScreen(){
		/*
		 * Start the question activity to view the new question or appended answer.
		 */
		intent = new Intent(AskActivity.this, QuestionActivity.class);
		intent2 = intent;
		
		/*
		 * This destroys the activity. Basically, this means after a user asks a question or answers one,
		 * they cannot come back to this activity. The back button will not bring them here.
		 */
		finish();
		
		startActivity(intent);
	}
	public Boolean isAlphaNumeric(String s){
		String checkS = s;
		
	    if(checkS.trim().length() == 0){
	    	return false;
	    }
	    return true;
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		ctx = this.getApplicationContext();
		
		/*
		 * Tell the controller what ForumEntry the singleton is focusing on.
		 */
		this.feController.setView(ForumEntrySingleton.getInstance().getForumEntry());
		
		/*
		 * Set the name of the author in the view to be the sessionAuthor in the authorModel 
		 */
		EditText newAuthorEdit = (EditText) findViewById(R.id.name);
		newAuthorEdit.setText(this.authorModel.getSessionAuthor());
		
		/*
		 * If the focus of the ForumEntrySingleton is not null then we are answering a
		 * question, not creating one. Therefore, we need to remove the subject text element
		 * and change the dialog of the submit button.
		 */
		EditText newSubjectEdit = (EditText) findViewById(R.id.subject);
		EditText textBody = (EditText) findViewById(R.id.question);
		TextView titleText = (TextView) findViewById(R.id.askTitle);
		Button submitButton = (Button) findViewById(R.id.askButton);
		if(ForumEntrySingleton.getInstance().getForumEntry() != null)
		{
			newSubjectEdit.setVisibility(EditText.INVISIBLE);
			submitButton.setText(AskActivity.SUBMIT_ANSWER);
			textBody.setHint(AskActivity.TEXT_HINT_ANSWER);
			titleText.setText(AskActivity.TITLE_ANSWER);
		}
		/*
		 * Otherwise, we are creating a question and we do want the subject text element visible.
		 */
		else
		{
			newSubjectEdit.setVisibility(EditText.VISIBLE);
			submitButton.setText(AskActivity.SUBMIT_QUESTION);
			textBody.setHint(AskActivity.TEXT_HINT_QUESTION);
			titleText.setText(AskActivity.TITLE_QUESTION);
		}
	}
	
	//http://stackoverflow.com/questions/16928727/open-gallery-app-from-android-intent
	public void getPicture(){

		Intent galleryIntent = new Intent(
		                    Intent.ACTION_PICK,
		                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(galleryIntent , RESULT_GALLERY );
		
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);

	   if(requestCode == RESULT_GALLERY){
	        if (null != data) {
	            pictureFile = data.getData();
	            decodeUri();
	        }
	    }
	}
	
	//http://stackoverflow.com/questions/21195899/bitmapfactory-unable-to-decode-stream
	public void decodeUri(){
	        byte[] data = null;
	        try {
	            ContentResolver cr = getBaseContext().getContentResolver();
	            InputStream inputStream = getContentResolver().openInputStream(pictureFile);
	            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
	            ByteArrayOutputStream out = new ByteArrayOutputStream();
	            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
	        
	            data = out.toByteArray();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        image= data;
	}
	class AddQuestionThread extends Thread
	{
		private ForumEntry forumEntry;

		public AddQuestionThread(ForumEntry forumEntry)
		{
			this.forumEntry = forumEntry;
		}

		@Override
		public void run()
		{
			feController.addNewQuestion(this.forumEntry);
			feController.saveMyAuthoredCopy();
		}
	}
	class AddAnswerThread extends Thread
	{
		private Answer answer;
		
		public AddAnswerThread(Answer answer)
		{
			this.answer = answer;
		}
		
		@Override
		public void run()
		{
			feController.addAnswer(this.answer);
		}
	}


	@Override
	public void update(ForumEntryList model)
	{
		// TODO Auto-generated method stub

	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ask, menu);
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
	
}