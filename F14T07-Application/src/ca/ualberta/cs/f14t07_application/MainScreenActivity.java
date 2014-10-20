package ca.ualberta.cs.f14t07_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


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
	}
	
	
	public void signInButton(){
		
	}
	
	public void askButton(){
		Intent intent = new Intent(this, AskActivity.class);
		startActivity(intent);
	}
	
	public void browseButton(){
		
	}
	
	public void searchButton(){
		
	}
	
}
