package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Picture;


/**
 * Contains all the information of one forum post.
 *
 */
public class ForumEntry
{
	private Question question;
	private ArrayList<Answer> answers;
	private String subject;
	private int entryFlag;
	private String id;
	
	/**
	 * Create a new ForumEntry.
	 * @param subject_ Title of the ForumEntry.
	 * @param question_ The question being asked.
	 * @param author The person who created the question.
	 */
	public ForumEntry(String subject_, String question_, String author)
	{
		super();
		question = new Question(question_,author);
		question.setSubject(subject_);
		subject = subject_;
		answers = new ArrayList<Answer>();
		question.setPicture(null);
		
	}
	public ForumEntry(String subject_, String question_, String author, Bitmap picture_)
	{
		super();
		question = new Question(question_,author);
		question.setSubject(subject_);
		subject = subject_;
		answers = new ArrayList<Answer>();
		question.setPicture(picture_);
		
	}
	/**
	 * Create a new ForumEntry.
	 * @param question The question being asked.
	 */
	public ForumEntry(Question question)
	{
		this.question = question;
		this.answers = new ArrayList<Answer>();
	}
	
	/**
	 * Return this ForumEntry's Question's main body of text.
	 * @return String
	 */
	public String toString(){ 
		return question.getSubject();
	}
	
	/**
	 * Set the answers of this ForumEntry.
	 * @param answers The answers.
	 */
	public void setAnswer(ArrayList<Answer> answers)
	{
		this.answers = answers;
	}
	
	/**
	 * Get all the answers in this ForumEntry.
	 * @return ArrayList<Answer>
	 */
	public ArrayList<Answer> getAnswers(){
		return answers;
	}
	
	/**
	 * Get the question being asked in this ForumEntry
	 * @return Question
	 */
	public Question getQuestion(){
		return question;
	}
	
	public void setId(String newId){
		id = newId;
	}
	public String getId(){
		return id;
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param newId
	 */
	public void setSubject(String s){
		subject = s;
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param newId
	 */
	public String getSubject(){ 
		return subject;
	}
	
	/**
	 * Deprecated. Do not use. Use setAnswer() instead.
	 * @param newId
	 */
	public void addAnswer(Answer Answer){
		answers.add(Answer);
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param newId
	 */
	public int getFlag(){ 
		return entryFlag;
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param newId
	 */
	public void setFlag(int f){
		entryFlag=f;
	}
	
	/**
	 * Deprecated. Do not use.
	 * @param newId
	 */

}
