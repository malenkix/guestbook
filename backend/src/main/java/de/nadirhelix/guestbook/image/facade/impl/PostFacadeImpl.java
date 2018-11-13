package de.nadirhelix.guestbook.image.facade.impl;

import static de.nadirhelix.guestbook.PostConstants.TEMP_IMAGE_PATH;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import de.nadirhelix.guestbook.image.dto.PostData;
import de.nadirhelix.guestbook.image.facade.PostFacade;
import de.nadirhelix.guestbook.post.service.BroadcastingService;
import de.nadirhelix.guestbook.post.strategy.PostCreationStrategy;
import de.nadirhelix.guestbook.post.util.PostUtil;

/**
 * Default implementation of {@link PostFacade}.
 * 
 * @author Phil
 *
 */
@Component("postFacade")
public class PostFacadeImpl implements PostFacade {
	
	@Resource
	private PostCreationStrategy postCreationStrategy;
	
	@Resource
	private BroadcastingService broadcastingService;

	/**
	 * Adds a {@link PostData} to postCreationStrategy.
	 */
	@Override
	public void addPost(PostData data) {
		postCreationStrategy.addPost(data);
	}

	@Override
	public String uploadImage(byte[] payload, String originalFileName) throws IOException {
		String uuid = UUID.randomUUID().toString();	
		File file = new File(TEMP_IMAGE_PATH +  uuid + PostUtil.getFileExtension(originalFileName));
		FileUtils.writeByteArrayToFile(file, payload);
		return file.getName();
	}

}
