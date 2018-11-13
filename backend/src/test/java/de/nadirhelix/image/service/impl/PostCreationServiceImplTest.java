package de.nadirhelix.image.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.awt.GraphicsEnvironment;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;
import org.h2.store.fs.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.ReflectionUtils;

import de.nadirhelix.guestbook.image.dto.BackgroundData;
import de.nadirhelix.guestbook.image.dto.ImageData;
import de.nadirhelix.guestbook.image.dto.PostData;
import de.nadirhelix.guestbook.image.dto.TextData;
import de.nadirhelix.guestbook.image.service.PostCreationService;
import de.nadirhelix.guestbook.image.service.impl.PostCreationServiceImpl;
import de.nadirhelix.guestbook.post.model.Post;
import de.nadirhelix.guestbook.post.util.PostIdGenerator;

/**
 * UnitTest for {@link PostCreationServiceImpl}.
 * 
 * @author Phil
 */
@RunWith(MockitoJUnitRunner.class)
public class PostCreationServiceImplTest {

	private static final String FILE_PATH = System.getProperty("user.dir") + "/posts/%s.png";
	private static final AtomicLong ID = new AtomicLong();

	@InjectMocks
	private PostCreationService postCreationService = new PostCreationServiceImpl();
	
	@Mock
	private PostIdGenerator postIdGenerator;

	private PostData data;

	@BeforeClass
	public static void prepareTest() throws NoSuchFieldException, SecurityException {
		Field field = GraphicsEnvironment.class.getDeclaredField("headless");
		field.setAccessible(true);
		ReflectionUtils.setField(field, GraphicsEnvironment.class, Boolean.FALSE);
	}

	@Before
	public void prepareDependencies() {
		when(postIdGenerator.generateId()).thenReturn(ID.incrementAndGet());
	}

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
		background.setIsImage(false);
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
		Post post = postCreationService.createImage(data);
		assertFileCreated(post.getId());
	}

	@Test
	public void testCreateImageWithoutMessage() {
		data.getMessage().setContent(StringUtils.EMPTY);		
		Post post = postCreationService.createImage(data);
		assertFileCreated(post.getId());
	}

	@Test
	public void testCreateImageWithoutImage() {
		data.setImage(null);

		Post post = postCreationService.createImage(data);

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

		Post post = postCreationService.createImage(data);

		assertFileCreated(post.getId());
	}

	public void deleteFile(String id) {
		String fileName = String.format(FILE_PATH, id);
		FileUtils.delete(fileName);
	}
}
