package de.nadirhelix.guestbook.post.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import de.nadirhelix.guestbook.post.dao.PostDao;
import de.nadirhelix.guestbook.post.model.Post;
import de.nadirhelix.guestbook.post.service.BroadcastingService;

@Service("broadcastingService")
public class BroadcastingServiceImpl implements BroadcastingService {
	
	@Resource
	private PostDao postDao;

	@Override
	public void addPost(Post post) {
		postDao.storePost(post);
	}

}
