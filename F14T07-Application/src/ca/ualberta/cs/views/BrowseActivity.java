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

/**
 * The Browse view implements the observer class
 * */
public class BrowseActivity extends Activity implements Observer<ForumEntryList>
{
	private ArrayAdapter<ForumEntry> browseListAdapter;
	private ListView browseListView;
	public List<ForumEntry> forumEntries;
	private BrowseController browseController;

	private Runnable doUpdateGUIList = new Runnable()
	{
		public void run()
		{
			browseListAdapter.notifyDataSetChanged();
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
		forumEntries = new ArrayList<ForumEntry>();

		browseController = new BrowseController(this);

		browseListAdapter = new ArrayAdapter<ForumEntry>(BrowseActivity.this, R.layout.list_item, forumEntries);
		browseListView = (ListView) findViewById(R.id.browseListView);
		browseListView.setAdapter(browseListAdapter);
		SearchThread thread = new SearchThread(BrowseRequestSingleton.getInstance().getSearchToken());
		thread.start();

		browseListAdapter.notifyDataSetChanged();

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
		switch (id)
		{
		case R.id.switchToHome:
			Intent homeIntent = new Intent(this, MainScreenActivity.class);
			startActivity(homeIntent);
			return true;
		case R.id.switchToReadLater:
			Intent readLaterIntent = new Intent(this, ReadLaterActivity.class);
			startActivity(readLaterIntent);
			return true;
		case R.id.switchToMyQuestions:
			Intent myQuestionsIntent = new Intent(this,
					MyQuestionsActivity.class);
			startActivity(myQuestionsIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void viewBy()
	{
		Toast.makeText(BrowseActivity.this, "Work in progress",
				Toast.LENGTH_SHORT).show();
	}

	public Intent returnIntent()
	{
		return getIntent();
	}

	@Override
	public void update(ForumEntryList model)
	{
		// TODO Auto-generated method stub

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
	}
}

