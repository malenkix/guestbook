package de.nadirhelix.guestbook.util;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathFinder {
	
	private static Logger logger = LoggerFactory.getLogger(PathFinder.class);

	public static Path getFilePath(URI uri) throws IOException {
		final Map<String, String> env = new HashMap<>();
		if (!StringUtils.contains(uri.toString(),'!')) {
			return Paths.get(uri);
		}
		logger.info("The uri to be adjusted: " + uri.toString());
		final String[] array = uri.toString().split("!");
		final FileSystem fs = FileSystems.newFileSystem(URI.create(array[0]), env);
		final Path path = fs.getPath(array[1]+array[2]);
		logger.info("Evaluated path is: " + path.toString());
		return path;
	}
}
