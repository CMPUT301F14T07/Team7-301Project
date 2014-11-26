package ca.ualberta.cs.views;

import java.util.ArrayList;

import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.models.Answer;
import ca.ualberta.cs.models.Entry;
import ca.ualberta.cs.models.ForumEntry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
/**
 * This code is to be used for a making a list within a list on the question page (There is currently a list for 
 * answers but that list will be composed of upvote buttons, answers, and their respective replies). It is being left
 * empty for the moment because it will depend on how Entry.java, Question.java, Answer.java, ForumEntry.java and
 * ForumEntryList.java work, so until they are a little more filled out I will leave this code blank
 * @author jfryan*/
public class AnswerListAdapter extends ArrayAdapter<Entry>{

	private Context adaptersContext;
	private ArrayList<Entry> AnswerList;
	
	public AnswerListAdapter(Context context, ArrayList<Entry> AnswerList)
	{
		super(context, android.R.layout.simple_list_item_1, android.R.id.text1, AnswerList);
		this.adaptersContext = context;
		this.AnswerList = AnswerList;
	}
}/*	
	@Override
	public View getView(int listPosition, View listView, ViewGroup parent)
	{		
		class TempAnswerList{
			public Answer answer;
			public TextView answerText;
			public TextView Author;
			public TextView Date;
			public Button UpvoteButton;
			public TextView UpvoteNumber;
//			public ListView Replies;
		};
		final TempAnswerList temp = new TempAnswerList();
		
		LayoutInflater inflater = (LayoutInflater) this.adaptersContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View listViewRow = inflater.inflate(R.layout.answer_list, parent, false);
		
		//temp.todo = this.toDoList.get(listPosition);
		temp.Author = (TextView) listViewRow.findViewById(R.id.AnswerAuthor);
		
		temp.textView.setText(this.toDoList.get(listPosition).getName());
		
		if(temp.todo.isFinished()){ temp.imageView.setImageResource(R.drawable.ic_action_important); }
		else { temp.imageView.setImageResource(R.drawable.ic_action_not_important); }
		
		temp.checkbox.setChecked(this.toDoList.get(listPosition).isSelected());
		
		listViewRow.setTag(temp);
		
		return listViewRow;
	
}
*/