package de.nadirhelix.guestbook.post.util;

import javax.annotation.Resource;

import de.nadirhelix.guestbook.post.dao.PostDAO;

public class PostIDGenerator {

	private static PostIDGenerator instance = new PostIDGenerator();
	private Long lastId;
	
	@Resource
	private PostDAO postDao;
	
	private PostIDGenerator() {
		//
	}
	
	public static String generateId() {
		synchronized (instance) {
			if (instance.getLastId() == null) {
				long initialId = -1;
				try {
					initialId = Long.parseLong(instance.getPostDao().getLatestId());
				} catch (NumberFormatException e) {
					// Log wrong id!
				}
				instance.setLastId(initialId);
			}
			long newId = instance.getLastId() + 1;
			return instance.setLastId(newId);
		}
	}
	
	private String setLastId(long newLastId) {
		if (newLastId < 0) {
			lastId = 85679810 + (long)(Math.random() * 1235);
		}
		lastId = newLastId;
		return Long.toString(lastId);
	}
	
	private Long getLastId() {
		return lastId;
	}
	
	private PostDAO getPostDao() {
		return postDao;
	}
	
}
