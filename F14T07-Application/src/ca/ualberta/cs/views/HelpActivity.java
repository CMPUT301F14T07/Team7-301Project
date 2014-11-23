package ca.ualberta.cs.views;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ca.ualberta.cs.f14t07_application.R;
/**
 * This is the screen that the help button in each other screens menu opens.  
 * Depending on the screen that started this activity, a different text will
 * be displayed.  This is done via an intent.  Giving the intent an extra with
 * the key "HELP_TEXT" enables this activity to get that text and set it to the
 * textview shown to the user.  This screen also has an ok button that, when clicked,
 * closes the activity and returns to the activity that called it.
 * 
 * @author dlacours*/
public class HelpActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help_activity);

		TextView help = (TextView) findViewById(R.id.helpText);
		String helpText = getIntent().getStringExtra("HELP_TEXT");
		help.setText(helpText);
		help.setMovementMethod(new ScrollingMovementMethod());
		
		Button ok_button = (Button) findViewById(R.id.okButton);
		ok_button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}
	
}
