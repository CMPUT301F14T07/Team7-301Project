package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import ca.ualberta.cs.f14t07_application.AskActivity;
import ca.ualberta.cs.f14t07_application.DataManager;
import ca.ualberta.cs.f14t07_application.ForumEntry;
import ca.ualberta.cs.f14t07_application.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import junit.framework.TestCase;

public class AnswerReplyTest extends ActivityInstrumentationTestCase2<AnswerReplyActivity> {
	
	private AnswerReplyActivity testActivity;
	private Button testButton;
	private AnswerReplyActivity activity;
	
	public AnswerReplyTest()
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
	
	public void postReplyToQuestionTest(){
		
		ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
		posts.add(new ForumEntry("This is a question","AuthorName"));
		(new DataManager()).save(posts);
		
		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.addAnswerReplyButton);
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
	
	public void postReplyToAnswerTest(){
		
		ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
		
		ForumEntry entry=new ForumEntry("This is a question","AuthorName");
		entry.addAnswer(new Entry("This is an answer","AuthorName2"));
		posts.add(entry);
		(new DataManager()).save(posts);
		
		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.addAnswerReplyButton);
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
	
	public void postAnswerTest(){
		
		ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
		posts.add(new ForumEntry("This is a question","AuthorName"));
		(new DataManager()).save(posts);
		
		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.addAnswerReplyButton);
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
	
	
	public void postAnswerWithPictureTest(){
		
		ArrayList<ForumEntry> posts = new ArrayList<ForumEntry>();
		posts.add(new ForumEntry("This is a question","AuthorName"));
		(new DataManager()).save(posts);
		
		testButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.addAnswerReplyButton);
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
