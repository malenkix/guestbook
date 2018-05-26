package de.nadirhelix.guestbook;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<String> uploadImage(Object payload) {
		String fileName = postFacade.uploadImage(payload);
		return ResponseEntity.status(HttpStatus.OK.value()).body(fileName);
	}
}
