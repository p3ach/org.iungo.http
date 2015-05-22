package org.iungo.http.api;

import org.iungo.context.api.Context;
import org.iungo.node.api.NodeContext;

public class HTTPNodeContext extends NodeContext {

	private static final long serialVersionUID = 1L;

	public static final String HOST = "Host";
	
	public static final String PORT = "Port";
	
	public HTTPNodeContext() {
		super();
	}

	public HTTPNodeContext(final NodeContext nodeContext) {
		super(nodeContext);
	}

	public void setHost(final String host) {
		put(HOST, host);
	}
	
	public String getHost() {
		return (String) get(HOST);
	}
	
	public void setPort(final Integer port) {
		put(PORT, port);
	}
	
	public Integer getPort() {
		return (Integer) get(PORT, new NodeContext.Go() {
			@Override
			public Object go(Context<String, Object> context) {
				return 8080;
			}
		});
	}
	
}
