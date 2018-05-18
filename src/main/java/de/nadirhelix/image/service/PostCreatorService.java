package de.nadirhelix.image.service;

import de.nadirhelix.image.dto.PostData;

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
	String createImage(PostData data);
	
}
