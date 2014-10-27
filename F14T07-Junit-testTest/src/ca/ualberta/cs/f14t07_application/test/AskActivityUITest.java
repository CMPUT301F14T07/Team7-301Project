package ca.ualberta.cs.f14t07_application.test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.f14t07_application.AskActivity;

public class AskActivityUITest extends
		ActivityInstrumentationTestCase2<AskActivity> {

	public AskActivityUITest() {
		super(AskActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	public void AskButtonTest() {
		Button askButton = (Button) AskActivity.findViewById(ca.ualberta.cs.f14t07_application.title_activity_ask_screen.R.id.askButton);
		EditText question = (EditText) AskActivity.findViewById(ca.ualberta.cs.f14t07_application.title_activity_ask_screen.R.id.question);
		EditText subject = (EditText) AskActivity.findViewById(ca.ualberta.cs.f14t07_application.title_activity_ask_screen.R.id.subject);
		EditText name = (EditText) AskActivity.findViewById(ca.ualberta.cs.f14t07_application.title_activity_ask_screen.R.id.name);

		name.setText("Person");
		subject.setText("Subject");
		question.setText("the question");
		
		askButton.performClick();
		Intent newIntent = getStartedActivityIntent();
		Intent AskIntent = new Intent(AskActivityUITest.this, QuestionActivity.class);
		
		assertTrue(newIntent.filterEquals(AskIntent));
	}

}
