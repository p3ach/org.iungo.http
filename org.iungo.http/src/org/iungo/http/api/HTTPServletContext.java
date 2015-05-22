package org.iungo.http.api;

import org.iungo.context.api.SimpleContext;
import org.iungo.id.api.ID;
import org.iungo.message.api.SendMessage;

public class HTTPServletContext extends SimpleContext {

	private static final long serialVersionUID = 1;
	
	public static final String SERVLET_CLASS_NAME_KEY = new ID(HTTPServletContext.class.getSimpleName(), "Servlet", "ClassName").toString();
	
	public static final String SERVLET_PATH_KEY = new ID(HTTPServletContext.class.getSimpleName(), "Servlet", "Path").toString();

	public static final String SERVLET_VERB_GET_KEY = new ID(HTTPServletContext.class.getSimpleName(), "Servlet/Verb", "Get").toString();

	public static final String SERVLET_VERB_POST_KEY = new ID(HTTPServletContext.class.getSimpleName(), "Servlet/Verb", "Post").toString();

	public static final String SERVLET_SEND_MESSAGE_KEY = new ID(HTTPServletContext.class.getSimpleName(), null, "SendMessage").toString();
	
	public HTTPServletContext() {
		super();
	}

	public HTTPServletContext(final SimpleContext simpleContext) {
		super(simpleContext);
	}

	public String getServletClassName() {
		return (String) get(SERVLET_CLASS_NAME_KEY);
	}
	
	public void setServletClassName(final String value) {
		put(SERVLET_CLASS_NAME_KEY, value);
	}
	
	public String getServletPath() {
		return (String) get(SERVLET_PATH_KEY);
	}
	
	public void setServletPath(final String path) {
		put(SERVLET_PATH_KEY, path);
	}
	
	public Boolean getServletVerbGet() {
		return (Boolean) get(SERVLET_VERB_GET_KEY);
	}
	
	public Boolean getServletVerbPost() {
		return (Boolean) get(SERVLET_VERB_POST_KEY);
	}
	
	public SendMessage getServletSendMessage() {
		return (SendMessage) get(SERVLET_SEND_MESSAGE_KEY);
	}
	
	public void setServletSendMessage(final SendMessage sendMessage) {
		put(SERVLET_SEND_MESSAGE_KEY, sendMessage);
	}
}
