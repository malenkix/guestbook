package de.nadirhelix.post.service.impl;

import javax.annotation.Resource;

import de.nadirhelix.post.dao.PostDAO;
import de.nadirhelix.post.service.BroadcastingService;

public class BroadcastingServiceImpl implements BroadcastingService {
	
	@Resource
	private PostDAO postDao;

	@Override
	public void addPost(String postId) {
		postDao.storePost(postId);
	}

}
