package ca.ualberta.cs.controllers;

import java.util.ArrayList;
import java.util.List;

import android.R.bool;
import android.content.Context;
import ca.ualberta.cs.models.Answer;
import ca.ualberta.cs.models.DataManager;
import ca.ualberta.cs.models.Entry;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.models.ForumEntryList;
import ca.ualberta.cs.models.Question;
import ca.ualberta.cs.models.Reply;
import ca.ualberta.cs.views.Observer;

/**
 * Controls the data in the model of the QuestionActivity and EntryActivity.
 * Also Controls the DataManager model and therefore what gets saved and loaded
 * from memory and the remote server.
 */
public class ForumEntryController
{
	private ForumEntryList forumEntries;
	private DataManager dataManager;

	/**
	 * Constructor.
	 * @param viewsContext This is the instance of the view creating the controller. 
	 * Generally, the argument will be "this". This parameter is required so that 
	 * the model classes have access to the views update() method.
	 */
	public ForumEntryController(Observer viewsContext)
	{
		this.forumEntries = new ForumEntryList();
		this.forumEntries.addObserver(viewsContext);

		this.dataManager = new DataManager();
	}


	/**
	 * Sets the ForumEntry (given as input) in the model to be the first and only 
	 * ForumEntry then invokes the models notifyObservers() method.
	 * 
	 * @param forumEntry
	 *            The forum entry which will be set in the model.
	 */
	public void setView(ForumEntry forumEntry)
	{
		ArrayList<ForumEntry> fel = new ArrayList<ForumEntry>();
		fel.add(forumEntry);
		this.forumEntries.setView(fel);
		this.forumEntries.notifyObservers();
	}

	/**
	 * Creates a new ForumEntry with the Question and adds it to the remote
	 * server, my authored, and read later save locations
	 * 
	 * @param question
	 *            The question which is being created
	 */
	public void addNewQuestion(Question question)
	{
		ForumEntry fe = new ForumEntry(question);
		this.dataManager.addForumEntry(fe);
	}

	/**
	 * Adds the ForumEntry to the remote server, my authored, and read later
	 * save locations
	 * 
	 * @param forumEntry
	 *            The forumEntry being added.
	 */
	public void addNewQuestion(ForumEntry forumEntry)
	{
		this.dataManager.addForumEntry(forumEntry);
	}

