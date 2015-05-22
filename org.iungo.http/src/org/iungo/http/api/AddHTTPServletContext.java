package org.iungo.http.api;

import org.iungo.context.api.SimpleContext;


public class AddHTTPServletContext extends HTTPServletContext {

	private static final long serialVersionUID = 1L;

	public AddHTTPServletContext() {
		super();
	}

	public AddHTTPServletContext(final SimpleContext simpleContext) {
		super(simpleContext);
	}

}
