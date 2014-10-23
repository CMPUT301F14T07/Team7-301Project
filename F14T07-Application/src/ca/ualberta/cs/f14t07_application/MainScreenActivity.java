package ca.ualberta.cs.f14t07_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainScreenActivity extends Activity {

	
	private BrowseController browseController = new BrowseController();
	
	public void onCreate(Bundle savedInstanceState) { 
		    super.onCreate(savedInstanceState);  
		    setContentView(R.layout.main_activity_screen);  
		 
		    
		    Button ask_button=(Button) findViewById(R.id.askButton);
		    ask_button.setOnClickListener(new View.OnClickListener() {
		    	@Override
		    	public void onClick(View v){
		    		askButton();
		         }
		    });
		    Button browse_button=(Button) findViewById(R.id.browseButton);
		    browse_button.setOnClickListener(new View.OnClickListener() {
		    	@Override
		    	public void onClick(View v){
		    		browseButton();
		         }
		    });
		    Button search_button=(Button) findViewById(R.id.searchButton);
		    search_button.setOnClickListener(new View.OnClickListener() {
		    	@Override
		    	public void onClick(View v){
		    		searchButton();
		         }
		    });
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
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
        case R.id.switchToMyQuestions:
        	Intent myQsIntent = new Intent(this, MyQuestionsActivity.class);
        	startActivity(myQsIntent);
        	return true;
        case R.id.switchToReadLater:
        	Intent readLaterIntent = new Intent(this, ReadLaterActivity.class);
        	startActivity(readLaterIntent);
        	return true;
        default:
        	return super.onOptionsItemSelected(item);
        }
    }
	
	
	public void signInButton(){
		
	}
	
	public void askButton(){
		Intent intent = new Intent(this, AskActivity.class);
		startActivity(intent);
	}
	
	public void browseButton(){
		Intent intent = new Intent(this, BrowseActivity.class);
		startActivity(intent);
	}
	
	public void searchButton(){
		EditText editableTerm = (EditText) findViewById(R.id.searchTerm);
		Editable term = (Editable) editableTerm.getText();
		Intent intent = new Intent(this, SearchActivity.class);
		intent.putExtra(android.content.Intent.EXTRA_TEXT, term);
		startActivity(intent);
		
	}
	
}
