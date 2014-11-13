package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.List;


/**
 * Contains all the information of one forum post.
 *
 */
public class ForumEntry
{
	private Question question;
	private List<Answer> answers;
	private String subject;
	private int entryFlag;
	private long id;
	
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
	}
	
	/**
	 * Create a new ForumEntry.
	 * @param question The question being asked.
	 */
	public ForumEntry(Question question)
	{
		this.question = question;
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
	public void setAnswer(List<Answer> answers)
	{
		this.answers = answers;
	}
	
	/**
	 * Get all the answers in this ForumEntry.
	 * @return ArrayList<Answer>
	 */
	public List<Answer> getAnswers(){
		return answers;
	}
	
	/**
	 * Get the question being asked in this ForumEntry
	 * @return Question
	 */
	public Question getQuestion(){
		return question;
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
	public void setId(int newId){
		id = newId;
	}
}
