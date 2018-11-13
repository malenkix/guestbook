package de.nadirhelix.guestbook.resources.facade.impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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

	@Override
	public byte[] getHtml(String target) throws IOException {
		return resourceService.loadResource(buildFullPath("apps", target, "index.html"));
	}

	@Override
	public byte[] getFont(String fileName) throws IOException {
		return resourceService.loadResource(buildFullPath(StringUtils.EMPTY, "fonts", fileName));
	}

	private String buildFullPath(String path, String type, String fileName) {
		StringBuilder sb = new StringBuilder();
		sb.append("classpath:static/");
		if (StringUtils.isNotBlank(path)) {
			sb.append(path).append('/');
		}
		sb.append(type);
		sb.append('/').append(fileName);
		return sb.toString();
	}
	
}
