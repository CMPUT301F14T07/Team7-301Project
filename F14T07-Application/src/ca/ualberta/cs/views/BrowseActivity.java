package ca.ualberta.cs.views;

import java.util.ArrayList;
import java.util.List;

import ca.ualberta.cs.controllers.BrowseController;
import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.f14t07_application.R.id;
import ca.ualberta.cs.f14t07_application.R.layout;
import ca.ualberta.cs.f14t07_application.R.menu;
import ca.ualberta.cs.intent_singletons.BrowseRequestSingleton;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.models.Question;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The Browse view implements the observer class
 * */
public class BrowseActivity extends Activity implements Observer<ForumEntryList>
{
	private ArrayAdapter<ForumEntry> browseListAdapter;
	private ListView browseListView;
	public List<ForumEntry> forumEntries;
	private BrowseController browseController;
	private boolean mockMutex_searchFinished = true;

	private Runnable doUpdateGUIList = new Runnable()
	{
		public void run()
		{
			//browseListAdapter.notifyDataSetChanged();
			browseController.useOnLineView();
		}
	};

	/**
	 * lays out the screen and initializes onClickListeners
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.browse_activity_screen);

		forumEntries = new ArrayList<ForumEntry>();
		
		browseController = new BrowseController(this);
		
		browseListAdapter = new ArrayAdapter<ForumEntry>(BrowseActivity.this, R.layout.list_item, forumEntries);
		browseListView = (ListView) findViewById(R.id.browseListView);
		browseListView.setAdapter(browseListAdapter);
		
		Button view_by_button = (Button) findViewById(R.id.browseViewByButton);
		view_by_button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				viewBy();
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
		
		BrowseRequestSingleton brs = BrowseRequestSingleton.getInstance();

		SearchThread thread = new SearchThread(brs.getSearchToken()); /* Use search term in BrowseRequestSingleton */
		thread.start();
		
		/*
		 * Set the text displayed for the "viewing" type to be the view token in the BrowseRequestSingleton
		 */
		TextView viewType = (TextView) findViewById(R.id.browseTextView);
		viewType.setText(brs.getViewToken());
		
		/*
		 * Check the view type here, if an on line view is being used show a search bar. If a different type of view
		 * is being used (like favourites) don't show the search bar.
		 */
		EditText term = (EditText) findViewById(R.id.searchTextInput);
		if(brs.getViewToken() == BrowseRequestSingleton.ON_LINE_VIEW)
		{
			term.setText(brs.getSearchToken()); /* Use search term in BrowseRequestSingleton */
			term.setVisibility(EditText.VISIBLE);
		}
		else
		{
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
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		TextView viewType = (TextView) findViewById(R.id.browseTextView);
		BrowseRequestSingleton brs = BrowseRequestSingleton.getInstance();
		switch (id)
		{
		case R.id.switchToHome:
			Intent homeIntent = new Intent(this, MainScreenActivity.class);
			startActivity(homeIntent);
			return true;
		case R.id.switchToReadLater:
			this.browseController.useReadLaterView();
			brs.setViewToken(BrowseRequestSingleton.READ_LATER_VIEW);
			viewType.setText(brs.getViewToken());
			return true;
		case R.id.switchToMyQuestions:
			this.browseController.useMyAuthoredView();
			brs.setViewToken(BrowseRequestSingleton.MY_AUTHORED_VIEW);
			viewType.setText(brs.getViewToken());
			return true;
		case R.id.switchToFavourites:
			this.browseController.useFavouritesView();
			brs.setViewToken(BrowseRequestSingleton.FAVOURITES_VIEW);
			viewType.setText(brs.getViewToken());
			return true;
		case R.id.switchToOnline:
			this.browseController.useOnLineView();
			brs.setViewToken(BrowseRequestSingleton.ON_LINE_VIEW);
			viewType.setText(brs.getViewToken());
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void viewBy()
	{
		Toast.makeText(BrowseActivity.this, "Work in progress", Toast.LENGTH_SHORT).show();
		/*
		 * Example of how to implement this
		 *
		 * this.browseController.sortBy--X--View();
		 * this.browseController.refresh();
		 */
	}

	public Intent returnIntent()
	{
		return getIntent();
	}

	@Override
	public void update(ForumEntryList model)
	{
		this.forumEntries.addAll(model.getView());
		this.browseListAdapter.notifyDataSetChanged();
	}

	class SearchThread extends Thread
	{
		// TODO: Implement search thread
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
			runOnUiThread(doUpdateGUIList);
		}
	}
	
	
	
	/*
	 * Old version of SearchThread
	 *
	class SearchThread extends Thread
	{
		// TODO: Implement search thread
		private String search;

		public SearchThread(String s)
		{
			search = s;
		}

		@Override
		public void run()
		{

			super.run();
			browseController = new BrowseController(BrowseActivity.this);
			forumEntries.addAll(browseController.getAllEntries());
			ForumEntry forumEntry = new ForumEntry("this", "that", "something");
			forumEntries.add(forumEntry);

			try
			{
				Thread.sleep(500);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			runOnUiThread(doUpdateGUIList);
		}
	}*/
}

