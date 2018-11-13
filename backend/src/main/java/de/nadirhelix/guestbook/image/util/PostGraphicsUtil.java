package de.nadirhelix.guestbook.image.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import processing.awt.PGraphicsJava2D;
import processing.core.PGraphics;

/**
 * <p>This util class takes care that some preparation processes take place without any accesses to the graphics environment.</p>
 * <p>Most of the content is copied from {@link PGraphicsJava2D} and {@link PGraphics} but without anything trying to access any 
 * displays.</p>
 * 
 * @author Phil
 */
public class PostGraphicsUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(PostGraphicsUtil.class); 

	/**
	 * Prepares a {@link PGraphicsJava2D} so processing can create images with it and no errors
	 * related to graphics environments occur.
	 * 
	 * @param input 
	 * 			the {@link PGraphicsJava2D} to be prepared.
	 */
	public static void prepareGraphics(PGraphicsJava2D input) {
		Graphics2D g2 = checkImage(input);
		input.g2 = g2;

		handleSmooth(input, g2);

		checkSettings(input);
		resetMatrix(g2, input);
	}

	private static Graphics2D checkImage(PGraphics input) {
		BufferedImage image = new BufferedImage(input.width, input.height,  BufferedImage.TYPE_INT_ARGB);
		input.image = image;
		return (Graphics2D) image.getGraphics();
	}

	private static void handleSmooth(PGraphics input, Graphics2D g2) {
		if (input.smooth == 0) {
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_OFF);
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

		} else {
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			if (input.smooth == 1 || input.smooth == 3) { // default is bicubic
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			} else if (input.smooth == 2) {
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			}

			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
					RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		}
	}

	private static void checkSettings(PGraphicsJava2D input) {
		try {
			Method defaultSettings = PGraphicsJava2D.class
					.getDeclaredMethod("defaultSettings");
			defaultSettings.setAccessible(true);
			ReflectionUtils.invokeMethod(defaultSettings, input);

			Method reapplySettings = PGraphics.class
					.getDeclaredMethod("reapplySettings");
			reapplySettings.setAccessible(true);
			ReflectionUtils.invokeMethod(reapplySettings, input);
		} catch (NoSuchMethodException e) {
			LOG.error("Error on graphics preparation", e);
		}
	}

	private static void resetMatrix(Graphics2D g2, PGraphics input) {
		g2.setTransform(new AffineTransform());
		g2.scale(input.pixelDensity, input.pixelDensity);
	}
}
