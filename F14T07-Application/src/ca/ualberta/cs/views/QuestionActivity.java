package ca.ualberta.cs.views;

import ca.ualberta.cs.controllers.ForumEntryController;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.f14t07_application.R.layout;
import ca.ualberta.cs.models.ForumEntryList;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This activity displays a ForumEntry to the user. It displays the question, its answers, and their replies.
 * It also allows the user to create an answer to the question
 * @author bbruner
 *
 */
public class QuestionActivity extends Activity implements Observer<ForumEntryList>
{
	private ForumEntryController forumEntryController;
	
	
	/**
	 * Called when this activity is first created. Instantiate class variables here.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		
		this.forumEntryController = new ForumEntryController(this);
	}

	/**
	 * Creates a menu when the ... is clicked in the view.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question, menu);
		return true;
	}

	/**
	 * Handles selection of items in the options menu.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * This function is called by the model. The view should be updated in this function.
	 */
	@Override
	public void update(ForumEntryList model)
	{
		// TODO Auto-generated method stub
		
	}
}
