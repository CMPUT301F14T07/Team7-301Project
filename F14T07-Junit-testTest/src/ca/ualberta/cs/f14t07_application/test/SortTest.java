package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

import ca.ualberta.cs.f14t07_application.PostList;
import ca.ualberta.cs.f14t07_application.Question;

import android.test.ActivityInstrumentationTestCase2;


public class SortTest extends ActivityInstrumentationTestCase2<QuestionList> {
	/* TODO: The Class generic that goes with ActivityInstrumentationTestCase2 is
	 * an activity class - the activity which will be active when the test cases
	 * are called.
	 */
	public SortTest(){
		super(PostList.class);
	}
	
	public void sortByTimeTest(){
		ForumEntryList questions=new ForumEntryList(); 
		questions.add(new ForumEntry("test 1"));
		questions.add(new ForumEntry("test 2"));
		questions.add(new ForumEntry("test 3"));
		
		//set time using index, day month year hour minute
		questions.setTime(0,30,01,1982,17,04);
		questions.setTime(1,30,01, 1987,17,04);
		questions.setTime(1,30,01,1987,17,05);
		
		questions.sortByTime();
		ForumEntryList testList= new ForumEntryList();
		testList.add(new ForumEntry("test 3"));
		testList.add(new ForumEntry("test 2"));
		testList.add(new ForumEntry("test 1"));
		
		assertEquals(questions.getlist(),testList.getlist());
		
		}
	
	public void sortByRatingTest(){
		ForumEntryList questions=new ForumEntryList(); 
		questions.add(new ForumEntry("test 1"));
		questions.add(new ForumEntry("test 2"));
		questions.add(new ForumEntry("test 3"));
		
		questions.changeRating(0,5);
		questions.changeRating(1,-1);
		questions.changeRating(2,10);
		
		questions.sortByRating();
		
		ForumEntryList testList= new ForumEntryList();
		testList.add(new ForumEntry("test 3"));
		testList.add(new ForumEntry("test 1"));
		testList.add(new ForumEntry("test 2"));
		
		assertEquals(questions.getlist(),testList.getlist());
		
		}
	
	public void sortByHasPictureTest(){
		ForumEntryList questions=new ForumEntryList; 
		questions.add(new ForumEntry("test 1"));
		questions.add(new ForumEntry("test 2"));
		questions.add(new ForumEntry("test 3"));
		
		questions.addPicture(1);
		
		questions.sortByHasPicture();
		
		ForumEntryList testList= new ForumEntryList();
		testList.add(new ForumEntry("test 2"));
		testList.add(new ForumEntry("test 1"));
		testList.add(new ForumEntry("test 3"));
		
		assertEquals(questions.getlist(),testList.getlist());
		
		}
	}


