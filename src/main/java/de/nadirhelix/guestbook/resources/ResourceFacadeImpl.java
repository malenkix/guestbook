package de.nadirhelix.guestbook.resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import de.nadirhelix.guestbook.util.PathFinder;

@Component("resourceFacade")
public class ResourceFacadeImpl implements ResourceFacade {
	
	private ResourceLoader resourceLoader;

    @Autowired
    public ResourceFacadeImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

	@Override
	public byte[] getCSS(String path, String fileName) throws IOException {
		Resource resource = resourceLoader.getResource("classpath:static/" + path + "/css/" + fileName);
		Path p = PathFinder.getFilePath(resource.getURI());
		return Files.readAllBytes(p);
	}

	@Override
	public byte[] getJS(String path, String fileName) throws IOException {
		Resource resource = resourceLoader.getResource("classpath:static/" + path + "/js/" + fileName);
		Path p = PathFinder.getFilePath(resource.getURI());
		return Files.readAllBytes(p);
	}

}
