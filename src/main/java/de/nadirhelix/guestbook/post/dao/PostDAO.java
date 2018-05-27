package de.nadirhelix.guestbook.post.dao;

import java.util.List;

import de.nadirhelix.guestbook.post.model.Post;

/**
 * DAO to store and receive Posts.
 * 
 * @author Phil
 */
public interface PostDAO {
	
	void storePost(Post post);
	
	List<String> getAllPostIds();
	
	List<Post> getAllActivePosts();
	
	void setPinned(String postId, boolean isActive);

	String getLatestId();
	
	

}
