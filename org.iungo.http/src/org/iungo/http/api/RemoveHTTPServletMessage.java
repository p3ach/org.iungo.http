package org.iungo.http.api;

import org.iungo.id.api.ID;

public class RemoveHTTPServletMessage extends AbstractHTTPServletMessage {

	private static final long serialVersionUID = 1L;

	public RemoveHTTPServletMessage(final ID id, final ID from, final ID to, final Integer priority, final AbstractHTTPServlet httpServlet) {
		super(id, from, to, priority, HTTPNode.ADD_SERVLET_KEY, httpServlet);
	}

}
