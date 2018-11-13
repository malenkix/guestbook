package de.nadirhelix.guestbook.post.util;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import de.nadirhelix.guestbook.post.dao.PostDao;

/**
 * This class returns an id for posts considering that the id must not be used already.
 * 
 * @author Phil
 */
@Component("postIdGenerator")
public class PostIdGenerator {
	
	private static final Logger LOG = LoggerFactory.getLogger(PostIdGenerator.class);
	
	private static final Long PLACE_HOLDER = -1l;

	private Long lastId = PLACE_HOLDER;
	
	@Resource
	private PostDao postDao;
	
	/**
	 * <p>Returns an id which is latest known id + 1.</p>
	 * <p>If no id is actually given a randomized start id will be chosen.</p>
	 * 
	 * @return the next id as Long
	 */
	public Long generateId() {
		synchronized (lastId) {
			if (lastId.equals(PLACE_HOLDER)) {
				long initialId = -1;
				String latest = postDao.getLatestId();
				try {
					initialId = Long.parseLong(latest);
				} catch (NumberFormatException e) {
					LOG.warn("{} is not a valid number or is not parseable as Long. Will create new initial id.", latest);
				}
				lastId = initialId;
			}
			long newId = lastId + 1;
			return setLastId(newId);
		}
	}
	
	private Long setLastId(long newLastId) {
		if (newLastId <= 0) {
			lastId = 85679810 + (long)(Math.random() * 1235);
		} else {
			lastId = newLastId;
		}
		return lastId;
	}
	
}
