package ca.ualberta.cs.models;

import ca.ualberta.cs.views.MainScreenActivity;

/**
 * Deprecated. Do not use.
 *
 */
public class MainScreenModel {

	public void setSessionAuthor(String author) {
		DataManager dm = new DataManager();
		dm.saveAuthor(author);
		MainScreenActivity msa = new MainScreenActivity();
		//msa.update(author);
	}

}
