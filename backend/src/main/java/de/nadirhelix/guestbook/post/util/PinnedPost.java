package de.nadirhelix.guestbook.post.util;

import de.nadirhelix.guestbook.pinwall.PinwallPosition;

/**
 * Contains the id of a Post and its position at the pinwall.
 * This is used for the pinwall update.
 * 
 * @author Phil
 */
public class PinnedPost {

	private String postId;
	
	private PinwallPosition position;

	public String getPostId() {
		return postId;
	}

	public PinwallPosition getPosition() {
		return position;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public void setPosition(PinwallPosition position) {
		this.position = position;
	}
	
}
