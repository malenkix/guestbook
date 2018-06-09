package de.nadirhelix.guestbook.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.nadirhelix.guestbook.resources.facade.ResourceFacade;

/**
 * This Controller handles all requests for certain resources such as .js-files or .css-files.
 * 
 * @author Phil
 */
@RestController
@RequestMapping("/")
public class ResourceController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ResourceController.class); 

	@Resource
	private ResourceFacade resourceFacade;
	
	/**
	 * Retrieves a css file based on path and fileName.
	 * 
	 * @param path 
	 * 			the path where to find the file
	 * @param fileName
	 * 			the name of the requested file
	 * @return {@link ResponseEntity} containing byte[] 
	 */
	@GetMapping("{path:.+}/css/{fileName:.+}")
	public ResponseEntity<byte[]> getCss(@PathVariable("path") String path, @PathVariable("fileName") String fileName) {
		String targetPath = normalizePath(path);
		try {
			return ResponseEntity.ok().body(resourceFacade.getCss(targetPath, fileName));
		} catch (IOException e) {
			LOG.debug(String.format("Could not find js file %s in path: %s", fileName, path), e);
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Retrieves a js file based on path and fileName.
	 * 
	 * @param path 
	 * 			the path where to find the file
	 * @param fileName
	 * 			the name of the requested file
	 * @return {@link ResponseEntity} containing byte[] 
	 */
	@GetMapping("{path:.+}/js/{fileName:.+}")
	public ResponseEntity<byte[]> getJs(@PathVariable("path") String path, @PathVariable("fileName") String fileName) {
		String targetPath = normalizePath(path);
		try {
			return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(resourceFacade.getJs(targetPath, fileName));
		} catch (IOException e) {
			LOG.debug(String.format("Could not find js file %s in path: %s", fileName, path), e);
		}
		return ResponseEntity.notFound().build();
	}
	
	private String normalizePath(String path) {
		if ("shared".equals(path)) {
			return path;
		}
		return "apps/" + path;
	}
}
