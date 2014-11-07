package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.views.AnswerReplyActivity;
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
	
	public AnswerRepliesTest()
	{
		super(AnswerReplyActivity.class);
		activity=getActivity();
	}
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		setActivityInitialTouchMode(true);
		testActivity = getActivity();
	}
	
	public void postReplyToQuestionTest(){ //NEEDS TO BE CHANGED TO MATCH THE NEW WAY WE REPLY
		
		DataManager dm = new DataManager();
    	dm.addForumEntry((new ForumEntry("subject","This is a question","AuthorName")));
		
		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.answerReplyButton);
		testButton.performClick();
		
		//Body is the content of the reply
		EditText BodyEdit=(EditText) activity.findViewById(R.id.answerReplyText);
		String Body= BodyEdit.getText().toString(); 
		
		Boolean i= false;
		
		for (Reply r : (new DataManager()).load().getView().get(0).getQuestion().getReplies()){
			if (r.getReply() == Body){
				i=true;
			}
		}
		assert(i); 
	}
	
	public void postReplyToAnswerTest(){//NEEDS TO BE CHANGED TO MATCH THE NEW WAY WE REPLY
		
		DataManager dm = new DataManager();
		
		ForumEntry entry=new ForumEntry("subject","This is a question","AuthorName");
		ForumEntryController forumEntryController = new ForumEntryController(entry);
		
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
	
	public void postAnswerTest(){//NEEDS TO BE CHANGED TO MATCH THE NEW WAY WE REPLY
		
		DataManager dm = new DataManager();
		
		dm.addForumEntry((new ForumEntry("subject","This is a question","AuthorName")));

		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.answerReplyButton);
		testButton.performClick();
		
		//Body is the content of the reply
		EditText BodyEdit=(EditText) activity.findViewById(R.id.answerReplyText);
		String Body= BodyEdit.getText().toString(); 
		
		Boolean i= false;
		
		for (Entry e : (new DataManager()).load().getView().get(0).getAnswers()){
			if (e.getPost() == Body){
				i=true;
			}
		}
		assert(i); 
	}
	
	
	public void postAnswerWithPictureTest(){//NEEDS TO BE CHANGED TO MATCH THE NEW WAY WE REPLY
		
		DataManager dm = new DataManager();
		
		dm.addForumEntry((new ForumEntry("subject","This is a question","AuthorName")));

		
		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.answerReplyButton);
		testButton.performClick();
		
		//Body is the content of the reply
		EditText BodyEdit=(EditText) activity.findViewById(R.id.answerReplyText);
		String Body= BodyEdit.getText().toString(); 
		
		Boolean i= false;
		
		for (Entry e : (new DataManager()).load().getView().get(0).getAnswers()){
			if (e.getPost() == Body && e.getPicture() != null){
				i=true;
			}
		}
	
		assert(i); 
	}
}
