package org.iungo.http.api;

import org.iungo.id.api.ID;

public class AddHTTPServletMessage extends AbstractHTTPServletMessage {

	private static final long serialVersionUID = 1L;

	private final AddHTTPServletContext addHTTPServletContext;
	
	public AddHTTPServletMessage(final ID id, final ID from, final ID to, final Integer priority, final AddHTTPServletContext addHTTPServletContext) {
		super(id, from, to, priority, HTTPNode.ADD_SERVLET_KEY, addHTTPServletContext);
		this.addHTTPServletContext = addHTTPServletContext;
	}

	public AddHTTPServletContext getAddHTTPServletContext() {
		return addHTTPServletContext;
	}
}
