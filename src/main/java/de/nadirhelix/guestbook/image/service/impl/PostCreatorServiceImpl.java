package de.nadirhelix.guestbook.image.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import processing.core.PImage;
import de.nadirhelix.guestbook.image.dto.BackgroundData;
import de.nadirhelix.guestbook.image.dto.PostData;
import de.nadirhelix.guestbook.image.service.PostCreatorService;
import de.nadirhelix.guestbook.post.model.Post;
import de.nadirhelix.guestbook.post.util.PostIdGenerator;
import de.nadirhelix.guestbook.processing.PostApplet;

/**
 * The default implementation for the {@link PostCreatorService}.
 * 
 * @author Phil
 */
@Service("postCreatorService")
public class PostCreatorServiceImpl implements PostCreatorService {
	
	@Resource
	private PostIdGenerator postIdGenerator;

	@Override
	public Post createImage(PostData data) {
		PostApplet applet = PostApplet.create();
		setBackground(applet, data.getBackground());
		applet.addImage(data.getImage());
		applet.addMessage(data.getMessage());
		applet.drawFrame();
		applet.addSubtext(data.getSubtext());
		String postId = generatePostID(data);
		String fileName = applet.storeImage(postId);
		finalizeApplet(applet);
		return createPost(postId, data, fileName);
	}

	private void finalizeApplet(PostApplet applet) {
		applet.dispose();
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

	private String generatePostID(PostData data) {
		return postIdGenerator.generateId();
	}

	private Post createPost(String postId, PostData data, String fileName) {
		return new Post(postId, data.getDate(), fileName, data.getWishes(), data.getName());
	}

}
