package de.nadirhelix.guestbook.image.facade.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.nadirhelix.guestbook.image.dto.PostData;
import de.nadirhelix.guestbook.image.facade.PostFacade;
import de.nadirhelix.guestbook.image.service.PostCreatorService;
import de.nadirhelix.guestbook.post.model.Post;
import de.nadirhelix.guestbook.post.service.BroadcastingService;

/**
 * Default implementation of {@link PostFacade}.
 * 
 * @author Phil
 *
 */
@Component("postFacade")
public class PostFacadeImpl implements PostFacade {
	
	@Resource
	private PostCreatorService postCreatorService;
	
	@Resource
	private BroadcastingService broadcastingService;

	/**
	 * Adds a post for given {@link PostData} to the broadcasting system.
	 */
	@Override
	public String addPost(PostData data) {
		Post post = postCreatorService.createImage(data);
		broadcastingService.addPost(post);
		
		return post.getId();
	}

	@Override
	public String uploadImage(Object payload) {
		// TODO Auto-generated method stub
		String fileName = "";
		return fileName;
	}

}
