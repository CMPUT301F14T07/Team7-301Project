package ca.ualberta.cs.models;

import java.util.ArrayList;

import ca.ualberta.cs.views.Observer;

public class ForumEntryList extends Observable<Observer> {

	public void setView(ArrayList<ForumEntry> list) {
		
	}
	
	public void setMyAuthored(ArrayList<ForumEntry> list) {
		
	}
	
	public void setReadLater(ArrayList<ForumEntry> list) {
		
	}
	
	public void setFavourites(ArrayList<ForumEntry> list) {
		
	}
	
	public boolean setRemote(ArrayList<ForumEntry> list) {
		
		return false;
	}
	
	public boolean setRemoteBlynd(int x, ForumEntry fe) {
		
		return false;
	}
	
	public ArrayList<ForumEntry> getView() {
		
		ArrayList<ForumEntry> list=null;
		return list;
	}
	
	public ArrayList<ForumEntry> getMyAuthored() {
		
		ArrayList<ForumEntry> list=null;
		return list;
	}
	
	public ArrayList<ForumEntry> getReadLater() {
		
		ArrayList<ForumEntry> list=null;
		return list;
	}
	
	public ArrayList<ForumEntry> getFavourites() {
		
		ArrayList<ForumEntry> list=null;
		return list;
	}
	
	public ArrayList<ForumEntry> getRemote(String a,String b, String c) {
		
		ArrayList<ForumEntry> list=null;
		return list;
	}
	
	public boolean appendToRemote(ForumEntry fe) {
		
		return true;
	}
	
	public ForumEntry getRemoteBlyd(int x){
		
		ForumEntry fe = null;
		return fe;
	}
}
