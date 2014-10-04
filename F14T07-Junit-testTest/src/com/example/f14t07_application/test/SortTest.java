package com.example.f14t07_application.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.example.f14t07_application.Question;

public class SortTest extends ActivityInstrumentationTestCase2<QuestionList> {
	public sortTest(){
		super(PostList.class);
	}
	
	public void sortByTime(){
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
	
	public void sortByHighestRating(){
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
	
	public void sortByhasPicture(){
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


