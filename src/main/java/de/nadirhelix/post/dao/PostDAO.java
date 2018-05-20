package de.nadirhelix.post.dao;

import java.util.List;

/**
 * DAO to store and receive Posts.
 * 
 * @author Phil
 */
public interface PostDAO {
	
	void storePost(String postId);
	
	List<String> getAllPostIds();
	
	List<String> getAllActivePosts();
	
	void setPostActive(String postId, boolean isActive);
	
	

}
