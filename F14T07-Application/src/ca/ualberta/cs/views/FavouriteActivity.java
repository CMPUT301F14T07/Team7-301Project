package ca.ualberta.cs.views;

import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.f14t07_application.R.layout;
import ca.ualberta.cs.f14t07_application.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FavouriteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favourite_activity_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favourite, menu);
		return true;
	}

}
