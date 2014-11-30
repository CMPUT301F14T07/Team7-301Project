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
 * It also handles searching for queries and processing the results
 * 
 * @author lexie
 */
public class SearchController
{
	private ArrayList<ForumEntry> searchResult;
	private HttpClient httpclient = new DefaultHttpClient();
	private Gson gson;
	
	private static final String SEARCH_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t07/ForumEntry/_search";
	private static final String TAG = "ForumEntrySearch";


	/**
	 * Constructor
	 */
	public SearchController()
	{
		searchResult = new ArrayList<ForumEntry>();
		gson = new Gson();
	}

	/**
	 * Searches the forum entries in the remote server for a specific term.
	 * 
	 * @param searchString The term to search for. pass as null, "", or "*" to perform a 
	 * search for everything.
	 * @param field pass as null
	 * @return an ArrayList<ForumEntry> containing all the ForumEntries which match the search
	 * term.
	 * @throws ClientProtocolException, IOException
	 * */
	public ArrayList<ForumEntry> searchForumEntries(String searchString, String field,Boolean byLocation)
			throws ClientProtocolException, IOException
	{
		ArrayList<ForumEntry> result = new ArrayList<ForumEntry>();
		// TODO: Implement search movies using ElasticSearch
		if ("".equals(searchString) || searchString == null)
		{
			searchString = "*";
		}
		HttpClient httpClient = new DefaultHttpClient();

		try
		{
			HttpPost searchRequest = createSearchRequest(searchString, field, byLocation);
			HttpResponse response = httpClient.execute(searchRequest);

			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

			SearchResponse<ForumEntry> esResponse = parseSearchResponse(response);

			Hits<ForumEntry> hits = esResponse.getHits();
			int i = 0;
			if (hits != null)
			{
				if (hits.getHits() != null)
				{
					// there are movies in the search
					for (SearchHit<ForumEntry> sesr : hits.getHits())
					{
						result.add(sesr.getSource());
						result.get(i).setId(sesr.get_id());
						i++;
					}
				}
			}
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public ArrayList<ForumEntry> searchForumEntries(String searchString, String field) throws ClientProtocolException, IOException{
	   return searchForumEntries(searchString,field,false);
	}
	/**
	 * creates the search request
	 * @param byLocation 
	 * 
	 * @param the
	 *            term, and the field
	 * @throws UnsupportedEncodingException
	 * @return search Request
	 * */
	private HttpPost createSearchRequest(String searchString, String field, Boolean byLocation)
			throws UnsupportedEncodingException
	{

		HttpPost searchRequest = new HttpPost(SEARCH_URL);

		String[] fields = null;
		if (field != null)
		{
			fields = new String[1];
			fields[0] = field;
		}

		SimpleSearchCommand command = new SimpleSearchCommand(searchString,
				fields, byLocation);

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
	 * 
	 * @param the
	 *            response
	 * @throws IOException
	 * @return Elastic Search Response
	 * */
	private SearchResponse<ForumEntry> parseSearchResponse(HttpResponse response)
			throws IOException
	{
		String json;
		json = getEntityContent(response);

		Type searchResponseType = new TypeToken<SearchResponse<ForumEntry>>()
		{
		}.getType();

		SearchResponse<ForumEntry> esResponse = gson.fromJson(json,
				searchResponseType);

		return esResponse;

	}

	/**
	 * gets the response from elasticsearch
	 * 
	 * @param the
	 *            response
	 * @throws IOException
	 * @return the string of the response
	 * */
	public String getEntityContent(HttpResponse response) throws IOException
	{
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";

		while ((line = rd.readLine()) != null)
		{
			result.append(line);
		}

		return result.toString();
	}

}
