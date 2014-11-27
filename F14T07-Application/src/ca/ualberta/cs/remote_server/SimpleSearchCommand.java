package ca.ualberta.cs.remote_server;

/**
 * turns query into a proper elastic search command
 * so that it can be passed into the elastic search server
 * 
 * @author lexie
 * 
 */
public class SimpleSearchCommand {

	private String query;
	private String[] fields;

	public SimpleSearchCommand(String query) {
		this(query, null);
	}

	public SimpleSearchCommand(String query, String[] fields) {
		this.query = query;
		this.fields = fields;
	}

	public String getJsonCommand() {
		StringBuffer command = new StringBuffer(
				"{\"from\":0, \"size\": 100, \"query\" : {\"query_string\" : {\"query\" : \"" + query
						+ "\"");

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
