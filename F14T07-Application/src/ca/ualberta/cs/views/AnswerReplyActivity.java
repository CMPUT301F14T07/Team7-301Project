package ca.ualberta.cs.views;

import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.f14t07_application.R.id;
import ca.ualberta.cs.f14t07_application.R.layout;
import ca.ualberta.cs.f14t07_application.R.menu;
import ca.ualberta.cs.intent_singletons.BrowseRequestSingleton;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AnswerReplyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answer_reply_activity_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.answer_reply, menu);
		return true;
	}
	
	/**
	 * Allows user to navigate through some screens using the menu bar
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		
		int id = item.getItemId();
		Intent intent;
		intent = new Intent(this, BrowseActivity.class);
		BrowseRequestSingleton.getInstance().setSearchToken(BrowseRequestSingleton.SEARCH_EVERYTHING);
		switch (id)
		{
		
		case R.id.switchToMyQuestions:
			/*
			 * Set the search and view tokens in the BrowseRequestSingleton, this way, the browse activity
			 * knows what to search for and what view to present when starting up.
			 */
			BrowseRequestSingleton.getInstance().setViewToken(BrowseRequestSingleton.MY_AUTHORED_VIEW);
			startActivity(intent);
			return true;
			
		case R.id.switchToReadLaters:
			/*
			 * Set the search and view tokens in the BrowseRequestSingleton, this way, the browse activity
			 * knows what to search for and what view to present when starting up.
			 */
			BrowseRequestSingleton.getInstance().setViewToken(BrowseRequestSingleton.READ_LATER_VIEW);
			startActivity(intent);
			return true;
			
		case R.id.switchToFavourites:
			/*
			 * Set the search and view tokens in the BrowseRequestSingleton, this way, the browse activity
			 * knows what to search for and what view to present when starting up.
			 */
			BrowseRequestSingleton.getInstance().setViewToken(BrowseRequestSingleton.FAVOURITES_VIEW);
			startActivity(intent);
			return true;
			
		case R.id.switchToHomeScreen:
			Intent homeIntent = new Intent(this, MainScreenActivity.class);
			startActivity(homeIntent);
			return true;
			
		default:
			
			return super.onOptionsItemSelected(item);
		}
	}
}
