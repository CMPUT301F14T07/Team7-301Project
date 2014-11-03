package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.f14t07_application.AnswerRepliesActivity;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.Entry;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.Reply;


public class AnswerRepliesTest extends ActivityInstrumentationTestCase2<AnswerRepliesActivity> {
	
	private AnswerRepliesActivity testActivity;
	private Button testButton;
	private AnswerRepliesActivity activity;
	
	public AnswerRepliesTest()
	{
		super(AnswerRepliesActivity.class);
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
		
		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.AddReply);
		testButton.performClick();
		
		//Body is the content of the reply
		EditText BodyEdit=(EditText) activity.findViewById(R.id.body);
		String Body= BodyEdit.getText().toString(); 
		
		Boolean i= false;
		
		for (Reply r : (new DataManager()).load().get(0).getQuestion().getReplies()){
			if (r.getReply() == Body){
				i=true;
			}
		}
		assert(i); 
	}
	
	public void postReplyToAnswerTest(){//NEEDS TO BE CHANGED TO MATCH THE NEW WAY WE REPLY
		
		DataManager dm = new DataManager();
		
		ForumEntry entry=new ForumEntry("subject","This is a question","AuthorName");
		entry.addAnswer(new Entry("This is an answer","AuthorName2"));
		dm.addForumEntry(entry);
		
		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.AddReply);
		testButton.performClick();
		
		//Body is the content of the reply
		EditText BodyEdit=(EditText) activity.findViewById(R.id.body);
		String Body= BodyEdit.getText().toString(); 
		
		Boolean i= false;
		
		for (Reply r : (new DataManager()).load().get(0).getAnswers().get(0).getReplies()){
			if (r.getReply() == Body){
				i=true;
			}
		}
		assert(i); 
	}
	
	public void postAnswerTest(){//NEEDS TO BE CHANGED TO MATCH THE NEW WAY WE REPLY
		
		DataManager dm = new DataManager();
		
		dm.addForumEntry((new ForumEntry("subject","This is a question","AuthorName")));

		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.AddReply);
		testButton.performClick();
		
		//Body is the content of the reply
		EditText BodyEdit=(EditText) activity.findViewById(R.id.body);
		String Body= BodyEdit.getText().toString(); 
		
		Boolean i= false;
		
		for (Entry e : (new DataManager()).get(0).load().getAnswers(){
			if (e.getPost() == Body){
				i=true;
			}
		}
		assert(i); 
	}
	
	
	public void postAnswerWithPictureTest(){//NEEDS TO BE CHANGED TO MATCH THE NEW WAY WE REPLY
		
		DataManager dm = new DataManager();
		
		dm.addForumEntry((new ForumEntry("subject","This is a question","AuthorName")));

		
		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.AddReply);
		testButton.performClick();
		
		//Body is the content of the reply
		EditText BodyEdit=(EditText) activity.findViewById(R.id.body);
		String Body= BodyEdit.getText().toString(); 
		
		Boolean i= false;
		
		for (Entry e : (new DataManager()).load().get(0).getAnswers(){
			if (e.getPost() == Body && e.getPicture() != null){
				i=true;
			}
			}
		}
		assert(i); 
	}
}
