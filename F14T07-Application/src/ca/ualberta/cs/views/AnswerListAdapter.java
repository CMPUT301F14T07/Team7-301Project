package ca.ualberta.cs.views;

import java.util.ArrayList;

import ca.ualberta.cs.f14t07_application.R;
import ca.ualberta.cs.models.Answer;
import ca.ualberta.cs.models.Reply;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout;
/**
 *This is used for creating a list of different objects. More specifically for the answers list, includes the answer texts,
 *authors, dates, upvotes and replies.
 * @author jfryan
 */
public class AnswerListAdapter extends ArrayAdapter<Answer>{

	private Context adaptersContext;
	private ArrayList<Answer> AnswerList;
	
	public AnswerListAdapter(Context context, ArrayList<Answer> AnswerList)
	{
		super(context, android.R.layout.simple_list_item_1, android.R.id.text1, AnswerList);
		this.adaptersContext = context;
		this.AnswerList = AnswerList;
	}
	
	@Override
	public View getView(int listPosition, View LinearLayout, ViewGroup parent)
	{		
	
		class TempReplyList{
			public Answer answer;
			public TextView answerText;
			public TextView author;
			public TextView date;
			public Button upvotebutton;
		};
		final TempReplyList temp = new TempReplyList();
		
		LayoutInflater inflater = (LayoutInflater) this.adaptersContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View listViewRow = inflater.inflate(R.layout.answer_list, parent, false);
		
		temp.answer = this.AnswerList.get(listPosition);
		temp.answerText = (TextView) listViewRow.findViewById(R.id.AnswerText);
		temp.author = (TextView) listViewRow.findViewById(R.id.AnswerAuthor);
		temp.date = (TextView) listViewRow.findViewById(R.id.AnswerDate);
		temp.upvotebutton = (Button) listViewRow.findViewById(R.id.AnswerUpvoteButton);
		listViewRow.setTag(temp);
		
		return listViewRow;
	
}
}
