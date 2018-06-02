package de.nadirhelix.guestbook.image.facade.impl;

import static de.nadirhelix.guestbook.image.PostConstants.POST_FILE_PATH;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import de.nadirhelix.guestbook.image.facade.ImageFacade;
import de.nadirhelix.guestbook.resources.service.ResourceService;

@Component("imageFacade")
public class ImageFacadeImpl implements ImageFacade {
	
	@Resource
	private ResourceService resourceService;

	@Override
	public byte[] getImage(String path) throws IOException {
		return resourceService.loadResource("classpath:assets/images/" + path);
	}

	@Override
	public byte[] getPostImage(String id) throws IOException {
		File f = new File(POST_FILE_PATH + id + ".png"); 
		return IOUtils.toByteArray(new FileInputStream(f));	
	}
}
