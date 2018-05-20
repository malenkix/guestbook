package de.nadirhelix.image.facade.impl;

import javax.annotation.Resource;

import de.nadirhelix.image.dto.PostData;
import de.nadirhelix.image.facade.PostFacade;
import de.nadirhelix.image.service.PostCreatorService;
import de.nadirhelix.post.service.BroadcastingService;

/**
 * Default implementation of {@link PostFacade}.
 * 
 * @author Phil
 *
 */
public class PostFacadeImpl implements PostFacade {
	
	@Resource
	private PostCreatorService postCreatorService;
	
	@Resource
	private BroadcastingService broadcastingService;

	/**
	 * Adds a post for given {@link PostData} to the broadcasting system.
	 */
	@Override
	public void addPost(PostData data) {
		String postId = postCreatorService.createImage(data);
		broadcastingService.addPost(postId);
	}

}
