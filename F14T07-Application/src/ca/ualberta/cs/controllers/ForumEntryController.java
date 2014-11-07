package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.views.Observer;

/**
 * Controls the data in the model of the QuestionActivity and EntryActivity. Also
 * Controls the DataManager model and therefore what gets saved and loaded from memory
 * and the remote server.
 */
public class ForumEntryController {

	private ForumEntryList forumEntries;
	private DataManager dataManager;
	
	/**
	 * Creates a new ForumEntryController.
	 * @param viewsContext The context of the view which aggregates the ForumEntryController.
	 */
	void ForumEntryController(Observer viewsContext){}

	/**
	 * Creates a new ForumEntry with the Question and adds it to the remote server, my authored,
	 * and read later save locations
	 * @param question The question which is being created
	 */
	public void addQuestion(Question question) {
		// TODO Auto-generated method stub
		
	}

	public void addAnswer(ForumEntry question, ForumEntry answer) {
		// TODO Auto-generated method stub
		
	};
}
