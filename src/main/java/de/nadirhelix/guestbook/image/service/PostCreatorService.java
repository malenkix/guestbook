package de.nadirhelix.guestbook.image.service;

import de.nadirhelix.guestbook.image.dto.PostData;
import de.nadirhelix.guestbook.post.model.Post;

/**
 * 
 */
public interface PostCreatorService {
	
	/**
	 * Creates and stores an Image out of incoming {@link PostData} and returns the id for it.
	 * 
	 * @param data the given {@link PostData}
	 * @return the post id
	 */
	Post createImage(PostData data);
	
}
