package ca.ualberta.cs.views;

import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.f14t07_application.R.id;
import ca.ualberta.cs.f14t07_application.R.layout;
import ca.ualberta.cs.f14t07_application.R.menu;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ReadLaterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readlater_activity_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_later, menu);
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
		case R.id.switchToFavorites:
			Intent favoritesIntent = new Intent(this, FavouriteActivity.class);
			startActivity(favoritesIntent);
			return true;
        default:
        	return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);	
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.readlatercontextmenu, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	switch(item.getItemId()) {
    	case R.id.readLaterDelete:
    		// run edit code here
    		return true;
    	case R.id.readLaterCancel:
    		// run delete code here
    		return true;
    	default:
    		return super.onContextItemSelected(item);
    	}
    }

	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}
}
