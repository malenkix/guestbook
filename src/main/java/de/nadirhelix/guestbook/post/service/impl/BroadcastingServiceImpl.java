package de.nadirhelix.guestbook.post.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import de.nadirhelix.guestbook.pinwall.PinwallPosition;
import de.nadirhelix.guestbook.pinwall.PinwallPositioningStrategy;
import de.nadirhelix.guestbook.pinwall.PinwallUpdateStrategy;
import de.nadirhelix.guestbook.post.dao.PostDao;
import de.nadirhelix.guestbook.post.model.Post;
import de.nadirhelix.guestbook.post.service.BroadcastingService;
import de.nadirhelix.guestbook.post.util.PinnedPost;
import de.nadirhelix.guestbook.post.util.PinnedPosts;

/**
 * Default implementation of the {@link BroadcastingService}.
 * 
 * @author Phil
 */
@Service("broadcastingService")
public class BroadcastingServiceImpl implements BroadcastingService {

	@Resource
	private PostDao postDao;

	@Override
	public void addPost(Post post) {
		postDao.storePost(post);
		int pinId = PinwallPositioningStrategy.pin(post.getId());
		PinwallUpdateStrategy.update(pinId, post.getId());
	}

	@Override
	public PinnedPosts getPinnedPosts(int lastUpdate) {
		PinnedPosts result = new PinnedPosts();
		result.setUpdateId(PinwallUpdateStrategy.getLastUpdateId());

		Map<Integer, String> recentUpdates = PinwallUpdateStrategy.getUpdate(lastUpdate);
		recentUpdates.entrySet().stream().forEach(e -> addPinnedPost(result, e));

		return result;
	}

	private void addPinnedPost(PinnedPosts result, Entry<Integer, String> e) {
		PinnedPost post = new PinnedPost();
		if (StringUtils.isEmpty(e.getValue())) {
			post.setPosition(PinwallPosition.empty(e.getKey()));
		} else {
			populate(post, e.getValue());
		}
		result.addPost(post);
	}

	private void populate(PinnedPost post, String postId) {
		post.setPostId(postId);
		post.setPosition(PinwallPositioningStrategy.getPosition(postId));
	}

	@Override
	public void setPinned(String postId, boolean pinned) {
		postDao.setPinned(postId, pinned);
		int index;
		if (pinned) {
			index = PinwallPositioningStrategy.pin(postId);
			PinwallUpdateStrategy.update(index, postId);
		} else {
			index = PinwallPositioningStrategy.unpin(postId);
			repin(index);
		}
	}

	private void repin(int index) {
		if (postDao.getAllActivePosts().size() > PinwallPositioningStrategy.pinnedPosts()) {
			pinYoungestUnpinnedPost();
		} else {
			PinwallUpdateStrategy.update(index, "");
		}
	}

	private void pinYoungestUnpinnedPost() {
		List<String> activePostsIds = postDao.getAllActivePosts().stream().map(Post::getId)
				.collect(Collectors.toList());
		activePostsIds.removeAll(PinwallPositioningStrategy.getPinnedPosts());

		if (!CollectionUtils.isEmpty(activePostsIds)) {
			Collections.reverse(activePostsIds);
			String candidate = activePostsIds.get(0);
			int index = PinwallPositioningStrategy.pin(candidate);

			PinwallUpdateStrategy.update(index, candidate);
		}
	}

}
