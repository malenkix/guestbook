package de.nadirhelix.image.service.impl;

import org.springframework.stereotype.Service;

import processing.core.PImage;
import processing.core.PostApplet;
import de.nadirhelix.image.dto.BackgroundData;
import de.nadirhelix.image.dto.PostData;
import de.nadirhelix.image.service.PostCreatorService;

/**
 * The default implementation for the {@link PostCreatorService}.
 * 
 * @author Phil
 */
@Service
public class PostCreatorServiceImpl implements PostCreatorService {

	@Override
	public String createImage(PostData data) {
		PostApplet applet = PostApplet.create();
		setBackground(applet, data.getBackground());
		applet.addImage(data.getImage());
		applet.addMessage(data.getMessage());
		applet.drawFrame();
		applet.addSubtext(data.getSubtext());
		String postId = generatePostID(data);
		applet.storeImage(postId);
		return postId;
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
		// TODO Replace with valid generator.
		return data.getWishes();
	}

}
