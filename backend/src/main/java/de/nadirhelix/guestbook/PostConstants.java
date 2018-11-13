package de.nadirhelix.guestbook;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import de.nadirhelix.guestbook.GuestbookApplication;
import de.nadirhelix.guestbook.image.processing.util.ColorConverter;
import de.nadirhelix.guestbook.image.util.Point;

/**
 * Constants class containing hard-coded configurations.
 * 
 * @author Phil
 */
public class PostConstants {
	
	public static final int POST_WIDTH = 690;
	
	public static final int POST_HEIGHT = 850;
	
	public static final Point LEFT_UPPER_CORNER = new Point(45,45);	
	
	public static final float SCALING_FACTOR = 2.0f;
	
	public static final String ASSETS_PATH = buildPath(true, true, "resources");
	
	public static final String POST_FILE_PATH = buildPath(true, true, "posts");
	
	public static final String  TEMP_IMAGE_PATH = buildPath(true, true, "temp");
	
	public static final String  ARCHIVE_PATH = buildPath(true, true, "archive");
	
	public static final String XML_DB_FILE_PATH = buildPath(false, true, "data", "post.xml");
	
	public static final String BACKGROUND_IMAGE_PATH = ASSETS_PATH + buildPath(true, false, "background");
	
	public static final String FONTS_PATH = ASSETS_PATH + buildPath(true, false, "fonts");
	
	public static final int DEFAULT_FONT_SIZE = 20;
	
	public static final int MAX_FONT_SIZE = 120;

	public static final String DEFAULT_MESSAGE_FONT = "Arial";
	
	public static final int MESSAGE_MAXLENGTH = 32;
	
	public static final int SUBTEXT_MAXLENGTH = 32;
	
	public static final String DEFAULT_SUBTEXT_FONT = FONTS_PATH + buildPath(false, false, "subtextFont.vlw");
	
	public static final int DEFAULT_SUBTEXT_COLOR = ColorConverter.convert("#000");
	
	public static final int DEFAULT_SUBTEXT_POS_X = 345;
	
	public static final int DEFAULT_SUBTEXT_POS_Y = 750;
	
	public static String buildPath(boolean hasClosingSeparator, boolean isRootPath, String... args) {
		List<String> arguments = Arrays.asList(args);
		StringBuilder sb = new StringBuilder();
		if (isRootPath) {
			sb.append('.');
			sb.append(File.separator);
			checkForDevMode(sb);
		}
		arguments.forEach(a -> { sb.append(a); sb.append(File.separator);});
		if (hasClosingSeparator) {
			return sb.toString();
		}
		return sb.toString().substring(0, sb.length() - 1);
	}
	
	private static void checkForDevMode(StringBuilder sb) {
		if (GuestbookApplication.isDevMode()) {
			sb.append("target").append(File.separator);
		}
		
	}

	private PostConstants() {
		//
	}
}
