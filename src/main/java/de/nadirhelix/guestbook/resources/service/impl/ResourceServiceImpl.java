package de.nadirhelix.guestbook.resources.service.impl;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import de.nadirhelix.guestbook.resources.service.ResourceService;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ResourceService.class);
	
	private ResourceLoader resourceLoader;

    @Autowired
    public ResourceServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

	@Override
	public byte[] loadResource(String fullPath) throws IOException {
		Resource resource = resourceLoader.getResource(fullPath);
		Path p = getFilePath(resource.getURI());
		return Files.readAllBytes(p);
	}

	public static Path getFilePath(URI uri) throws IOException {
		final Map<String, String> env = new HashMap<>();
		if (!StringUtils.contains(uri.toString(),'!')) {
			return Paths.get(uri);
		}
		LOG.info("The uri to be adjusted: {}", uri.toString());
		final String[] array = uri.toString().split("!");
		FileSystem fs;
		try { 
			fs = FileSystems.getFileSystem(URI.create(array[0]));
		} catch (FileSystemNotFoundException e) {
			LOG.info("Didn't find Filesystem for URI {}. Will create one.", array[0]);
			fs = FileSystems.newFileSystem(URI.create(array[0]), env);
		}
		final Path path = fs.getPath(array[1] + array[2]);
		LOG.info("Evaluated path is: {}", path.toString());
		return path;
	}

}
