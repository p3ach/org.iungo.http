package org.iungo.http.api;

import org.iungo.id.api.ID;
import org.iungo.message.api.Message;

public abstract class AbstractHTTPServletMessage extends Message {

	private static final long serialVersionUID = 1L;
	
	private final HTTPServletContext httpServletContext;
	
	public AbstractHTTPServletMessage(final ID id, final ID from, final ID to, final Integer priority, final ID key, final HTTPServletContext httpServletContext) {
		super(id, from, to, priority, key);
		this.httpServletContext = httpServletContext;
	}

	public HTTPServletContext getHTTPServletContext() {
		return httpServletContext;
	}
}
