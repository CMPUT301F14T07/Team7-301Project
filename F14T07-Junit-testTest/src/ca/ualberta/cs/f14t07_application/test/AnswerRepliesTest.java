package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.views.AnswerReplyActivity;
import ca.ualberta.cs.views.Observer;
import ca.ualberta.cs.controllers.ForumEntryController;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.models.Answer;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.Entry;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.Reply;


public class AnswerRepliesTest extends ActivityInstrumentationTestCase2<AnswerReplyActivity> {
	
	private AnswerReplyActivity testActivity;
	private Button testButton;
	private AnswerReplyActivity activity;
	private Context ctx;
	
	public AnswerRepliesTest()
	{
		super(AnswerReplyActivity.class);

	}
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		activity=getActivity();
		setActivityInitialTouchMode(true);
		testActivity = getActivity();
		ctx = testActivity.getApplicationContext();
	}
	
	public void testpostReplyToQuestion(){//NEEDS TO BE CHANGED TO MATCH THE NEW WAY WE REPLY
		activity.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
		DataManager dm = new DataManager();
    	dm.addForumEntry((new ForumEntry("subject","This is a question","AuthorName")));
		
		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.answerReplyButton);
		testButton.performClick();
		
		//Body is the content of the reply
		EditText BodyEdit=(EditText) activity.findViewById(R.id.answerReplyText);
		String Body= BodyEdit.getText().toString(); 
		
		Boolean i= false;
		
		for (Reply r : (new DataManager()).loadLocallySaved().getView().get(0).getQuestion().getReplies()){
			if (r != null){
				if (r.getReply() == Body){
					i=true;
				}
			}
		}
		assert(i); 
			}
		});
		
	}
	
	public void testpostReplyToAnswer(){//NEEDS TO BE CHANGED TO MATCH THE NEW WAY WE REPLY
		activity.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
		DataManager dm = new DataManager();
		
		ForumEntry entry=new ForumEntry("subject","This is a question","AuthorName");
		ForumEntryController forumEntryController = new ForumEntryController(this);
		
		Answer answer = new Answer("This is an answer","AuthorName2");
		forumEntryController.addAnswer(answer);
		
		dm.addForumEntry(entry);
		
		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.answerReplyButton);
		testButton.performClick();
		
		//Body is the content of the reply
		EditText BodyEdit=(EditText) activity.findViewById(R.id.answerReplyText);
		String Body= BodyEdit.getText().toString(); 
		
		Boolean i= false;
		
		for (Reply r : (new DataManager()).load().getView().get(0).getAnswers().get(0).getReplies()){
			if (r.getReply() == Body){
				i=true;
			}
		}
		assert(i); 
			}
			
			});
	}
	
	public void testpostAnswer(){//NEEDS TO BE CHANGED TO MATCH THE NEW WAY WE REPLY
		activity.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
		DataManager dm = new DataManager();
		
		dm.addForumEntry((new ForumEntry("subject","This is a question","AuthorName")));

		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.answerReplyButton);
		testButton.performClick();
		
		//Body is the content of the reply
		EditText BodyEdit=(EditText) activity.findViewById(R.id.answerReplyText);
		String Body= BodyEdit.getText().toString(); 
		
		Boolean i= false;
		
		for (Answer a : (new DataManager()).loadLocallySaved().getView().get(0).getAnswers()){
			if (a != null){
				if (a.getPost() == Body){
					i=true;
				}
			}
		}
		assert(i); 
		(new DataManager()).saveLocally(null);
			}
		});
		
	}
	
	
	public void testpostAnswerWithPicture(){//NEEDS TO BE CHANGED TO MATCH THE NEW WAY WE REPLY
		activity.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
		DataManager dm = new DataManager();
		
		dm.addForumEntry((new ForumEntry("subject","This is a question","AuthorName")));

		
		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.answerReplyButton);
		testButton.performClick();
		
		//Body is the content of the reply
		EditText BodyEdit=(EditText) activity.findViewById(R.id.answerReplyText);
		String Body= BodyEdit.getText().toString(); 
		
		Boolean i= false;
		
		for (Answer a : (new DataManager()).loadLocallySaved().getView().get(0).getAnswers()){
			if (a != null){
				if (a.getPost() == Body && a.getPicture() != null){
					i=true;
				}
			}
		}
	
		assert(i); 
		}
		});
	}
}