package de.nadirhelix.guestbook.image.facade;

import java.io.IOException;

import de.nadirhelix.guestbook.image.dto.PostData;

/**
 * Handles incoming {@link PostData}.
 * 
 * @author Phil
 */
public interface PostFacade {

	String addPost(PostData data);

	String uploadImage(byte[] payload, String originalFileName) throws IOException;
	
}