	/**
	 * Appends an Answer to the ForumEntry in the remote server and the model.
	 * Does not call the online models notifyObservers() method.
	 * 
	 * @param answer
	 *            The answer to the question
	 */
	public void addAnswer(Answer answer)
	{
		/*
		 * Get the ForumEntry that the answer is being added to.
		 */
		ArrayList<ForumEntry> fel = this.forumEntries.getView();
		ForumEntry forumEntry = fel.get(ForumEntryList.FIRST_FORUM_ENTRY);

		/*
		 * Add the answer to the ForumEntry.
		 */
		ArrayList<Answer> allAnswers = forumEntry.getAnswers();
		allAnswers.add(answer);
		forumEntry.setAnswer(allAnswers);
		dataManager.updateForumEntry(forumEntry);
		

	}
	
	
	/**
	 * Checks if the ForumEntry focus is in the ArrayList fel.
	 * @param fel 
	 * @param focus
	 * @return true on success, false on failure.
	 */
	private Boolean checkSaved(ArrayList<ForumEntry> fel, ForumEntry focus) {
		for (int i = 0; i < fel.size(); i++) {
			if (fel.get(i).equals(focus)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	/**
	 * Saves the first ForumEntry from the model as a read later.
	 */
	public void saveReadLaterCopy()
	{
		ArrayList<ForumEntry> fel = this.dataManager.getReadLater();
		ForumEntry focus = this.forumEntries.getView().get(ForumEntryList.FIRST_FORUM_ENTRY);
		
		Boolean savedAlready = checkSaved(fel, focus);
		if (savedAlready.equals(false)) {
			fel.add(focus);
		}
		
		this.dataManager.setReadLater(fel);
	}
	

	/**
	 * Saves the first ForumEntry from the model as a favourite.
	 */
	public void saveFavouritesCopy()
	{
		ArrayList<ForumEntry> fel = this.dataManager.getFavourites();
		ForumEntry focus = this.forumEntries.getView().get(ForumEntryList.FIRST_FORUM_ENTRY);
		
		Boolean savedAlready = checkSaved(fel, focus);
		if (savedAlready.equals(false)) {
			fel.add(focus);
		}
		
		this.dataManager.setFavourites(fel);
	}

	/**
	 * Gets the first ForumEntry in the model then upvotes one of its entries based on
	 * the input argument. The model is updated with this change and so is the remote
	 * server.
	 * 
	 * @param index
	 *            Index of the Entry to up vote. 0 up votes the Questions, 1 up
	 *            votes the first Answer...
	 */
	public void upVoteEntry(int index)
	{
		ArrayList<ForumEntry> fel = this.forumEntries.getView();
		ForumEntry focus = fel.get(ForumEntryList.FIRST_FORUM_ENTRY);
		
		if(index == 0)
		{
			int upVote = focus.getQuestion().getUpVote();
			upVote += 1;
			focus.getQuestion().setUpVote(upVote);
		}
		else if(index > 0)
		{
			ArrayList<Answer> aws = focus.getAnswers();
			if(aws.size() >= index)
			{
				int upVote = aws.get(index-1).getUpVote();
				upVote += 1;
				aws.get(index-1).setUpVote(upVote);
			}
		}
		dataManager.updateForumEntry(focus);
/*
		this.forumEntries.setView(fel);
		this.forumEntries.notifyObservers();
		*/
		/*
		 * TODO: Make this update in the remote server
		 * TODO: Check if this ForumEntry is a favourite/read later/my authored and make
		 * 		 update there too.
		 */
	}
	
	/**
	 * Invokes the models notifyObservers() method.
	 */
	public void updateView(){
		ArrayList<ForumEntry> fel = this.forumEntries.getView();
		ForumEntry focus = fel.get(ForumEntryList.FIRST_FORUM_ENTRY);
		this.forumEntries.setView(fel);
		this.forumEntries.notifyObservers();
	}
	
	
	/**
	 * Gets the first ForumEntry from the model then adds a reply to one of the entries
	 * depending on the input arguments. The model is updated with this new reply and
	 * so is the remote server. The models notifyObservers() method is invoked at the end.
	 * 
	 * @param index
	 *            Index of the Entry to up vote. 0 adds a reply to the Question,
	 *            1 to the first Answer...
	 * @param reply
	 *            The Reply to add.
	 */
	public void addReplyToEntry(int index, Reply reply)
	{
		ArrayList<ForumEntry> fel = this.forumEntries.getView();
		ForumEntry focus = fel.get(ForumEntryList.FIRST_FORUM_ENTRY);
		
		if(index == 0)
		{
			focus.getQuestion().addReplies(reply);

		}
		else if(index > 0)
		{
			List<Answer> aws = focus.getAnswers();
			if(aws.size() >= index)
			{
				aws.get(index-1).addReplies(reply);
			}
		}
		dataManager.updateForumEntry(focus);
		this.forumEntries.setView(fel);
		this.forumEntries.notifyObservers();
		
		/*
		 * TODO: Make this update in the remote server
		 * TODO: Check if this ForumEntry is a favourite/read later/my authored and make
		 * 		 update there too.
		 */
	}

	/**
	 * Cross checks the size of the picture is less than 64Kb in a particular
	 * Entry from the model.
	 * 
	 * @param index
	 *            Index of Entry to check picture size of. 0 checks the
	 *            Question, 1 the first Answer...
	 */
	public void checkPictureSize(int index)
	{
	}

	/**
	 * Sorts the ForumEntry in the model by the number of votes each answer has.
	 * Answer with the most votes are moved to the top and Answers with the
	 * least votes are moved to the bottom.
	 */
	public void sortForumEntryByVotes()
	{
	}

}
