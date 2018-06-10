package de.nadirhelix.guestbook.post.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import de.nadirhelix.guestbook.post.dao.PostDao;
import de.nadirhelix.guestbook.post.model.Post;
/**
 * Default implementation of {@link PostDao}.
 * This DAO does only deal with in memory data.
 * 
 * @author Phil
 */
public class DefaultPostDao implements PostDao {
	
	private ConcurrentHashMap<String, Post> posts = new ConcurrentHashMap<>(); 

	@Override
	public void storePost(Post post) {
		posts.put(post.getId(), post);			
	}

	@Override
	public Collection<String> getAllPostIds() {
		return posts.keySet();
	}

	@Override
	public Collection<Post> getAllActivePosts() {
		return posts.entrySet().stream().map(Entry::getValue).filter(Post::isPinned).collect(Collectors.toList());
	}

	@Override
	public void setPinned(String postId, boolean isActive) {
		posts.get(postId).setPinned(isActive);
	}
	
	@Override
	public String getLatestId() {
		if (CollectionUtils.isEmpty(getAllActivePosts())) {
			return "-1";
		}
		List<String> sortedIds = getAllPostIds().stream().sorted(String::compareTo).collect(Collectors.toList());
		return sortedIds.get(sortedIds.size() -1);
		
	}
}
