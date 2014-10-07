package ca.ualberta.cs.f14t07_application.test;

import android.test.ActivityInstrumentationTestCase2;

public class SearchTest extends ActivityInstrumentationTestCase2<QuestionList> {

    public SearchTest(){
    	super(PostList.class);
    }

    public void getSearchTermTest(){
    	String searchTerm = "foo";
    	String inputedTerm = getSearchTerm();
    	assertEquals(inputedTerm,searchTerm);	
    }

    public void sortBySearchTerm()){
    	PostList posts = new PostList;
		posts.add(new Question("no term"));
		posts.add(new Question("still no term"));
		posts.add(new Question("has foo!"));
		posts.add(new Question("has foo foo twice!")); //If it has the search term twice, it should probably be above? (Tested in sortedList2)
		posts.sortBySearchTerm("foo");

		ArrayList<Question> sortedList = new ArrayList<Question>();
		sortedList.add(new Question("has foo!"));
		sortedList.add(new Question("has foo foo twice!"));
		sortedList.add(new Question("no term"));
		sortedList.add(new Question("still no term"));
		assertEquals(posts.getlist(), sortedList);
		
		ArrayList<Question> sortedList2 = new ArrayList<Question>();
		sortedList2.add(new Question("has foo foo twice!"));
		sortedList2.add(new Question("has foo!"));
		sortedList2.add(new Question("no term"));
		sortedList2.add(new Question("still no term"));
		assertEquals(posts.getlist(), sortedList2);
    }

}
