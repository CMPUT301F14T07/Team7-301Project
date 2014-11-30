package ca.ualberta.cs.models;

/**
 * This class is an answer to a question in a forum entry. There can be 0 to many of these in
 * a forum entry. It inherits from Entry and does not add any new functionality. This class
 * exists for the sake of clarity.
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
