package ca.ualberta.cs.f14t07_application;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.f14t07_application.R;


/* this is the view that is shown to the user when they would 
 * like to ask a question. It includes setting a subjected and 
 * being able to ask questions
 */
public class AskActivity extends Activity {
	  @Override  
	  public void onCreate(Bundle savedInstanceState) { 
	    super.onCreate(savedInstanceState);  
	    setContentView(R.layout.ask_activity_screen);  
	 
	    
	    //the button the user clicks when they ask a question
	    Button ask_button=(Button) findViewById(R.id.askButton);
	    ask_button.setOnClickListener(new View.OnClickListener() {
	    	@Override
	    	public void onClick(View v){
	    		
	         }
	    });
	  }
	  
}
