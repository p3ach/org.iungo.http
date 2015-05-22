package org.iungo.http.api;

import javax.servlet.AsyncContext;

import org.iungo.message.api.AbstractMessageHandle;

public abstract class AbstractHTTPServletMessageHandle extends AbstractMessageHandle {

	private final AsyncContext asyncContext;

	public AbstractHTTPServletMessageHandle(final AsyncContext asyncContext) {
		super();
		this.asyncContext = asyncContext;
	}

	public AsyncContext getAsyncContext() {
		return asyncContext;
	}
}
