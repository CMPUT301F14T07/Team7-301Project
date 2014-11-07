package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.Answer;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.Entry;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.models.Question;
import ca.ualberta.cs.models.Reply;
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
	public ForumEntryController(Observer viewsContext){}

	public ForumEntryController(ForumEntry forumEntry){}
	/**
	 * Sets the ForumEntry in the model.
	 * @param forumEntry The forum entry to be pushed to the model.
	 */
	public void setView(ForumEntry forumEntry){}
	
	/**
	 * Creates a new ForumEntry with the Question and adds it to the remote server, my authored,
	 * and read later save locations
	 * @param question The question which is being created
	 */
	public void addNewQuestion(Question question) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Apends an Answer to the ForumEntry in the model then pushes the updated ForumEntry to
	 * the remote server, my authored, and read later save locations. Will push the updated
	 * ForumEntry to the favourites save location too if applicable.
	 * @param answer The answer to the question
	 */
	public void addAnswer(Answer answer) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Saves the ForumEntry from the model as a favourite.
	 */
	public void saveFavouritesCopy(){}
	
	/**
	 * Up vote an entry from the model. Changes are then updated in the applicable save locations.
	 * @param index Index of the Entry to up vote. 0 up votes the Questions, 1 up votes the
	 * first Answer...
	 */
	public void upVoteEntry(int index){}
	
	/** 
	 * Add a Reply to an Entry from the model. Changes are then updated in the applicable save
	 * locations.
	 * @param index Index of the Entry to up vote. 0 adds a reply to the Question, 1 to the first
	 * Answer...
	 * @param reply The Reply to add.
	 */
	public void addReplyToEntry(int index, Reply reply){}
	
	/**
	 * Cross checks the size of the picture is less than 64Kb in a particular Entry from the 
	 * model.
	 * @param index Index of Entry to check picture size of. 0 checks the Question, 1 the first
	 * Answer...
	 */
	public void checkPictureSize(int index){}
	
	/**
	 * Sorts the ForumEntry in the model by the number of votes each answer has. Answer
	 * with the most votes are moved to the top and Answers with the least votes are moved 
	 * to the bottom.
	 */
	public void sortForumEntryByVotes(){}
}
