package ca.ualberta.cs.views;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.cs.controllers.BrowseController;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.intent_singletons.BrowseRequestSingleton;
import ca.ualberta.cs.intent_singletons.ForumEntrySingleton;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;

/**
 * The Browse view implements the observer class
 * */
public class BrowseActivity extends Activity implements Observer<ForumEntryList>
{
	/*
	 * TODO: Display a questions upvote count beside it
	 * TODO: Can select multiple questions and then have these saved to favourites / read later / w/e
	 * TODO: A button to click when the search field EditText is filled in with a term to search for.
	 */
	private ArrayAdapter<ForumEntry> browseListAdapter;
	private ListView browseListView;
	public ArrayList<ForumEntry> forumEntries;
	private BrowseController browseController;
	private EditText term;
	private BrowseRequestSingleton brs;
	private String searchTerm;
	private DataManager dm;

	private Runnable doUpdateGUIList = new Runnable()
	{
		public void run()
		{
			browseController.useOnLineView();
		}
	};

	/**
	 * lays out the screen, initializes onClickListeners, and initializes class variables.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.browse_activity_screen);

		forumEntries = new ArrayList<ForumEntry>();
		dm = new DataManager();
		browseController = new BrowseController(this);
		
		browseListAdapter = new ArrayAdapter<ForumEntry>(BrowseActivity.this, R.layout.list_item, forumEntries);
		browseListView = (ListView) findViewById(R.id.browseListView);
		browseListView.setAdapter(browseListAdapter);
		term = (EditText) findViewById(R.id.searchTextInput);

		/*
		 * Create an on click listener for the "view by" button.
		 */
		Button view_by_button = (Button) findViewById(R.id.browseViewByButton);
		view_by_button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				viewBy();
			}
		});
		
		/*
		 * Create an on click listener for items in the list view. Currently, this on click listener
		 * is for single selection only.
		 */
		this.browseListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		this.browseListView.setOnItemClickListener(new ListView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				/*
				 * Get the instance of the ForumEntry which was selected and set that as the focus in the
				 * ForumEntrySingleton. Then, start the QuestionActivity.
				 */
				ForumEntry fe = forumEntries.get(arg2);
				ForumEntrySingleton.getInstance().setForumEntry(fe);
				startQuestionActivity();
			}
		});
		
		/* listener so that when enter is clicked it will search */
		
		term.setOnKeyListener(new OnKeyListener() {
		    public boolean onKey(View v, int keyCode, KeyEvent event) {
		        // If the event is a key-down event on the "enter" button
		        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
		            (keyCode == KeyEvent.KEYCODE_ENTER)) {
		          // Perform action on key press
		        	searchTerm = term.getText().toString();
		        	callSearchThread();
		          return true;
		        }
		        return false;
		    }
		});
		

	}

	@Override
	public void onResume()
	{
		super.onResume();
	}

	@Override
	public void onStart()
	{
		super.onStart();
		registerForContextMenu(browseListView);
		brs = BrowseRequestSingleton.getInstance();
		
		/*
		 * Set the text displayed for the "viewing" type to be the view token in the BrowseRequestSingleton
		 */
		TextView viewType = (TextView) findViewById(R.id.browseTextView);
		viewType.setText(brs.getViewToken());

		/*
		 * Switch the viewToken in the BrowseRequestSingleton (note, switch statement
		 * can't be used for strings). Then, call the proper methods so that this
		 * activity displays the ForumEntries which correspond to that  view.
		 */

		if(brs.getViewToken().equals(BrowseRequestSingleton.ON_LINE_VIEW))
		{
			/*
			 * Use search term in BrowseRequestSingleton and start a searchThread to
			 * probe the remote server for ForumEntries
			 */
			searchTerm=brs.getSearchToken();

			callSearchThread();
		}

		else if(brs.getViewToken().equals(BrowseRequestSingleton.FAVOURITES_VIEW))
		{
			/*
			 * Invoke the controller to use cached ForumEntries marked as favourites.
			 */
			this.browseController.useFavouritesView();
			term.setVisibility(EditText.INVISIBLE);
		}
		else if(brs.getViewToken().equals(BrowseRequestSingleton.MY_AUTHORED_VIEW))
		{
			/*
			 * Invoke the controller to use cached ForumEntries authored by the user.
			 */
			this.browseController.useMyAuthoredView();
			term.setVisibility(EditText.INVISIBLE);
		}
		else if(brs.getViewToken().equals(BrowseRequestSingleton.READ_LATER_VIEW))
		{
			/*
			 * Invoke the controller to use cached ForumEntries marked as read later.
			 */
			this.browseController.useReadLaterView();
			term.setVisibility(EditText.INVISIBLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browse, menu);
		return true;
	}

	/**
	 * Initializes the menu
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		/*
		 * Handle action bar item clicks here. The action bar will
		 * automatically handle clicks on the Home/Up button, so long
		 * as you specify a parent activity in AndroidManifest.xml.
		 */
		int id = item.getItemId();
		TextView viewType = (TextView) findViewById(R.id.browseTextView);
		EditText term = (EditText) findViewById(R.id.searchTextInput);
		BrowseRequestSingleton brs = BrowseRequestSingleton.getInstance();
		switch (id)
		{
		case R.id.switchToHome:
			Intent homeIntent = new Intent(this, MainScreenActivity.class);
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(homeIntent);
			return true;
		case R.id.switchToReadLater:
			this.browseController.useReadLaterView();
			brs.setViewToken(BrowseRequestSingleton.READ_LATER_VIEW);
			viewType.setText(brs.getViewToken());
			term.setVisibility(EditText.INVISIBLE);
			return true;
		case R.id.switchToMyQuestions:
			this.browseController.useMyAuthoredView();
			brs.setViewToken(BrowseRequestSingleton.MY_AUTHORED_VIEW);
			viewType.setText(brs.getViewToken());
			term.setVisibility(EditText.INVISIBLE);
			return true;
		case R.id.switchToFavourites:
			this.browseController.useFavouritesView();
			brs.setViewToken(BrowseRequestSingleton.FAVOURITES_VIEW);
			viewType.setText(brs.getViewToken());
			term.setVisibility(EditText.INVISIBLE);
			return true;	
		case R.id.help:
			if(brs.getViewToken().equals(BrowseRequestSingleton.ON_LINE_VIEW))
			{
				String helpText = "This section of the app contains all of the posts in our system. \n\n" +
						"Because our system is based online, you can only view these posts when you are connected" +
						" to the internet.  If you would like to save a post to be read while offline, click on the " +
						"question and, on the newly opened screen, click the Save button.  It will prompt you to choose " +
						"whether to save the questions to your favourites or your saved questions. Choose whichever suits you.\n\n" +
						"If you would like to view the posts on this screen sorted in a different manner, you can " +
						"click the View By button located directly under the title.  This will prompt you to " +
						"choose how you would like the posts to be sorted. Click the option " +
						"that works best for you.\n\n " +
						"If you would like to view a post in more detail, please click on the post. \n\n" +
						"You can navigate to other screens either by clicking the " +
						"back button, or by using the menu found by clicking the ellipsis in the corner.";
				Intent helpIntent = new Intent(BrowseActivity.this, HelpActivity.class);
				helpIntent.putExtra("HELP_TEXT", helpText);
				startActivity(helpIntent);
			}
			else if(brs.getViewToken().equals(BrowseRequestSingleton.FAVOURITES_VIEW))
			{
				String helpText = "This section of the app contains all of your favourited questions. \n\n" +
						"Here you can browse through the questions and their answers. \n\n" +
						"If you would like to view the questions sorted in a different manner, you can " +
						"click the View By button located directly under the title.  This will prompt you to " +
						"choose how you would like your favourited questions to be sorted. Click the option " +
						"that works best for you.\n\n " +
						"If you would like to view a post in more detail, please click on the post. \n\n" +
						"You can navigate to other screens either by clicking the " +
						"back button, or by using the menu found by clicking the ellipsis in the corner.";
				Intent helpIntent = new Intent(BrowseActivity.this, HelpActivity.class);
				helpIntent.putExtra("HELP_TEXT", helpText);
				startActivity(helpIntent);
			}
			else if(brs.getViewToken().equals(BrowseRequestSingleton.MY_AUTHORED_VIEW))
			{
				String helpText = "This section of the app contains all of your authored questions. \n\n" +
						"Here you can browse through your questions and their answers. \n\n" +
						"If you would like to view the questions sorted in a different manner, you can " +
						"click the View By button located directly under the title.  This will prompt you to " +
						"choose how you would like your posts to be sorted. Click the option " +
						"that works best for you.\n\n " +
						"If you would like to view a post in more detail, please click on the post. \n\n" +
						"You can navigate to other screens either by clicking the " +
						"back button, or by using the menu found by clicking the ellipsis in the corner.";
				Intent helpIntent = new Intent(BrowseActivity.this, HelpActivity.class);
				helpIntent.putExtra("HELP_TEXT", helpText);
				startActivity(helpIntent);
			}
			else if(brs.getViewToken().equals(BrowseRequestSingleton.READ_LATER_VIEW))
			{
				String helpText = "This section of the app contains all of your saved questions. \n\n" +
						"Here you can browse through the questions and their answers. \n\n" +
						"If you would like to view the questions sorted in a different manner, you can " +
						"click the View By button located directly under the title.  This will prompt you to " +
						"choose how you would like your saved questions to be sorted. Click the option " +
						"that works best for you.\n\n" +
						"If you would like to view a post in more detail, please click on the post. \n\n" +
						"You can navigate to other screens either by clicking the " +
						"back button, or by using the menu found by clicking the ellipsis in the corner.";
				Intent helpIntent = new Intent(BrowseActivity.this, HelpActivity.class);
				helpIntent.putExtra("HELP_TEXT", helpText);
				startActivity(helpIntent);
			}

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	public void callSearchThread(){
		SearchThread thread = new SearchThread(searchTerm);
		thread.start();
		term.setText(searchTerm);
		term.setVisibility(EditText.VISIBLE);
	}
	public void viewBy()
	{
		final CharSequence[] sortTypes = {"Sort by time", "Sort by rating", "Sort by picutre"};
		AlertDialog.Builder alert = new AlertDialog.Builder(BrowseActivity.this);
		
		alert.setTitle("What do you want to sort by?"); 
		
		alert.setItems(sortTypes, new DialogInterface.OnClickListener() {
			 @Override
			 public void onClick(DialogInterface dialog, int item) {
				if (item == 0){
					Toast.makeText(BrowseActivity.this, sortTypes[item], Toast.LENGTH_SHORT).show();
					BrowseThread thread = new BrowseThread(0);
					thread.start();
				}
				else if (item ==1){
					Toast.makeText(BrowseActivity.this, sortTypes[item], Toast.LENGTH_SHORT).show();
					BrowseThread thread = new BrowseThread(1);
					thread.start();
				}
				else if (item ==2){
					Toast.makeText(BrowseActivity.this, sortTypes[item], Toast.LENGTH_SHORT).show();
					BrowseThread thread = new BrowseThread(2);
					thread.start();
				}
			    
			    
			 }
		});
		
		alert.show(); 
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		MenuInflater inflater = getMenuInflater();
		if (brs.getViewToken().equals("Favourites") || brs.getViewToken().equals("Saved")) {
			inflater.inflate(R.menu.unsave, menu);
		}
		
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		if (item.getItemId() == (R.id.unSaveEntry)) {
			unSave(info);
			return true;
		}
		return super.onContextItemSelected(item);
	}
	
	public void unSave(AdapterContextMenuInfo id){
		ForumEntry focus = browseListAdapter.getItem(id.position);
		if (brs.getViewToken().equals("Favourites")) {
			dm.unSave(focus, "F");
			browseListAdapter.remove(focus);
			browseListAdapter.notifyDataSetChanged();
		} else {
			dm.unSave(focus, "R");
			browseListAdapter.remove(focus);
			browseListAdapter.notifyDataSetChanged();
		}
	}
	
	
	/**
	 * Starts the QuestionActivity.
	 */
	private void startQuestionActivity()
	{
		/*
		 * Start the question activity to view the ForumEntry.
		 */
		Intent intent = new Intent(this, QuestionActivity.class);
		startActivity(intent);
	}
	
	public Intent returnIntent()
	{
		return getIntent();
	}

	@Override
	public void update(ForumEntryList model)
	{
		/*
		 * Cannot use assignment (ie forumEntries = model.getView()) because then
		 * we would have to make a new adapter and then set that new adapter (due
		 * to java being call by value).
		 */
		this.forumEntries.clear();
		this.forumEntries.addAll(model.getView());
		this.browseListAdapter.notifyDataSetChanged();
	}

	class SearchThread extends Thread
	{
		private String search;

		public SearchThread(String s)
		{
			search = s;
		}

		@Override
		public void run()
		{

			super.run();
			browseController.searchAndSet(this.search);
			
			//this wait is important for users with 
			// slow internet DO NOT REMOVE 
			// please and thank you
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			runOnUiThread(doUpdateGUIList);
		}
	}
	
	class BrowseThread extends Thread {
		private int casetype;
		
		public BrowseThread(int type){
			casetype=type;
		}
		
		@Override
		public void run()
		{

			super.run();
			if (casetype==0){
				browseController.sortByTime();
			}
			else if (casetype ==1){
				browseController.sortByRating();
			}
			else if (casetype ==2){
				browseController.sortByHasPicture();
			}
			
			//this wait is important for users with 
			// slow internet DO NOT REMOVE 
			// please and thank you
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			runOnUiThread(doUpdateGUIList);
		}
		
	}

	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}
}

