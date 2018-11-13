package de.nadirhelix.guestbook.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.nadirhelix.guestbook.image.facade.ImageFacade;

/**
 * This Controller handles all requests for certain images.
 * 
 * @author Phil
 */
@RestController
public class ImageController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ImageController.class); 

	@Resource
	private ImageFacade imageFacade;
	
	/**
	 * Retrieves a post image for a given id.
	 * 
	 * @param id
	 * 			the id of the post
	 * @return a {@link ResponseEntity} of byte[]
	 */
	@GetMapping("/posts/{id:.+}")
	public ResponseEntity<byte[]> getPostImage(@PathVariable("id") String id) {
		try {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageFacade.getPostImage(id));
		} catch (IOException e) {
			LOG.warn("Could not find post with id {}", id);
			LOG.debug("Following Exception was caught:",e);
		}
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Retrieves a background image for a given id.
	 * 
	 * @param id
	 * 			the background id
	 * @return a {@link ResponseEntity} of byte[]
	 */
	@GetMapping("/backgrounds/{id:.+}")
	public ResponseEntity<byte[]> getBackgroundImage(@PathVariable("id") String id) {
		return getImage("backgrounds/" + id);
	}
	
	/**
	 * <p>Retrieves an image for a given fileName.</p>
	 * Allowed file extensions:
	 * <ul>
	 * <li>.jpg</li>
	 * <li>.png</li>
	 * <li>.svg</li>
	 * </ul>
	 * 
	 * @param fileName
	 * 			the name of the image file
	 * @return a {@link ResponseEntity} of byte[]
	 */
	@GetMapping("/assets/{fileName:.+}")
	public ResponseEntity<byte[]> getAsset(@PathVariable("fileName") String fileName) {
		return getImage(fileName);
	}

	private ResponseEntity<byte[]> getImage(String id) {
		byte[] image;
		try {
			image = imageFacade.getImage(id);
			MediaType mediaType = fetchType(id);
			if (mediaType != null) {
				return ResponseEntity.ok().contentType(mediaType).body(image);
			}
			LOG.debug("Unknown MediaType at Filename: {}", id);
		} catch (IOException e) {
			LOG.error("Error during getImage:", e);
		}
		return ResponseEntity.notFound().build();
	}

	private MediaType fetchType(String id) {
		String suffix = id.substring(id.lastIndexOf("."));
		switch (suffix) {
		case ".png" : return MediaType.IMAGE_PNG;
		case ".jpg" : return MediaType.IMAGE_JPEG;
		case ".svg" : return MediaType.parseMediaType("image/svg+xml");
		}
		return null;
	}
}
