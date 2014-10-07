package ca.ualberta.cs.f14t07_application.test;

import java.util.ArrayList;

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
		PostList questions=new PostList; 
		questions.add(new Question("test 1"));
		questions.add(new Question("test 2"));
		questions.add(new Question("test 3"));
		
		questions.sortByTime();
		ArrayList<Question> testList= new ArrayList<Question>();
		testList.add(new Question("test 1"));
		testList.add(new Question("test 2"));
		testList.add(new Question("test 3"));
		
		assertEquals(questions.getlist(),testList);
		
		}
	
	public void sortByRatingTest(){
		PostList questions=new PostList; 
		questions.add(new Question("test 1"));
		questions.add(new Question("test 2"));
		questions.add(new Question("test 3"));
		
		questions.changeRating(0,5);
		questions.changeRating(1,-1);
		questions.changeRating(2,10);
		
		questions.sortByRating();
		
		ArrayList<Question> testList= new ArrayList<Question>();
		testList.add(new Question("test 3"));
		testList.add(new Question("test 1"));
		testList.add(new Question("test 2"));
		
		assertEquals(questions.getlist(),testList);
		
		}
	
	public void sortByHasPictureTest(){
		PostList questions=new PostList; 
		questions.add(new Question("test 1"));
		questions.add(new Question("test 2"));
		questions.add(new Question("test 3"));
		
		questions.addPicture(1);
		
		questions.sortByHasPicture();
		
		ArrayList<Question> testList= new ArrayList<Question>();
		testList.add(new Question("test 2"));
		testList.add(new Question("test 1"));
		testList.add(new Question("test 3"));
		
		assertEquals(questions.getlist(),testList);
		
		}
	}


