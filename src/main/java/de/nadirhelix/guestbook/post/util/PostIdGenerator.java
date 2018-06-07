package de.nadirhelix.guestbook.post.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.nadirhelix.guestbook.post.dao.PostDao;

@Component("postIdGenerator")
public class PostIdGenerator {

	private Long lastId = -1l;
	
	@Resource
	private PostDao postDao;
	
	public String generateId() {
		synchronized (lastId) {
			if (lastId.equals(-1l)) {
				long initialId = -1;
				try {
					initialId = Long.parseLong(postDao.getLatestId());
				} catch (NumberFormatException e) {
					// Log wrong id!
					
				}
				lastId = initialId;
			}
			long newId = lastId + 1;
			return setLastId(newId);
		}
	}
	
	private String setLastId(long newLastId) {
		if (newLastId <= 0) {
			lastId = 85679810 + (long)(Math.random() * 1235);
		} else {
			lastId = newLastId;
		}
		return Long.toString(lastId);
	}
	
}
