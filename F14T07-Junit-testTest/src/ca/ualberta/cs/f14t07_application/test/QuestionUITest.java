package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ListView;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.models.Answer;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.Reply;
import ca.ualberta.cs.views.QuestionActivity;

public class QuestionUITest extends ActivityInstrumentationTestCase2<QuestionActivity> {
	private QuestionActivity testActivity;
	private Button testPostButton;
	private Button testMainMenuButton;
	private DataManager dm;
	private Context context;
	private ForumEntry fm;
	public QuestionUITest()
	{
		super(QuestionActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception
	{
	super.setUp();
	setActivityInitialTouchMode(true);
	testActivity = getActivity();
	
	}
	
	public void testViewReplies(){ 
		dm = new DataManager();
		fm = new ForumEntry("subject","What is life?", "Kibbles");
		dm.addForumEntry(fm);
		Reply r = new Reply ("I hate eclipse");
		fm.getQuestion().addReplies(r);
		
		ListView tv = (ListView) testActivity.findViewById(R.id.QuestionReplyList);
		for(int i = 0; i<fm.getQuestion().getReplies().size();i++){
			if (r == fm.getQuestion().getReplies().get(i)){
				assert(false);
			}
		}
	}

	public void testViewAnswer(){ 
		Answer r = new Answer ("I hate eclipse","kill me");
		ArrayList<Answer>a = new ArrayList<Answer>();
		a.add(r);
		fm.setAnswer(a);
		
		DataManager dataManager = new DataManager();
		dataManager.addForumEntry(fm);
		
		ListView tv = (ListView) testActivity.findViewById(R.id.QuestionAnswerList);
		for(int i = 0; i<fm.getAnswers().size();i++){
			if (r == fm.getAnswers().get(i)){
				assert(false);
			}
		}
	}
	
	
	// u12
	public void testReplyToQuestion(){
		
	}
	
	// u14
	public void testReplyToAnswer(){
		
	}
	
	
	
}
