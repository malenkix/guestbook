package de.nadirhelix.guestbook.post.dao;

import java.util.Collection;

import de.nadirhelix.guestbook.post.model.Post;

/**
 * DAO to store and receive Posts.
 * 
 * @author Phil
 */
public interface PostDao {
	
	void storePost(Post post);
	
	Collection<String> getAllPostIds();
	
	Collection<Post> getAllActivePosts();
	
	void setPinned(String postId, boolean isActive);

	String getLatestId();
	
}
