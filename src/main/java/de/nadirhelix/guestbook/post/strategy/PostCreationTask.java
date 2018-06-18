package de.nadirhelix.guestbook.post.strategy;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.nadirhelix.guestbook.image.dto.PostData;
import de.nadirhelix.guestbook.post.exception.PostCreationException;

public class PostCreationTask extends Thread {
	
	private static final Logger LOG = LoggerFactory.getLogger(PostCreationTask.class);
	
	private Queue<PostData> queue = new ConcurrentLinkedQueue<>();
	
	private PostCreationStrategy postCreationStrategy;
	
	
	@Override
	public void run() {
		while(isAlive()) {
			waitForPosts();
			createPost(queue.poll());
		}
	}

	private void waitForPosts() {
		while(queue.isEmpty()) {
			synchronized (this) {
				try {
					wait(1000);
				} catch (InterruptedException e) {
					LOG.warn("Wait mechanism was interrupted.");
				}
			}
		}
	}

	private void createPost(PostData data) {
		try {
			postCreationStrategy.createPost(data);
		} catch (PostCreationException e) {
			LOG.warn(e.getMessage());
			LOG.debug("Post creation unfinished:", e);
		}
	}
	
	public void addPost(PostData post) {
		queue.add(post);
	}
	
	public void setPostCreationStrategy(PostCreationStrategy postCreationStrategy) {
		this.postCreationStrategy = postCreationStrategy;
	}
	
}
