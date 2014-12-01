package ca.ualberta.cs.views;

import java.util.ArrayList;

import ca.ualberta.cs.f14t07_application.R;
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
 *This is used for creating a list of different objects. More specifically for the replies list, includes the reply texts,
 *authors and dates.
 * @author jfryan
 */
public class ReplyListAdapter extends ArrayAdapter<Reply>{

	private Context adaptersContext;
	private ArrayList<Reply> ReplyList;
	
	public ReplyListAdapter(Context context, ArrayList<Reply> ReplyList)
	{
		super(context, android.R.layout.simple_list_item_1, android.R.id.text1, ReplyList);
		this.adaptersContext = context;
		this.ReplyList = ReplyList;
	}

	
	
	@Override
	public View getView(int listPosition, View LinearLayout, ViewGroup parent)
	{		
		class TempReplyList{
			public Reply reply;
			public TextView replyText;
		};
		final TempReplyList temp = new TempReplyList();
		
		LayoutInflater inflater = (LayoutInflater) this.adaptersContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View listViewRow = inflater.inflate(R.layout.reply_list, parent, false);
		
		temp.reply = this.ReplyList.get(listPosition);
		temp.replyText = (TextView) listViewRow.findViewById(R.id.ReplyText);
		
		listViewRow.setTag(temp);
		
		return listViewRow;
	
}
}
