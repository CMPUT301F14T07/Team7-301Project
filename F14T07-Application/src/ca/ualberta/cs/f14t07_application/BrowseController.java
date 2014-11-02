package ca.ualberta.cs.f14t07_application;


public class BrowseController {

	public void setSessionAuthor(String author) {
		MainScreenModel msm = new MainScreenModel();
		msm.setSessionAuthor(author);
	}
}
