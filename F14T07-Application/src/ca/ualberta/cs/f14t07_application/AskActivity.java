package ca.ualberta.cs.f14t07_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/* this is the view that is shown to the user when they would 
 * like to ask a question. It includes setting a subjected and 
 * being able to ask questions
 */
public class AskActivity extends Activity {
	  
	  //controller for this view 
	  private DataManager dataManager=new DataManager();
	  
	  @Override
	  public void onCreate(Bundle savedInstanceState) { 
	    super.onCreate(savedInstanceState); 
	    
	    setContentView(R.layout.ask_activity_screen);  
	 
	    
	    //the button the user clicks when they ask a question
	    Button ask_button=(Button) findViewById(R.id.askButton);
	    ask_button.setOnClickListener(new View.OnClickListener() {
	    	@Override
	    	public void onClick(View v){
	    		//new edit text boxes
	    		EditText newQuestionEdit=(EditText)findViewById(R.id.question);
	    		EditText newSubjectEdit=(EditText)findViewById(R.id.subject);
	    		EditText newAuthorEdit =(EditText)findViewById(R.id.name);
	    		
	    		//set strings from edit text boxes
	    		String newQuestion=newQuestionEdit.getText().toString();
	    		String newSubject=newSubjectEdit.getText().toString();
	    		String newAuthor= newAuthorEdit.getText().toString();
	    		
	    		//create a new ForumEntry
	    		ForumEntry newForumEntry=new ForumEntry(newSubject,newQuestion,newAuthor);
	    		
	    		//Pass it to the controller
	    		dataManager.addForumEntry(newForumEntry);
	    		Toast.makeText(AskActivity.this,"Asking still needs to be implemented",Toast.LENGTH_SHORT).show();

	    		//then an intent needs to open the question screen for the new forum entry
	         }
	    });
	    
	    
	    //the button the user clicks to attach a file
	    Button attach_button=(Button) findViewById(R.id.attachButton);
	    attach_button.setOnClickListener(new View.OnClickListener() {
	    	@Override
	    	public void onClick(View v){
	    		//in here we need to put file attachment shit 
	    		Toast.makeText(AskActivity.this,"Picture Attachment still needs to be added",Toast.LENGTH_SHORT).show();

	         }
	    });
	  }
	  
	  public Intent returnIntent()
	  {
		  return getIntent();
	  }
	  
}
