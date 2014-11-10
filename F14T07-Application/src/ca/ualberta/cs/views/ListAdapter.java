package ca.ualberta.cs.views;

import java.util.ArrayList;

import ca.ualberta.cs.models.ForumEntry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<QuestionActivity>{

	private Context adaptersContext;
	private ArrayList<QuestionActivity> AnswerList;
	
	public ListAdapter(Context context, ArrayList<QuestionActivity> QuestionActivity)
	{
		super(context, android.R.layout.simple_list_item_1, android.R.id.text1, QuestionActivity);
		this.adaptersContext = context;
		this.AnswerList = QuestionActivity;
	}
	
//	@Override
//	public View getView(int listPosition, View listView, ViewGroup parent)
//	{		

		
/*
 * This code is to be used for a making a list within a list on the question page (There is currently a list for 
 * answers but that list will be composed of upvote buttons, answers, and their respective replies). It is being left
 * empty for the moment because it will depend on how Entry.java, Question.java, Answer.java, ForumEntry.java and
 * ForumEntryList.java work, so until they are a little more filled out I will leave this code blank
 * 
 */
		
		
//	}
	
}
