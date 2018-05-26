package de.nadirhelix.guestbook.post.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.nadirhelix.guestbook.post.dao.PostDAO;
import de.nadirhelix.guestbook.post.model.Post;
/**
 * Default implementation of {@link PostDAO}.
 * This DAO does only deal with in memory data.
 * 
 * @author Phil
 */
@Service("postDao")
public class DefaultPostDAO implements PostDAO {
	
	List<Post> posts = new ArrayList<>(); 

	@Override
	public void storePost(Post post) {
		posts.add(post);		
	}

	@Override
	public List<String> getAllPostIds() {
		return posts.stream().map(Post::getId).collect(Collectors.toList());
	}

	@Override
	public List<Post> getAllActivePosts() {
		return posts.stream().filter(Post::isPinned).collect(Collectors.toList());
	}

	@Override
	public void setPinned(String postId, boolean isActive) {
		posts.stream().filter(p -> p.getId().equals(postId))
				.findFirst().ifPresent(p -> p.setPinned(isActive));
	}
}
