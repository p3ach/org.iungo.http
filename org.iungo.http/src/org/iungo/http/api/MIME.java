package org.iungo.http.api;

public class MIME {

	public static final String SEPARATOR = "/";
	
	public static final String[] EMPTY_PARAMETERS = new String[0];
	
	public static final String APPLICATION_SPARQL_QUERY = valueOf("application", "sparql-query", EMPTY_PARAMETERS); 
	
	public static final String TEXT_HTML = valueOf("text", "html", EMPTY_PARAMETERS);
	
	public static String valueOf(final String type, final String subType, final String[] parameters) {
		return type + SEPARATOR + subType;
	}
}
