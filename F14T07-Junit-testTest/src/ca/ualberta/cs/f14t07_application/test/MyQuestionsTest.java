package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import ca.ualberta.cs.models.AuthorModel;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.views.FavouriteActivity;
import ca.ualberta.cs.views.MyQuestionsActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;

public class MyQuestionsTest extends ActivityInstrumentationTestCase2<MyQuestionsActivity>{

	public MyQuestionsTest(Class<MyQuestionsActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testViewAuthoredQuestions(){
		String username="John Doe";
		AuthorModel ac= new AuthorModel();
		ac.setSessionAuthor(username);
		
		ForumEntry entry1 = new ForumEntry("subject1","questionbody1",username);
		ForumEntry entry2 = new ForumEntry("subject2","questionbody2","Adam Smith");
		ForumEntry entry3 = new ForumEntry("subject3","questionbody3","Eric Cartman");
		ForumEntry entry4 = new ForumEntry("subject4","questionbody4",username);
		ForumEntry entry5 = new ForumEntry("subject5","questionbody5","Stan Marsh");
		
		ArrayList<ForumEntry> list = null;
		list.add(entry1);
		list.add(entry2);
		list.add(entry3);
		list.add(entry4);
		list.add(entry5);
		
		ForumEntryList fel= new ForumEntryList();
		
		//This method is supposed to filter the questions asked by the user?
		fel.setMyAuthored(list);
		
		ArrayList<ForumEntry> myAuthored=fel.getMyAuthored();
		
		assert(myAuthored.size() == 2);
		
		boolean i=true;
		
		for (ForumEntry fe : myAuthored){
			if (!(fe.getQuestion().getPost().equals("subject1") || fe.getQuestion().getPost().equals("subject4"))){
				i=false;
			}
		assert(i);
		}
		
		
		
		
		
		
	}
}
