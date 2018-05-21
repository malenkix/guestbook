package de.nadirhelix.guestbook.image.facade;

import de.nadirhelix.guestbook.image.dto.PostData;

/**
 * Handles incoming {@link PostData}.
 * 
 * @author Phil
 */
public interface PostFacade {

	String addPost(PostData data);

	String uploadImage(Object payload);
	
}
