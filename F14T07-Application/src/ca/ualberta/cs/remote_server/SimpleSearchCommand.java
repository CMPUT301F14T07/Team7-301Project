package ca.ualberta.cs.remote_server;

import android.content.Context;
import ca.ualberta.cs.controllers.AuthorController;
import ca.ualberta.cs.intent_singletons.ContextSingleton;

/**
 * turns query into a proper elastic search command
 * so that it can be passed into the elastic search server
 * 
 * taken from:
 * https://github.com/dfserrano/AndroidElasticSearch
 * @author lexie
 * 
 */
public class SimpleSearchCommand {

	private String query;
	private String[] fields;
	private Boolean byLocation=false;
	public SimpleSearchCommand(String query, String[] fields, Boolean byLocation_) {
		this.query = query;
		this.fields = fields;
		byLocation = byLocation_;
	}

	public String getJsonCommand() {
		StringBuffer command = new StringBuffer();

		final AuthorController ac= new AuthorController();
		
		if(ac.getModel().isGpsSet()){
				double latitude = ac.getModel().getSessionLatitude();
				double longitude = ac.getModel().getSessionLongitude();
		
				if(byLocation){
					command = new StringBuffer("{\"sort\":[{\"_geo_distance\" : { \"pin.location\": ["+latitude+ ","+ longitude+"],"
							+ "\"order\":\"asc\", \"unit\": \"km\" } } ] ,"+
							"{\"from\":0, \"size\": 100, \"query\" : {\"query_string\" : {\"query\" : \"" + query
							+ "\"");
				}
			}
		else{
			command = new StringBuffer(
				"{\"from\":0, \"size\": 100, \"query\" : {\"query_string\" : {\"query\" : \"" + query
						+ "\"");
		}

		if (fields != null) {
			command.append(", \"fields\":  [");

			for (int i = 0; i < fields.length; i++) {
				command.append("\"" + fields[i] + "\", ");
			}
			command.delete(command.length() - 2, command.length());

			command.append("]");
		}

		command.append("}}}");

		return command.toString();
	}
}
