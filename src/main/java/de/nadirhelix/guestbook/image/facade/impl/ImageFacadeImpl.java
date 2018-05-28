package de.nadirhelix.guestbook.image.facade.impl;

import static de.nadirhelix.guestbook.image.PostConstants.POST_FILE_PATH;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import de.nadirhelix.guestbook.image.facade.ImageFacade;
import de.nadirhelix.guestbook.util.PathFinder;

@Component("imageFacade")
public class ImageFacadeImpl implements ImageFacade {
	
	private ResourceLoader resourceLoader;

    @Autowired
    public ImageFacadeImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
	@Override
	public byte[] getImage(String path) throws IOException {
		Resource resource = resourceLoader.getResource("classpath:assets/images/" + path);
		Path p = PathFinder.getFilePath(resource.getURI());
		return Files.readAllBytes(p);
	}

	@Override
	public byte[] getPostImage(String id) throws IOException {
		File f = new File(POST_FILE_PATH + id + ".png"); 
		return IOUtils.toByteArray(new FileInputStream(f));	
	}
}
