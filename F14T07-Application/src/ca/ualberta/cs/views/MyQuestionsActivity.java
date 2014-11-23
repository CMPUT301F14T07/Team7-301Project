package ca.ualberta.cs.views;


import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.f14t07_application.R.id;
import ca.ualberta.cs.f14t07_application.R.layout;
import ca.ualberta.cs.f14t07_application.R.menu;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
/**
 * The view for the users questions. Should be used when viewing the MyQuestions screen
 * @author rtwong*/
public class MyQuestionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myquestions_activity_screen);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_questions, menu);
        return true;
    }

    // option menu to move to different activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
        case R.id.switchToHome:
        	Intent homeIntent = new Intent(this, MainScreenActivity.class);
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        	startActivity(homeIntent);
        	return true;
        case R.id.switchToReadLater:
        	Intent readLaterIntent = new Intent(this, ReadLaterActivity.class);
        	startActivity(readLaterIntent);
        	return true;
		case R.id.switchToFavourites:
			Intent favoritesIntent = new Intent(this, FavouriteActivity.class);
			startActivity(favoritesIntent);
			return true;
		case R.id.help:
			Toast.makeText(MyQuestionsActivity.this, "got here", Toast.LENGTH_SHORT).show();
			String helpText = "This is the home screen. \n\n Here you can sign in using the Sign In button on the bottom right of the screen. " +
					"\n\n You can also set your location using GPS or by typing in a location by pressing the Set Location Button. \n\n" +
					"Using the three buttons in the centre of the screen, you can ask a question, browse through already posted questions, and search through our questions. \n\n" +
					"To search, simply type a search term into the space provided and press the Search button. \n" +
					"\n From this screen you can also access your previously authored questions, your favourited questions, and your saved questions. " +
					"You can do this by pressing the ellipsis button in the corner and clicking on the desired menu option. \n\n" +
					"As you navigate through our app, if you find yourself in need of more information, please click the help button located in the menu of each screen.\n\n" +
					"Thank you, and enjoy your time on our app :)";
			Intent helpIntent = new Intent(MyQuestionsActivity.this, HelpActivity.class);
			helpIntent.putExtra("HELP_TEXT", helpText);
			startActivity(helpIntent);

        default:
        	return super.onOptionsItemSelected(item);
        }
    }


	
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);	
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.myquestionscontextmenu, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	switch(item.getItemId()) {
    	case R.id.myQuestionsEditCM:
    		// run edit code here
    		return true;
    	case R.id.myQuestionsDeleteCM:
    		// run delete code here
    		return true;
    	default:
    		return super.onContextItemSelected(item);
    	}
    }
	
	
}
