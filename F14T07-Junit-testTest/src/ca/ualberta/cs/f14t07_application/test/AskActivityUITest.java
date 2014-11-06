package ca.ualberta.cs.f14t07_application.test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.views.AskActivity;
import ca.ualberta.cs.views.QuestionActivity;

public class AskActivityUITest extends
		ActivityInstrumentationTestCase2<AskActivity> {

	public AskActivityUITest() {
		super(AskActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	public void AskButtonTest() {
		AskActivity a = getActivity();
		Button askButton = (Button) a.findViewById(ca.ualberta.cs.f14t07_application.R.id.askButton);
		EditText question = (EditText) a.findViewById(ca.ualberta.cs.f14t07_application.R.id.question);
		EditText subject = (EditText) a.findViewById(ca.ualberta.cs.f14t07_application.R.id.subject);
		EditText name = (EditText) a.findViewById(ca.ualberta.cs.f14t07_application.R.id.name);

		name.setText("Person");
		subject.setText("Subject");
		question.setText("the question");
		
		askButton.performClick();
		Intent newIntent = a.intent2;
		Intent askIntent = new Intent(a, QuestionActivity.class);
		assertTrue(newIntent.filterEquals(askIntent));
	}

}
