package de.nadirhelix.guestbook.post.strategy;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.nadirhelix.guestbook.image.dto.PostData;

/**
 * <p>This task is based on a {@link Thread} and contains a queue which holds the {@link PostData} to be 
 * transformed to posts.</p>
 * <p>The task itself waits for input and once received, the corresponding PostData will be passed to
 * the linked {@link PostCreationStrategy} to start creation process.</p>
 * 
 * @author Phil
 */
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
		} catch (Exception e) {
			// caught to keep Task alive
			LOG.warn(e.getMessage());
			LOG.debug("Post creation unfinished:", e);
		}
	}
	
	/**
	 * Adds a {@link PostData} to the queue.
	 * 
	 * @param post
	 * 			the {@link PostData}
	 */
	public void addPost(PostData post) {
		queue.add(post);
	}
	
	/**
	 * Sets the {@link PostCreationStrategy}.
	 * 
	 * @param postCreationStrategy
	 * 				the {@link PostCreationStrategy}
	 */
	public void setPostCreationStrategy(PostCreationStrategy postCreationStrategy) {
		this.postCreationStrategy = postCreationStrategy;
	}
	
}
