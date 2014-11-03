package ca.ualberta.cs.f14t07_application;

public class MainScreenModel {

	public void setSessionAuthor(String author) {
		DataManager dm = new DataManager();
		dm.saveAuthor(author);
		MainScreenActivity msa = new MainScreenActivity();
		msa.update(author);
	}

}
