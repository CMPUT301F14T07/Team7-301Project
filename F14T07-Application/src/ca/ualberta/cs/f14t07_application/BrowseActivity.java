package ca.ualberta.cs.f14t07_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class BrowseActivity extends Activity {

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
}
