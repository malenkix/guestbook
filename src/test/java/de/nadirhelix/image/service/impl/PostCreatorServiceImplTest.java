package de.nadirhelix.image.service.impl;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.h2.store.fs.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.nadirhelix.image.dto.BackgroundData;
import de.nadirhelix.image.dto.ImageData;
import de.nadirhelix.image.dto.PostData;
import de.nadirhelix.image.dto.TextData;
import de.nadirhelix.image.service.PostCreatorService;

/**
 * UnitTest for {@link PostCreatorServiceImpl}.
 * 
 * @author Phil
 */
public class PostCreatorServiceImplTest {

	private PostCreatorService postCreatorService = new PostCreatorServiceImpl();
	
	private PostData data;
	
	private static String currentDir = System.getProperty("user.dir");
	
	@BeforeClass
	public static void preparePath() {
		System.setProperty("user.dir", 
				new StringBuilder(currentDir).append(File.separator)
					.append("src").append(File.separator)
					.append("test").append(File.separator)
					.append("resources").toString());
	}
	
	@AfterClass
	public static void cleanup() {
		System.setProperty("user.dir", currentDir);
	}
	
	@Before
	public void prepareData() {
		data = new PostData();
		
		data.setDate(new Date());
		ImageData image = new ImageData();
		image.setFileName("tempID123456.png");
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
		
		postCreatorService.createImage(data);
	}
	
	@Test
	public void testCreateImageWithoutMessage() {
		data.setWishes("ID123457");
		data.getMessage().setContent(StringUtils.EMPTY);
		
		postCreatorService.createImage(data);
	}
	
	@Test
	public void testCreateImageWithoutImage() {
		data.setWishes("ID123458");
		data.setImage(null);
		
		postCreatorService.createImage(data);
	}
	
	@Test
	public void testCreateImageWithoutBackground() {
		data.setWishes("ID123459");
		data.setBackground(null);
		
		postCreatorService.createImage(data);
	}

	@After
	public void deleteFile() {
		String fileName = currentDir + "/src/test/resources/posts/" + data.getWishes() + ".png";
		assertTrue(FileUtils.exists(fileName));
		FileUtils.delete(fileName);
	}
}
