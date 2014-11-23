package ca.ualberta.cs.views;

import java.util.ArrayList;

import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.f14t07_application.R.id;
import ca.ualberta.cs.f14t07_application.R.layout;
import ca.ualberta.cs.f14t07_application.R.menu;
import ca.ualberta.cs.models.ForumEntry;
import android.app.Activity;
import android.content.Intent;//
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**The view for the search model
 * @author dlacours
 * @author pietrasi*/
public class SearchActivity extends Activity {

	public ArrayList<ForumEntry> searchHits = new ArrayList<ForumEntry>();
	
	/**the on click listeners are initialized here*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.search_activity_screen);
		
    Button view_by_button=(Button) findViewById(R.id.searchViewByButton);
    
    Button read_later_button=(Button) findViewById(R.id.searchSelectReadLater);
    
    //I (Dayna) added this. It gets the search term from the MainScreen
    EditText term = (EditText) findViewById(R.id.searchTextInput);
    Intent intent = getIntent();
    String word = null;
    word = intent.getStringExtra("TEXT");
    term.setText(word); 
    //
	    
	    view_by_button.setOnClickListener(new View.OnClickListener() {
	    	@Override
	    	public void onClick(View v){
	    		viewBy();
	         }
	    });
	    
	    read_later_button.setOnClickListener(new View.OnClickListener() {
	    	@Override
	    	public void onClick(View v){
	    		readLater();
	         }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	/**the menu*/
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
        case R.id.switchToMyQuestions:
        	Intent myQuestionsIntent = new Intent(this, MyQuestionsActivity.class);
        	startActivity(myQuestionsIntent);
        	return true;
        case R.id.help:
			String helpText = "This is where you can search through our system for a particular term. \n\n" +
					"Using the input area at the top right of the screen, you can type in a search term and press enter" +
					" to activate search." +
					"If you would like to view the posts on this screen sorted in a different manner, you can " +
					"click the View By button located directly under the title.  This will prompt you to " +
					"choose how you would like the posts to be sorted. Click the option " +
					"that works best for you.\n\n " +
					"If you would like to view a post in more detail, please click on the post. \n\n" +
					"You can navigate to other screens either by clicking the " +
					"back button, or by using the menu found by clicking the ellipsis in the corner.";	
			Intent helpIntent = new Intent(SearchActivity.this, HelpActivity.class);
			helpIntent.putExtra("HELP_TEXT", helpText);
			startActivity(helpIntent);
			return true;
        default:
        	return super.onOptionsItemSelected(item);
        }
    }
	
	public void viewBy(){
		Toast.makeText(SearchActivity.this,"Work in progress" , Toast.LENGTH_SHORT).show();
	}
	
	public void readLater(){
		Toast.makeText(SearchActivity.this,"Work in progress" , Toast.LENGTH_SHORT).show();
	}
	
	public Intent returnIntent()
	  {
		  return getIntent();
	  }
}
