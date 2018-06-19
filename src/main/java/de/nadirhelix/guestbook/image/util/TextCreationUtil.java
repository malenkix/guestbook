package de.nadirhelix.guestbook.image.util;

import static de.nadirhelix.guestbook.PostConstants.DEFAULT_FONT_SIZE;
import static de.nadirhelix.guestbook.PostConstants.FONTS_PATH;
import static de.nadirhelix.guestbook.PostConstants.MAX_FONT_SIZE;
import static de.nadirhelix.guestbook.PostConstants.MESSAGE_MAXLENGTH;
import static de.nadirhelix.guestbook.PostConstants.SCALING_FACTOR;
import static de.nadirhelix.guestbook.resources.util.FontsProvider.DEFAULT_MESSAGE_FONT;

import org.apache.commons.lang3.StringUtils;

import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;
import de.nadirhelix.guestbook.image.dto.TextData;
import de.nadirhelix.guestbook.image.dto.TextImage;
import de.nadirhelix.guestbook.image.processing.util.ColorConverter;
import de.nadirhelix.guestbook.processing.PostApplet;
import de.nadirhelix.guestbook.resources.util.FontsProvider;

/**
 * Allows to create a drawable {@link TextImage} out of a simple {@link TextData}.
 * 
 * @author Phil
 */
public class TextCreationUtil {

	/**
	 * <p>Creates a {@link TextImage} containing a graphical representation from a given {@link TextData}
	 * and its dimensions. </p>
	 * 
	 * @param data 
	 * 			the {@link TextData}
	 * @return the {@link TextImage}
	 */
	public static TextImage createTextImage(TextData data) {
		PostApplet applet = PostApplet.create();
		String message = StringUtils.substring(data.getContent(), 0, MESSAGE_MAXLENGTH);
		prepareFont(data, applet);
		applet.text(message, 0, applet.height);
		TextImage result = TextImage.create(applet.get());
		result.setPosX(data.getPosX());
		result.setPosY(data.getPosY());
		result.setRotation(data.getRotation());
		calculateDimension(result);
		applet.dispose();
		
		return result;
	}

	private static void calculateDimension(TextImage result) {
		PImage image = result.getImage();
		image.loadPixels();
		int[] pixels = image.pixels;
		int firstPixel = pixels.length;
		for (int i = 0 ; i < pixels.length ; i++) {
			if (pixels[i] != 0) {
				firstPixel = i;
				break;
			}
		}
		int verticalTranslation = firstPixel / image.width;
		int horizontalTranslation = getHorizontalTranslation(image, pixels, firstPixel, verticalTranslation);
		int horizontalAmplitude = getHorizontalAmplitude(image, pixels, verticalTranslation);
		result.setVerticalTranslation(verticalTranslation);
		result.setHorizontalTranslation(horizontalTranslation);
		result.setWidthEffective(horizontalAmplitude - horizontalTranslation);
		result.setHeightEffective(image.height - verticalTranslation);
	}

	private static int getHorizontalTranslation(PImage image, int[] pixels, int firstPixel, int verticalTranslation) {
		int horizontalTranslation = firstPixel % image.width;
		if (horizontalTranslation > 0) {
			for (int i = image.height - 1; i >= verticalTranslation; i--) {
				for (int j = horizontalTranslation - 1;  j >= 0; j--) {
					int index = i * image.width + j;
					if (pixels[index] > 0) {
						horizontalTranslation = j;
					}				
				}
				if (horizontalTranslation == 0) {
					break;
				}
			}
		}
		return horizontalTranslation;
	}

	private static int getHorizontalAmplitude(PImage image, int[] pixels,
			int verticalTranslation) {
		int horizontalAmplitude = 0;
		for (int i = image.height - 1; i >= verticalTranslation; i--) {
			for (int j = image.width - 1;  j > horizontalAmplitude; j--) {
				int index = i * image.width + j;
				if (pixels[index] > 0) {
					horizontalAmplitude = j;
				}				
			}
			if (horizontalAmplitude == image.width - 1) {
				break;
			}
		}
		return horizontalAmplitude;
	}

	private static void prepareFont(TextData data, PostApplet applet) {
		applet.textAlign(PConstants.LEFT, PConstants.BOTTOM);
		PFont font = applet.loadFont(findFont(data.getFont()));
		applet.textFont(font, normalizeSize(data.getSize()));
		applet.fill(ColorConverter.convert(data.getColor()));
	}

	private static String findFont(String font) {
		String fileName = FontsProvider.getFileNameForFont(font);
		if (StringUtils.isEmpty(fileName)) {
			return FONTS_PATH + DEFAULT_MESSAGE_FONT;
		}
		return FONTS_PATH + fileName;
	}

	private static float normalizeSize(int size) {
		float result = size * SCALING_FACTOR;
		if (result <= 0 || result > MAX_FONT_SIZE) {
			return DEFAULT_FONT_SIZE;
		}
		return result;
	}
	
}
