package ca.ualberta.cs.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Movie;
import android.util.Log;
import ca.ualberta.cs.f14t07_application.Hits;
import ca.ualberta.cs.models.ForumEntry;
import ca.ualberta.cs.remote_server.SearchHit;
import ca.ualberta.cs.remote_server.SearchResponse;
import ca.ualberta.cs.remote_server.SimpleSearchCommand;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Controls the data in the model of the SearchActivity
 */
public class SearchController {
	private List<ForumEntry> searchResult;
	private static final String SEARCH_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t07/ForumEntry/_search";

	private static final String TAG = "ForumEntrySearch";
	
	private Gson gson;
	
	/**
	 * Creates a new SearchController.
	 */
	public SearchController(){
		searchResult = new ArrayList<ForumEntry>();
		gson = new Gson();
	}
	
	/**
	 * Searches the entries for an empty screen, thereby showing all the entries
	 * @return the search result
	 * */
	public List<ForumEntry> searchAll(){
		//searchResult.clear(); 
		SearchThread thread = new SearchThread("");
		thread.start();
		
		ForumEntry forumEntry= new ForumEntry("search","Search","search");
		searchResult.add(forumEntry);
		
		return searchResult;
	}
	
	/**
	 * Searches the forum entries for a specific term
	 * @param the term, and the field
	 * @throws ClientProtocolException, IOException
	 * */
	public void searchForumEntries(String searchString, String field) throws ClientProtocolException, IOException { 
		searchResult = new ArrayList<ForumEntry>();

		// TODO: Implement search movies using ElasticSearch
		if ("".equals(searchString)||searchString==null){
			searchString= "*";
		}
		HttpClient httpClient = new DefaultHttpClient();
		
		try{
		HttpPost searchRequest = createSearchRequest(searchString, field);
		HttpResponse response = httpClient.execute(searchRequest);
	
		String status = response.getStatusLine().toString();
		Log.i(TAG, status);
		
		SearchResponse<ForumEntry> esResponse = parseSearchResponse(response);
		
		Hits<ForumEntry> hits = esResponse.getHits();
		
		if(hits != null){
			if (hits.getHits() != null ){
				//there are movies in the search 
				for(SearchHit<ForumEntry> sesr: hits.getHits()){
					searchResult.add(sesr.getSource());
				}
			}
		}
		} catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
	
	}
	/**
	 * creates the search request
	 * @param the term, and the field
	 * @throws UnsupportedEncodingException
	 * @return search Request
	 * */
	private HttpPost createSearchRequest(String searchString, String field) throws UnsupportedEncodingException{ 
		
		HttpPost searchRequest = new HttpPost(SEARCH_URL);

		String[] fields = null;
		if (field != null) {
			fields = new String[1];
			fields[0] = field;
		}
		
		SimpleSearchCommand command = new SimpleSearchCommand(searchString,	fields);
		
		String query = command.getJsonCommand();
		Log.i(TAG, "Json command: " + query);

		StringEntity stringEntity;
		stringEntity = new StringEntity(query);

		searchRequest.setHeader("Accept", "application/json");
		searchRequest.setEntity(stringEntity);

		return searchRequest;
	}
	
	/**
	 * parses through the response from elasticsearch
	 * @param the response
	 * @throws IOException
	 * @return Elastic Search Response
	 * */
	private SearchResponse<ForumEntry> parseSearchResponse(HttpResponse response) throws IOException { 
		String json; 
		json = getEntityContent(response);
		
		Type searchResponseType = new TypeToken<SearchResponse<ForumEntry>>(){}.getType();
		
		SearchResponse<ForumEntry> esResponse = gson.fromJson(json, searchResponseType);
		
		return esResponse;
		
	}
	
	/**
	 * gets the response from elasticsearch
	 * @param the response
	 * @throws IOException
	 * @return the string of the response
	 * */
	public String getEntityContent(HttpResponse response) throws IOException { 
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	
		StringBuffer result = new StringBuffer(); 
		String line = "";
		
		while ((line = rd.readLine()) != null){
			result.append(line);
		}
		
		return result.toString();
	}
	
/**
 * Separate thread for searching
 */
class SearchThread extends Thread {
	// TODO: Implement search thread
	private String search; 
	
	public SearchThread(String s){
		search = s;
	}
	public void run(){ 
		//searchResult.clear();
		try {
			ForumEntry forumEntry= new ForumEntry("search","Search","search");
			
			searchForumEntries(search,null);
			searchResult.add(forumEntry);
			
		//	runOnUiThread();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}
}
