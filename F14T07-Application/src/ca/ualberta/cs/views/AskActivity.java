package ca.ualberta.cs.views;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
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
import android.util.Base64;
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
	private String image;

	/**
	 * lays out the screen and creates onClickListeners
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ask_activity_screen);

		this.authorModel = new AuthorModel();
		this.feController = new ForumEntryController(this,this);
		
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
				GetPictureThread getpic = new GetPictureThread();
				getpic.start();

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
		String [] filePath = {MediaStore.Images.Media.DATA};
		Cursor cursor = getContentResolver().query(pictureFile, filePath, null, null, null);
		cursor.moveToFirst();
		int columnIndex = cursor.getColumnIndex(filePath[0]);
		String picturePath = cursor.getString(columnIndex);
		ImageView iv = (ImageView) findViewById(R.id.picture);
		iv.setImageBitmap(BitmapFactory.decodeFile(picturePath));
		image=picturePath;
		  
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

	class GetPictureThread extends Thread
	{
		
		@Override
		public void run()
		{
			getPicture();
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
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(homeIntent);
			return true;
			
		case R.id.help:
			ForumEntrySingleton forumEntryFocus = ForumEntrySingleton.getInstance();
			if(forumEntryFocus.getForumEntry() == null)
			{				
				String helpText = "This is where you can post a question to our system.\n\n" +
						"To ask a question, simply fill out the Author, Subject, and Question fields. " +
						"You may leave the Author field blank if you wish to remain anonymous, but we " +
						"must insist that you fill in the Subject and Question fields. Please include as much " +
						"relevent information as possible in your question so people are more able to comprehend " +
						"and answer it. \n\n" +
						"If you would like to attach an image to your question, you can click the Paperclip" +
						" button to the bottom right of the Question field.  This will prompt you to choose" +
						" whether to get the picture from your phone's album, or take a new picture.  Choose " +
						"whichever best suits you.\n\n" +
						"When you feel your question is complete, press the Ask button at the bottom of the screen. \n\n" +
						"You can navigate to other screens either by clicking the " +
						"back button, or by using the menu found by clicking the ellipsis in the corner.";	
				Intent helpIntent = new Intent(AskActivity.this, HelpActivity.class);
				helpIntent.putExtra("HELP_TEXT", helpText);
				startActivity(helpIntent);
				return true;
			}
			else
			{
				String helpText = "This is where you can answer a question.\n\n" +
						"To post your answer, simply fill out the Author and Answer fields. " +
						"You may leave the Author field blank if you wish to remain anonymous, but we " +
						"must insist that you fill in the Answer field. \n\n" +
						"If you would like to attach an image to your answer, you can click the Paperclip" +
						" button to the bottom right of the Answer field.  This will prompt you to choose" +
						" whether to get the picture from your phone's album, or take a new picture.  Choose " +
						"whichever best suits you.\n\n" +
						"When you feel your answer is complete, press the Answer button at the bottom of the screen. \n\n" +
						"You can navigate to other screens either by clicking the " +
						"back button, or by using the menu found by clicking the ellipsis in the corner.";
				Intent helpIntent = new Intent(AskActivity.this, HelpActivity.class);
				helpIntent.putExtra("HELP_TEXT", helpText);
				startActivity(helpIntent);
				return true;
			}

			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}