package ca.ualberta.cs.f14t07_application;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class BrowseActivity extends Activity {
	private ArrayAdapter<ForumEntry> browseListAdapter;
	private ListView browseListView;
	private List<ForumEntry> forumEntries;
	private BrowseController browseController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.browse_activity_screen);
		
	    Button view_by_button=(Button) findViewById(R.id.browseViewByButton);
	    
	    view_by_button.setOnClickListener(new View.OnClickListener() {
	    	@Override
	    	public void onClick(View v){
	    		viewBy();
	         }
	    });
	    
	    
	}
	@Override 
	public void onResume(){ 
		super.onResume();
	}
	
	@Override 
	public void onStart(){ 
		super.onStart(); 
		forumEntries = new ArrayList<ForumEntry>();
		
		browseController = new BrowseController();
		ForumEntry forumEntry = new ForumEntry("lexie","subject","question");
		
		browseListAdapter= new ArrayAdapter<ForumEntry>(BrowseActivity.this, R.layout.list_item,forumEntries);
		browseListView = (ListView) findViewById( R.id.browseListView);
		browseListView.setAdapter(browseListAdapter);
		
		forumEntries.add(forumEntry);
		browseController = new BrowseController();
		forumEntries.addAll(browseController.getAllEntries());

		
		Toast.makeText(BrowseActivity.this,forumEntry.toString(),Toast.LENGTH_SHORT).show();

		browseListAdapter.notifyDataSetChanged();
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browse, menu);
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
        case R.id.switchToReadLater:
        	Intent readLaterIntent = new Intent(this, ReadLaterActivity.class);
        	startActivity(readLaterIntent);
        	return true;
        case R.id.switchToMyQuestions:
        	Intent myQuestionsIntent = new Intent(this, MyQuestionsActivity.class);
        	startActivity(myQuestionsIntent);
        	return true;
        default:
        	return super.onOptionsItemSelected(item);
        }
    }
	
	public void viewBy(){
		Toast.makeText(BrowseActivity.this,"Work in progress" , Toast.LENGTH_SHORT).show();
	}
	
	public Intent returnIntent()
	  {
		  return getIntent();
	  }
}
