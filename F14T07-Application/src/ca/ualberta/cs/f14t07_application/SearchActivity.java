package ca.ualberta.cs.f14t07_application;

import android.app.Activity;
import android.content.Intent;//
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends Activity {

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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void viewBy(){
		Toast.makeText(SearchActivity.this,"Work in progress" , Toast.LENGTH_SHORT).show();
	}
	
	public void readLater(){
		Toast.makeText(SearchActivity.this,"Work in progress" , Toast.LENGTH_SHORT).show();
	}
}
