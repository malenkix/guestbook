package de.nadirhelix.guestbook.post.strategy;

import static de.nadirhelix.guestbook.PostConstants.ARCHIVE_IMAGE_PATH;
import static de.nadirhelix.guestbook.PostConstants.TEMP_IMAGE_PATH;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import de.nadirhelix.guestbook.image.dto.PostData;
import de.nadirhelix.guestbook.image.service.PostCreationService;
import de.nadirhelix.guestbook.post.model.Post;
import de.nadirhelix.guestbook.post.service.BroadcastingService;
import de.nadirhelix.guestbook.post.util.PostUtil;

/**
 * This Strategy takes care of post creation. It feeds a task with new post data and handles 
 * creation by postCreationService. 
 * 
 * @author Phil
 */
@Component
public class PostCreationStrategy {
	
	private static final Logger LOG  = LoggerFactory.getLogger(PostCreationStrategy.class);
	
	private final PostCreationTask task;

	@Resource
	private BroadcastingService broadcastingService;
	
	@Resource
	private PostCreationService postCreationService;	
	
	/**
	 * Default constructor. Initializes a {@link PostCreationTask}.
	 */
	public PostCreationStrategy() {
		task = new PostCreationTask();
		task.setPostCreationStrategy(this);
		task.start();
	}
	
	/**
	 * Add a post in the task queue.
	 * 
	 * @param post
	 * 			the {@link PostData} to be added
	 */
	public void addPost(PostData post) {
		task.addPost(post);
	}
	
	/**
	 * Triggers creation and broadcasting process.
	 * 
	 * @param data
	 * 				the {@link PostData}
	 */
	public void createPost(PostData data) {
		Post post = postCreationService.createImage(data);
		broadcastingService.addPost(post);
		clearTempData(data, post.getId());
	}

	private void clearTempData(PostData data, String id) {
		if (data.getImage()!= null && StringUtils.isNotEmpty(data.getImage().getFile())) {
			String tempFileName = TEMP_IMAGE_PATH + data.getImage().getFile();
			String archivedFileName = ARCHIVE_IMAGE_PATH + id + PostUtil.getFileExtension(tempFileName);
			try {
				FileUtils.moveFile(new File(tempFileName), new File(archivedFileName));
			} catch (IOException e) {
				LOG.warn("Could not move tempFile ({}) to archive ({})", tempFileName, archivedFileName);
				LOG.debug("Post creation unfinished:", e);
			}
		}
	}
	
}
