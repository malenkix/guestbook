package de.nadirhelix.guestbook.image.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import processing.core.PImage;
import de.nadirhelix.guestbook.image.dto.BackgroundData;
import de.nadirhelix.guestbook.image.dto.PostData;
import de.nadirhelix.guestbook.image.service.PostCreationService;
import de.nadirhelix.guestbook.post.model.Post;
import de.nadirhelix.guestbook.post.util.PostIdGenerator;
import de.nadirhelix.guestbook.processing.PostApplet;

/**
 * The default implementation for the {@link PostCreationService}.
 * 
 * @author Phil
 */
@Service("postCreationService")
public class PostCreationServiceImpl implements PostCreationService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PostCreationServiceImpl.class);
	
	@Resource
	private PostIdGenerator postIdGenerator;

	@Override
	public Post createImage(PostData data) {
		PostApplet applet = PostApplet.instance();
		setBackground(applet, data.getBackground());
		addImage(applet, data);
		applet.addMessage(data.getMessage());
		applet.drawFrame();
		applet.addSubtext(data.getSubtext());
		String postId = postIdGenerator.generateId();
		String fileName = applet.storeImage(postId);
		PostApplet.releaseInstance();
		return createPost(postId, data, fileName);
	}

	private void setBackground(PostApplet applet, BackgroundData bg) {
		if (bg != null) {
			if (bg.isImage()) {
				PImage image = applet.loadBackgroundImage(bg.getImageId());
				applet.background(image);
			} else {
				applet.background(bg.getColor());
			}			
		}
	}

	private void addImage(PostApplet applet, PostData data) {
		boolean success = applet.addImage(data.getImage());
		if (!success) {
			LOG.warn("Could not apply image to post. File ({}) seems to be missing.", data.getImage().getFile());
		}
	}
	
	private Post createPost(String postId, PostData data, String fileName) {
		return new Post(postId, data.getDate(), fileName, data.getWishes(), data.getName());
	}

}
