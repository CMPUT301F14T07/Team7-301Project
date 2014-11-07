package ca.ualberta.cs.models;

/**
 * This class is a question in a forum entry. there is only one of these in a forum entry
 */
public class Question extends Entry {

	/** 
	 * Create a new question.
	 * @param post_ The question.
	 * @param author_ Name of the person creating the question.
	 */
	public Question(String post_, String author_) {
		super(post_, author_);
	}

}
