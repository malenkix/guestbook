package de.nadirhelix.guestbook.image.facade;

import java.io.IOException;

public interface ImageFacade {

	byte[] getImage(String id) throws IOException;

	byte[] getPostImage(String path) throws IOException;

}
