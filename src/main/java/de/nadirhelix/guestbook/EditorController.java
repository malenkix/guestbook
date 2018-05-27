package de.nadirhelix.guestbook;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import de.nadirhelix.guestbook.image.dto.PostData;
import de.nadirhelix.guestbook.image.facade.PostFacade;

/**
 * This Controller handles all requests coming from the editor.
 * 
 * @author Phil
 */
@RestController
@RequestMapping("/")
public class EditorController {

	@Resource
	private PostFacade postFacade;
	
	/**
	 * Handles Post creation out of given PostData.
	 * 
	 * @param post
	 * 			the {@link PostData} from request
	 * @return {@link ResponseEntity}
	 */
	@PostMapping("/posts")
	public ResponseEntity<String> addPost(@RequestBody PostData post) {
		post.setDate(new Date());
		String postId = postFacade.addPost(post);
		// TODO: Send message instead of id.
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(postId);
	}
	
	// TODO ...
	@PostMapping("/posts/upload")
	public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile multipartFile) {
		String fileName;
		try {
			fileName = postFacade.uploadImage(multipartFile.getBytes(), multipartFile.getOriginalFilename());
			return ResponseEntity.ok().body(fileName);
		} catch (IOException e) {
			// TODO log
			e.printStackTrace();
		}
		return ResponseEntity.unprocessableEntity().body("Unable to upload image");
	}
	
}
