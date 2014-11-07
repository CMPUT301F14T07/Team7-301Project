package ca.ualberta.cs.models;

/**
 * This class is an answer to a question in a forum entry. There can be 0 to many of these in
 * a forum entry.
 */
public class Answer extends Entry {

	/**
	 * Creates a new answer.
	 * @param post_ The answer.
	 * @param author_ The person creating the answer.
	 */
	public Answer(String post_, String author_) {
		super(post_, author_);
	}

}
