package org.iungo.http.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iungo.logger.api.ClassLogger;
import org.iungo.message.api.Message;
import org.iungo.message.api.MessageHandle;
import org.iungo.message.api.ReplyMessage;
import org.iungo.message.api.SendAndReceiveResultMessage;
import org.iungo.message.api.SendMessage;
import org.iungo.result.api.Result;

public abstract class AbstractHTTPServlet extends HttpServlet {

	private static final ClassLogger logger = new ClassLogger(AbstractHTTPServlet.class);
	
	private static final long serialVersionUID = 1L;

	static protected String getParameter(HttpServletRequest request, String name) {
		final List<String> v = (List<String>) getParameter(request, name, 0, Integer.MAX_VALUE);
		return (v == null ? null : (v.size() == 1 ? v.get(0) : null));
	}

	static protected List<String> getParameter(HttpServletRequest request, String name, Integer min) {
		return getParameter(request, name, min, Integer.MAX_VALUE);
	}
	
	static protected List<String> getParameter(HttpServletRequest request, String name, Integer min, Integer max) {
		List<String> values = Arrays.asList(request.getParameterValues(name));
		if (values.size() < min || values.size() > max) {
			logger.warn(String.format("Parameter [%s] returned [%s] values [%s]<=l<=[%s].", name, values.size(), min, max));
			values = null;
		}
		return values;
	}
	
	public static void responsePrint(final HttpServletResponse response, final String text) {
		try {
			response.getOutputStream().print(text);
		} catch (final Exception exception) {
			logger.error(Result.valueOf(exception).toString());
		}
	}

	static public void responseWrite(final HttpServletResponse response, byte[] v) {
		try {
			response.getOutputStream().write(v);
		} catch (final Exception exception) {
			logger.error(Result.valueOf(exception).toString());
		}
	}

	/**
	 * 400
	 */
	public static void badRequest(final HttpServletRequest request, final HttpServletResponse response, final String text) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.setContentType("text/html");
		responsePrint(response, text);
	}

	/**
	 * 405.
	 */
	public static void methodNotAllowed(final HttpServletRequest request, final HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		response.setContentType("text/html");
		responsePrint(response, request.getMethod());
	}

	/**
	 * 500.
	 */
	public static void internalServerError(final HttpServletResponse response) {
		internalServerError(response, "Oops...");
	}

	public static void internalServerError(final HttpServletResponse response, final String text) {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.setContentType(MIME.TEXT_HTML);
		responsePrint(response, text);
	}

	protected final HTTPServletContext httpServletContext;
	
	private final SendMessage sendMessage;
	
	public AbstractHTTPServlet(final HTTPServletContext httpServletContext) {
		super();
		this.httpServletContext = httpServletContext;
		sendMessage = httpServletContext.getServletSendMessage();
	}

	public String getPath() {
		return httpServletContext.getServletPath();
	}

	/**
	 * Create the Message 
	 * @param asyncContext
	 * @return
	 */
	abstract protected Result createMessage(final AsyncContext asyncContext);
	
	abstract protected Result createMessageHandle(final Message message);
	
	/**
	 * Send a Message from the parent ID to the to ID.
	 * 
	 * This will put the request into asynchronous mode. 
	 */
	protected void sendMessage(final HttpServletRequest request, final HttpServletResponse response) {
		/*
		 * Put the Request into asynchronous mode.
		 */
		final AsyncContext asyncContext = request.startAsync(request, response);
		try {
			/*
			 * Relay the message to be handled.
			 */
			final SendAndReceiveResultMessage sendAndReceiveResultMessage = null;
			Result result = createMessage(asyncContext);
			if (result.isTrue()) {
				final Message message = (Message) result.getValue();
				result = createMessageHandle(message);
				final MessageHandle messageHandle = (MessageHandle) result.getValue();
				result = sendAndReceiveResultMessage.sendAndReceiveResult(message, messageHandle);
				if (result.isTrue()) {
					/*
					 * Yippee...
					 */
				} else {
					/*
					 * Return a 500.
					 */
					internalServerError((HttpServletResponse) asyncContext.getResponse(), String.format("Failed to send message"));
					asyncContext.complete();
				}
			}
		} catch (final Exception exception) {
			/*
			 * We died...
			 */
			internalServerError((HttpServletResponse) asyncContext.getResponse());
			asyncContext.complete();
		}
	}
	
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		methodNotAllowed(request, response);
	}

	@Override
	protected void doHead(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		methodNotAllowed(request, response);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		methodNotAllowed(request, response);
	}

	@Override
	protected void doPut(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		methodNotAllowed(request, response);
	}

	@Override
	protected void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		methodNotAllowed(request, response);
	}

	@Override
	protected void doOptions(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		methodNotAllowed(request, response);
	}

	@Override
	protected void doTrace(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		methodNotAllowed(request, response);
	}

	@Override
	public String toString() {
		return String.format("%s\nPath [%s]", getClass().getName(), getPath());
	}
}
