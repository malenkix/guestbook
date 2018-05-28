package de.nadirhelix.guestbook.resources;

import java.io.IOException;

public interface ResourceFacade {

	byte[] getCSS(String path, String fileName) throws IOException;

	byte[] getJS(String path, String fileName) throws IOException;

}
