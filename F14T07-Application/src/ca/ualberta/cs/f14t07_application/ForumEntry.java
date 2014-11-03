package ca.ualberta.cs.f14t07_application;

import java.util.ArrayList;

/**
 * Contains all the information of one forum post.
 * @author Brendan
 *
 */
public class ForumEntry
{
	private ArrayList<Entry> answers;
	private Entry question;
	private String subject;
	private int entryFlag;
	private long id;
	
	/** 
	 * Instantiate a question.
	 * @param question	The text contains the main question for the forum post.
	 * @param author	The name of the main poster
	 */
	public String toString(){ 
		return question.getPost();
	}
	public ForumEntry(String subject_, String question_, String author)
	{
		super();
		question = new Entry(question_,author);
		subject = subject_;
		
		answers = new ArrayList<Entry>();
	}
	public void addAnswer(Entry Answer){
		answers.add(Answer);
	}
	public ArrayList<Entry> getAnswers(){
		return answers;
	}
	public Entry getQuestion(){
		return question;
	}
	public int getFlag(){ 
		return entryFlag;
	}
	public void setFlag(int f){
		entryFlag=f;
	}
	public void setSubject(String s){
		subject = s;
	}
	public String getSubject(){ 
		return subject;
	}
	public void setId(int newId){
		id = newId;
	}
}
