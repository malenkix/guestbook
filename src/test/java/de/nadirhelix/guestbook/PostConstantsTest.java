package de.nadirhelix.guestbook;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

/**
 * This test verifies that {@link PostConstants#buildPath(boolean, boolean, String...)} is working properly.
 *  
 * @author Phil
 */
public class PostConstantsTest {

	private static final String ONE = "one";
	private static final String TWO = "two";
	private static final String THREE = "three";
	private static final String SEPARATOR = File.separator;
	private static final String ROOT_PATH_OPENER = "." + SEPARATOR;
	private static final String ONE_TWO_THREE = ONE + SEPARATOR + TWO
			+ SEPARATOR + THREE;

	@Test
	public void testbuildPathWithClosingSeparatorAndRootPath() {
		String result = PostConstants.buildPath(true, true, ONE, TWO, THREE);

		assertEquals(ROOT_PATH_OPENER + ONE_TWO_THREE + SEPARATOR, result);
	}

	@Test
	public void testbuildPathWithClosingSeparator() {
		String result = PostConstants.buildPath(true, false, ONE, TWO, THREE);

		assertEquals(ONE_TWO_THREE + SEPARATOR, result);
	}

	@Test
	public void testbuildPathWithRootPathOpener() {
		String result = PostConstants.buildPath(false, true, ONE, TWO, THREE);

		assertEquals(ROOT_PATH_OPENER + ONE_TWO_THREE, result);
	}

	@Test
	public void testbuildPathClean() {
		String result = PostConstants.buildPath(false, false, ONE, TWO, THREE);

		assertEquals(ONE_TWO_THREE, result);
	}
}
