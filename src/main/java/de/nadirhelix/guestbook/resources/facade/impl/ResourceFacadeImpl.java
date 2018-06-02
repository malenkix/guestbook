package de.nadirhelix.guestbook.resources.facade.impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.nadirhelix.guestbook.resources.facade.ResourceFacade;
import de.nadirhelix.guestbook.resources.service.ResourceService;

@Component("resourceFacade")
public class ResourceFacadeImpl implements ResourceFacade {
	
	@Resource
	private ResourceService resourceService;

	@Override
	public byte[] getCss(String path, String fileName) throws IOException {
		return resourceService.loadResource(buildFullPath(path, "css", fileName));
	}

	@Override
	public byte[] getJs(String path, String fileName) throws IOException {
		return resourceService.loadResource(buildFullPath(path, "js", fileName));
	}

	private String buildFullPath(String path, String type, String fileName) {
		StringBuilder sb = new StringBuilder();
		sb.append("classpath:static/").append(path);
		sb.append('/').append(type);
		sb.append('/').append(fileName);
		return sb.toString();
	}
	
}
