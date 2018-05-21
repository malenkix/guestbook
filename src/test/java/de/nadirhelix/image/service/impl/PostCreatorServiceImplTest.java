package de.nadirhelix.image.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.h2.store.fs.FileUtils;
import org.junit.Before;
import org.junit.Test;

import de.nadirhelix.guestbook.image.dto.BackgroundData;
import de.nadirhelix.guestbook.image.dto.ImageData;
import de.nadirhelix.guestbook.image.dto.PostData;
import de.nadirhelix.guestbook.image.dto.TextData;
import de.nadirhelix.guestbook.image.service.PostCreatorService;
import de.nadirhelix.guestbook.image.service.impl.PostCreatorServiceImpl;
import de.nadirhelix.guestbook.post.model.Post;

/**
 * UnitTest for {@link PostCreatorServiceImpl}.
 * 
 * @author Phil
 */
public class PostCreatorServiceImplTest {

	private static final String FILE_PATH = System.getProperty("user.dir") + "/posts/%s.png";

	private PostCreatorService postCreatorService = new PostCreatorServiceImpl();
	
	private PostData data;
	
	@Before
	public void prepareData() {
		data = new PostData();
		
		data.setDate(new Date());
		ImageData image = new ImageData();
		image.setFile("tempID123456.png");
		image.setPosX(50);
		image.setPosY(40);
		image.setHeight(250);
		image.setWidth(250);
		image.setRotation(10);
		data.setImage(image);
		
		BackgroundData background = new BackgroundData();
		background.setImage(false);
		background.setColor("#ffaaff");
		data.setBackground(background);
		
		TextData message = new TextData();
		message.setColor("#0000dd");
		message.setContent("My Message for you!");
		message.setFont("Arial");
		message.setPosX(25);
		message.setPosY(320);
		message.setRotation(-6);
		message.setSize(48);
		data.setMessage(message);
		
		data.setSubtext("This is a subtext");
	}
	
	@Test
	public void testCreateImage() {
		data.setWishes("ID123456");
		
		Post post = postCreatorService.createImage(data);

		assertFileCreated(post.getId());
	}
	
	@Test
	public void testCreateImageWithoutMessage() {
		data.getMessage().setContent(StringUtils.EMPTY);
		
		Post post = postCreatorService.createImage(data);

		assertFileCreated(post.getId());
	}
	
	@Test
	public void testCreateImageWithoutImage() {
		data.setImage(null);

		Post post = postCreatorService.createImage(data);

		assertFileCreated(post.getId());
	}
	
	private void assertFileCreated(String id) {
		String fileName = String.format(FILE_PATH, id);
		assertTrue(FileUtils.exists(fileName));
		deleteFile(id);
	}

	@Test
	public void testCreateImageWithoutBackground() {
		data.setBackground(null);

		Post post = postCreatorService.createImage(data);

		assertFileCreated(post.getId());
	}

	public void deleteFile(String id) {
		String fileName = String.format(FILE_PATH, id);
		FileUtils.delete(fileName);
	}
}
