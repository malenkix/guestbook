package de.nadirhelix.guestbook;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.nadirhelix.guestbook.image.dto.PostData;
import de.nadirhelix.guestbook.image.facade.ImageFacade;

/**
 * This Controller handles all requests coming from the editor.
 * 
 * @author Phil
 */
@RestController
@RequestMapping("/images")
public class ImageController {

	@Resource
	private ImageFacade imageFacade;
	
	/**
	 * Handles Post creation out of given PostData.
	 * 
	 * @param post
	 * 			the {@link PostData} from request
	 * @return {@link ResponseEntity}
	 * @throws IOException 
	 */
	@GetMapping("/posts/{id:.+}")
	public ResponseEntity<byte[]> getPostImage(@PathVariable("id") String id) {
		try {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageFacade.getPostImage(id));
		} catch (IOException e) {
			// TODO generate log here
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/backgrounds/{id:.+}")
	public ResponseEntity<byte[]> getBackgroundImage(@PathVariable("id") String id) {
		return getImage("backgrounds/" + id);
	}
	
	@GetMapping("/assets/{id:.+}")
	public ResponseEntity<byte[]> getAsset(@PathVariable("id") String id) {
		return getImage(id);
	}

	private ResponseEntity<byte[]> getImage(String id) {
		byte[] image;
		try {
			image = imageFacade.getImage(id);
		MediaType mediaType = fetchType(id);
		if (mediaType != null) {
			return ResponseEntity.ok().contentType(mediaType).body(image);
		}
		//TODO log unknown mediatype
		} catch (IOException e) {
			// TODO create valid logging here
			e.printStackTrace();
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
