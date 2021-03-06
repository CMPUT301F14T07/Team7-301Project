package ca.ualberta.cs.models;

/**
 * This class is a question in a forum entry. there is only one of these in a forum entry.
 * It extends Entry.
 */
public class Question extends Entry {

	private String questionSubject;
	
	/** 
	 * Create a new question.
	 * @param post_ The question.
	 * @param author_ Name of the person creating the question.
	 */
	public Question(String post_, String author_) {
		super(post_, author_);
		this.questionSubject = "";
	}
	
	/** 
	 * Create a new question.
	 * @param post_ The question.
	 * @param author_ Name of the person creating the question.
	 * @prarm subj_ Subject of the question.
	 */
	public Question(String post_, String author_, String subj_) {
		super(post_, author_);
		this.questionSubject = subj_;
	}

	/**
	 * Sets the questions subject. This is like the title of the question, preceding the 
	 * main body of text.
	 * @param subject The subject of the question.
	 */
	public void setSubject(String subject)
	{
		this.questionSubject = subject;
	}
	
	/**
	 * Gets the question subject. This is like the title of the question preceding the
	 * main body of text.
	 * @return String
	 */
	public String getSubject()
	{
		return this.questionSubject;
	}
	
}
