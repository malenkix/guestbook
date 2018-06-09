package de.nadirhelix.guestbook.pinwall.exception;

public class UnexpectedUpdateIdException extends IllegalStateException {
	
	private static final long serialVersionUID = 1L;

	private final int currentUpdateId;

	public UnexpectedUpdateIdException(int size) {
		currentUpdateId = size;
	}
	
	public int getCurrentUpdateId() {
		return currentUpdateId;
	}

	
}
