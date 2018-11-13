package de.nadirhelix.guestbook.resources.service;

import java.io.IOException;

public interface ResourceService {
	
	byte[] loadResource(String fullPath) throws IOException;

}
