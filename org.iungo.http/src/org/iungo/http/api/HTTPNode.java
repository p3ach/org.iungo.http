package org.iungo.http.api;

import org.iungo.id.api.ID;
import org.iungo.id.api.IDFactory;


public interface HTTPNode {

	static final IDFactory ID_FACTORY = new IDFactory(HTTPNode.class.getName(), null);
	
	static final ID ADD_SERVLET_KEY = ID_FACTORY.create("Servlet", "Add");
	
	static final ID REMOVE_SERVLET_KEY = ID_FACTORY.create("Servlet", "Remove");

}
