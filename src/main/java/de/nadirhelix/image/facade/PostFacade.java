package de.nadirhelix.image.facade;

import de.nadirhelix.image.dto.PostData;

/**
 * Handles incoming {@link PostData}.
 * 
 * @author Phil
 */
public interface PostFacade {

	void addPost(PostData data);
	
}
