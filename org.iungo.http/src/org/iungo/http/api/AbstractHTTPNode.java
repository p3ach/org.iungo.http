package org.iungo.http.api;

import org.iungo.message.api.AbstractMessageHandle;
import org.iungo.message.api.Message;
import org.iungo.node.api.AbstractNode;
import org.iungo.node.api.NodeContext;
import org.iungo.node.api.NodeUtils;
import org.iungo.result.api.Result;

public abstract class AbstractHTTPNode extends AbstractNode implements HTTPNode {

	public AbstractHTTPNode(final NodeContext nodeContext) {
		super(nodeContext);
		
		rootMessageLoop.getReceiveMessageLoopQueue().getMessageHandles().add(ADD_SERVLET_KEY, new AbstractMessageHandle() {
			@Override
			public Result go(final Message message) {
				final AddHTTPServletMessage addHTTPServletMessage = (AddHTTPServletMessage) message;
				final AddHTTPServletContext addHTTPServletContext = addHTTPServletMessage.getAddHTTPServletContext();
				Result result = NodeUtils.createObject(addHTTPServletContext.getServletClassName(), new Class<?>[]{HTTPServletContext.class}, new Object[]{addHTTPServletContext});
				if (result.isTrue()) {
					result = addServlet((AbstractHTTPServlet) result.getValue()); 
				}
				return result;
			}
		});
		
		rootMessageLoop.getReceiveMessageLoopQueue().getMessageHandles().add(REMOVE_SERVLET_KEY, new AbstractMessageHandle() {
			@Override
			public Result go(final Message message) {
				return Result.TRUE;
			}
		});
	}
	
	public Result addServlet(final AbstractHTTPServlet httpServlet) {
		return Result.FALSE;
	}

	public Result removeServlet(final AbstractHTTPServlet httpServlet) {
		return Result.FALSE;
	}
}
