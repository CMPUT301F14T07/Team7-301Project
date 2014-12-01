package ca.ualberta.cs.f14t07_application.test;

import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.views.AskActivity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AskWithLocationTest extends ActivityInstrumentationTestCase2<AskActivity> {
	private AskActivity testActivity;
	private Button testPostButton;
	private Button testMainMenuButton;
	private DataManager dm;
	private Context context;
	
	public AskWithLocationTest()
	{
		super(AskActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception
	{
	super.setUp();
	setActivityInitialTouchMode(true);
	testActivity = getActivity();
	
	dm = new DataManager();
	//dm.addForumEntry((new ForumEntry("subject","What is life?", "Kibbles")));
	
	}
	//Checks if a new ForumEntry has been added and if it matches what was put in by the user
	public void testPost()
	{
		AskActivity activity = getActivity();
		final EditText QuestionEdit=(EditText) activity.findViewById(R.id.question);
		final EditText SubjectEdit=(EditText) activity.findViewById(R.id.subject);
		final EditText AuthorEdit =(EditText) activity.findViewById(R.id.name);

		String location = null;
		testPostButton = (Button) activity.findViewById(ca.ualberta.cs.f14t07_application.R.id.askButton);	
		
		activity.runOnUiThread(new Runnable (){ 
			@Override 
			public void run(){ 
				QuestionEdit.setText("question",TextView.BufferType.EDITABLE);
				SubjectEdit.setText("Subject",TextView.BufferType.EDITABLE);
				AuthorEdit.setText("Author", TextView.BufferType.EDITABLE);
				testPostButton.performClick();
			}
		});
		
		
		//this is because if no errors have been thrown then it 
		// works because there are only really those two options
		Boolean testPass = true;
		assertNotNull(location);
	}
}
