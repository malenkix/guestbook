package de.nadirhelix.guestbook.post.strategy;

import static de.nadirhelix.guestbook.PostConstants.ARCHIVE_IMAGE_PATH;
import static de.nadirhelix.guestbook.PostConstants.TEMP_IMAGE_PATH;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import de.nadirhelix.guestbook.image.dto.PostData;
import de.nadirhelix.guestbook.image.service.PostCreationService;
import de.nadirhelix.guestbook.post.exception.PostCreationException;
import de.nadirhelix.guestbook.post.model.Post;
import de.nadirhelix.guestbook.post.service.BroadcastingService;
import de.nadirhelix.guestbook.post.util.PostUtil;

@Component
public class PostCreationStrategy {
	
	private final PostCreationTask task;

	@Resource
	private BroadcastingService broadcastingService;
	
	@Resource
	private PostCreationService postCreationService;	
	
	public PostCreationStrategy() {
		task = new PostCreationTask();
		task.setPostCreationStrategy(this);
		task.start();
	}
	
	public void addPost(PostData post) {
		task.addPost(post);
	}
	
	public void createPost(PostData data) throws PostCreationException {
		Post post = postCreationService.createImage(data);
		broadcastingService.addPost(post);
		clearTempData(data, post.getId());
	}

	private void clearTempData(PostData data, String id) throws PostCreationException {
		if (data.getImage()!= null && StringUtils.isNotEmpty(data.getImage().getFile())) {
			String tempFileName = TEMP_IMAGE_PATH + data.getImage().getFile();
			String archivedFileName = ARCHIVE_IMAGE_PATH + id + PostUtil.getFileExtension(tempFileName);
			try {
				FileUtils.moveFile(new File(tempFileName), new File(archivedFileName));
			} catch (IOException e) {
				throw new PostCreationException(e, "Could not move tempFile (%s) to archive (%s)", tempFileName, archivedFileName);
			}
		}
	}
	
}
