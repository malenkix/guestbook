package de.nadirhelix.guestbook.image.facade.impl;

import static de.nadirhelix.guestbook.image.PostConstants.TEMP_IMAGE_PATH;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import de.nadirhelix.guestbook.image.dto.PostData;
import de.nadirhelix.guestbook.image.facade.PostFacade;
import de.nadirhelix.guestbook.image.service.PostCreatorService;
import de.nadirhelix.guestbook.post.model.Post;
import de.nadirhelix.guestbook.post.service.BroadcastingService;

/**
 * Default implementation of {@link PostFacade}.
 * 
 * @author Phil
 *
 */
@Component("postFacade")
public class PostFacadeImpl implements PostFacade {
	
	@Resource
	private PostCreatorService postCreatorService;
	
	@Resource
	private BroadcastingService broadcastingService;

	/**
	 * Adds a post for given {@link PostData} to the broadcasting system.
	 */
	@Override
	public String addPost(PostData data) {
		Post post = postCreatorService.createImage(data);
		broadcastingService.addPost(post);
		clearTempData(data);
		
		return post.getId();
	}

	private void clearTempData(PostData data) {
		if (data.getImage()!= null && StringUtils.isNotEmpty(data.getImage().getFile())) {
			String tempFileName = TEMP_IMAGE_PATH + data.getImage().getFile();
			FileUtils.deleteQuietly(new File(tempFileName));
		}
	}

	@Override
	public String uploadImage(byte[] payload, String originalFileName) throws IOException {
		String uuid = UUID.randomUUID().toString();	
		File file = new File(TEMP_IMAGE_PATH +  uuid + getSuffix(originalFileName));
		FileUtils.writeByteArrayToFile(file, payload);
		return file.getName();
	}

	private String getSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf('.'));
	}

}
