package de.nadirhelix.guestbook.post.service;

import de.nadirhelix.guestbook.post.model.Post;
import de.nadirhelix.guestbook.post.util.PinnedPosts;

/**
 * Interface that is responsible for providing Posts to the frontend.
 * 
 * @author Phil
 */
public interface BroadcastingService {

	/**
	 * Adds a {@link Post} to the broadcasting system.
	 * 
	 * @param post the {@link Post} to be added.
	 */
	void addPost(Post post);

	/**
	 * Retrieves all active posts that were added after the last update.
	 * 
	 * @param lastUpdate it id of the last update
	 * @return the {@link PinnedPosts}
	 */
	PinnedPosts getPinnedPosts(int lastUpdate);

	/**
	 * Sets the active state of a post.
	 * 
	 * @param postId the post id
	 * @param pinned the state
	 */
	void setPinned(String postId, boolean pinned);

}
