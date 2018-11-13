package de.nadirhelix.guestbook.resources.facade;

import java.io.IOException;

public interface ResourceFacade {

	byte[] getCss(String path, String fileName) throws IOException;

	byte[] getJs(String path, String fileName) throws IOException;

	byte[] getHtml(String string) throws IOException;

	byte[] getFont(String fileName) throws IOException;

}
