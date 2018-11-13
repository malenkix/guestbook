package de.nadirhelix.guestbook.post.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all updated posts that are pinned at the pinwall. Including the id of the latest update.
 * 
 * @author Phil
 */
public class PinnedPosts {

	private int updateId;
	
	private List<PinnedPost> pinnedPosts = new ArrayList<>();
	
	public List<PinnedPost> getPinnedPosts() {
		return pinnedPosts;
	}
	
	public int getUpdateId() {
		return updateId;
	}
	
	public void setUpdateId(int updateId) {
		this.updateId = updateId;
	}
	
	public void addPost(PinnedPost post) {
		pinnedPosts.add(post);
	}

}
