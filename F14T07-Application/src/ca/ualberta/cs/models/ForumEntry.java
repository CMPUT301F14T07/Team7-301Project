package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Picture;


/**
 * Any thread in a forum is fully defined by a ForumEntry object. This class contains the 
 * question, all the answers, subject, and geographical location of the author. It is
 * basically a model class which provides get and set methods.
 *
 */
public class ForumEntry
{
	private Question question;
	private ArrayList<Answer> answers;
	private String subject;
	private int entryFlag;
	private String id;
	private double latitude = 0.0;
	private double longitude = 0.0; 
	private String location = null;
	private Boolean setLocation = false;
	
	/**
	 * Create a new ForumEntry with an image.
	 * @param subject_ Title of the ForumEntry.
	 * @param question_ The question being asked.
	 * @param author The person who created the question.
	 * @param image The image :/
	 */
	public ForumEntry(String subject_, String question_, String author, String image)
	{
		super();
		question = new Question(question_,author);
		question.setSubject(subject_);
		subject = subject_;
		answers = new ArrayList<Answer>();
		question.setPicture(image);
	}

	/**
	 * Create a new ForumEntry.
	 * @param subject_ Title of the ForumEntry.
	 * @param question_ The question being asked.
	 * @param author The person who created the question.
	 */
	public ForumEntry(String subject_, String question_, String author)
	{
		this(subject_,question_,author,null);
		
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
	 * Return this ForumEntry's Question's subject.
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
	 * Get all the question replies in this ForumEntry.
	 * @return ArrayList<Replies>
	 */
	public List<Reply> getQuestionReplies(){
		return question.getReplies();
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
	 * Rewritten to compare ForumEntries based on their id field.
	 */
	@Override
	public boolean equals(Object focus) {
		if(focus == null)
		{
			return false;
		}
		if(!(focus instanceof ForumEntry))
		{
			return false;
		}
		return ((ForumEntry) focus).getId().equals(id);
	}
	
	public void setLocation(String location_){
		location = location_;
	}
	
	public String getLocation(){
		return location;
		
	}
	
	public double getLatitude(){
		return latitude;
	}
	
	public void setLatitude(double latitude_){
		latitude = latitude_;
	}
	
	public double getLongitude(){
		return longitude;
	}
	
	public void setLongitude(double longitude_){
		longitude = longitude_;
	}

}
