package ca.ualberta.cs.views;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ca.ualberta.cs.f14t07_application.R;

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
