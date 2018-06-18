package de.nadirhelix.guestbook.post.exception;

public class PostCreationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private final String messageFormat;
	
	private final Object[] args;
	
	private String message;

	public PostCreationException(Throwable cause, String messageFormat, Object... args) {
		super(cause);
		this.messageFormat = messageFormat;
		this.args = args;
	}
	
	@Override
	public String getMessage() {
		return message != null ? message :
			generateMessage();		
	}

	private String generateMessage() {
		message = String.format(messageFormat, args);
		return message;
	}
	

	
}
