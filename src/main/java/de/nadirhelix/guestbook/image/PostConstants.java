package de.nadirhelix.guestbook.image;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import de.nadirhelix.guestbook.image.processing.util.ColorConverter;
import de.nadirhelix.guestbook.image.util.Point;

/**
 * Constants class containing hard-coded configurations.
 * 
 * @author Phil
 */
public class PostConstants {
	
	public static final int POST_WIDTH = 544;
	
	public static final int POST_HEIGHT = 667;
	
	public static final Point LEFT_UPPER_CORNER = new Point(25, 25);	
	
	public static final float SCALING_FACTOR = 1.0f;

	public static final String DEFAULT_BG_COLOR = "#00ff00";
	
	public static final String ASSETS_PATH = buildPath(false, "src", "main", "resources", "assets");
	
	public static final String POST_FILE_PATH = buildPath(true, "posts");
	
	public static final String  TEMP_IMAGE_PATH = buildPath(true, "temp");
	
	public static final String BACKGROUND_IMAGE_PATH = ASSETS_PATH + buildPath(true, "images", "background");
	
	
	public static final int DEFAULT_FONT_SIZE = 12;
	
	public static final int MAX_FONT_SIZE = 72;
	
	public static final List<String> FONTS = Arrays.asList("Arial", "Comic Sans MS", "Times New Roman");

	public static final String DEFAULT_MESSAGE_FONT = "Arial";
	
	public static final int MESSAGE_MAXLENGTH = 32;
	
	
	public static final int SUBTEXT_MAXLENGTH = 32;
	
	public static final String DEFAULT_SUBTEXT_FONT = ASSETS_PATH + buildPath(false, "font", "subtextFont.vlw");
	
	public static final int DEFAULT_SUBTEXT_COLOR = ColorConverter.convert("#000");
	
	public static final int DEFAULT_SUBTEXT_POS_X = 272;
	
	public static final int DEFAULT_SUBTEXT_POS_Y = 600;
	
	
	private static String buildPath(boolean hasClosingSeparator, String... args) {
		List<String> arguments = Arrays.asList(args);
		StringBuilder sb = new StringBuilder();
		arguments.forEach(a -> {sb.append(File.separator); sb.append(a);});
		if (hasClosingSeparator) {
			sb.append(File.separator);
		}
		return sb.toString();
	}
	
	private PostConstants() {
		//
	}
}
