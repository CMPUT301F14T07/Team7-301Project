package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.Date;


import android.graphics.Picture;

public class Entry {
	private int upVote; 
	private String post;
	private String posterName;
	private ArrayList<Reply> replies;
	private Date date;
	private Picture picture = null; 
	
	
	public Entry(String post_, String author_){
		post = post_;
		posterName = author_;
	}
	public ArrayList<Reply> getReplies(){
		return replies;
	}
	public void addReplies(Reply reply){
		replies.add(reply);
	}
	public int getUpVote(){
		return upVote;
	}
	public void setUpVote(int upVote_){
		upVote=upVote_;
	}
	public String getPost(){
		return post;
	}
	public void setPost(String s){ 
		post = s;
	}
	public String getAuthorsName(){
		return posterName;
	}
	public void setAuthorName(String name){
		posterName = name; 
	}
	public Date getDate(){
		return date;
	}
	public void setPicture(Picture pic){
		picture = pic;
	}
	public Picture getPicture(){ 
		return picture;
	}
}
