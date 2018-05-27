package de.nadirhelix.image.processing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.GraphicsEnvironment;
import java.lang.reflect.Field;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import de.nadirhelix.guestbook.image.dto.TextData;
import de.nadirhelix.guestbook.image.dto.TextImage;
import de.nadirhelix.guestbook.image.util.Point;
import de.nadirhelix.guestbook.image.util.TextCreationUtil;

/**
 * Tests the {@link TextCreationUtil}.
 * 
 * @author Phil
 */
public class TextCreationUtilTest {
	
	private static final String GREEN = "#0F0";

	@BeforeClass
	public static void prepareTest() throws NoSuchFieldException, SecurityException {
		Field field = GraphicsEnvironment.class.getDeclaredField("headless");
		field.setAccessible(true);
		ReflectionUtils.setField(field, GraphicsEnvironment.class, Boolean.FALSE);
	}
	
	@Test
	public void testImageCreation() {
		TextData data = new TextData();
		data.setColor(GREEN);
		data.setContent("Test");
		data.setFont("Arial");
		data.setPosX(20);
		data.setPosY(50);
		data.setRotation(3);
		data.setSize(12);
		
		TextImage result = TextCreationUtil.createTextImage(data);
		
		assertNotNull(result.getImage());
		assertEquals(data.getPosY(), result.getPosY());
		assertEquals(data.getPosX(), result.getPosX());	
		assertEquals(data.getRotation(), result.getRotation(), 0);
		assertTrue(result.getWidth() > result.getWidthEffective());
		assertTrue(result.getHeight() > result.getHeightEffective());
		
		Point buffer = result.getBuffer();
		assertTrue((result.getHeightEffective() * 0.5) < buffer.getY());
	}
	
}
