package ca.ualberta.cs.views;

import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.f14t07_application.R.layout;
import ca.ualberta.cs.f14t07_application.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;

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
	
	  @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	        int id = item.getItemId();
	        switch (id) {
	        case R.id.switchToHome:
	        	Intent homeIntent = new Intent(this, MainScreenActivity.class);
	        	startActivity(homeIntent);
	        	return true;
	        case R.id.switchToMyQuestions:
	        	Intent myQuestionsIntent = new Intent(this, MyQuestionsActivity.class);
	        	startActivity(myQuestionsIntent);
	        	return true;
	        default:
	        	return super.onOptionsItemSelected(item);
	        }
	    }

	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}
	  
}
