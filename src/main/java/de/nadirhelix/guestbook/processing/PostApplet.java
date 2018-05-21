package de.nadirhelix.guestbook.processing;

import static de.nadirhelix.guestbook.image.PostConstants.ASSETS_PATH;
import static de.nadirhelix.guestbook.image.PostConstants.BACKGROUND_IMAGE_PATH;
import static de.nadirhelix.guestbook.image.PostConstants.DEFAULT_BG_COLOR;
import static de.nadirhelix.guestbook.image.PostConstants.DEFAULT_SUBTEXT_COLOR;
import static de.nadirhelix.guestbook.image.PostConstants.DEFAULT_SUBTEXT_FONT;
import static de.nadirhelix.guestbook.image.PostConstants.DEFAULT_SUBTEXT_POS_X;
import static de.nadirhelix.guestbook.image.PostConstants.DEFAULT_SUBTEXT_POS_Y;
import static de.nadirhelix.guestbook.image.PostConstants.LEFT_UPPER_CORNER;
import static de.nadirhelix.guestbook.image.PostConstants.POST_FILE_PATH;
import static de.nadirhelix.guestbook.image.PostConstants.POST_HEIGHT;
import static de.nadirhelix.guestbook.image.PostConstants.POST_WIDTH;
import static de.nadirhelix.guestbook.image.PostConstants.SCALING_FACTOR;
import static de.nadirhelix.guestbook.image.PostConstants.SUBTEXT_MAXLENGTH;
import static de.nadirhelix.guestbook.image.PostConstants.TEMP_IMAGE_PATH;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import de.nadirhelix.guestbook.image.dto.ComponentData;
import de.nadirhelix.guestbook.image.dto.ImageData;
import de.nadirhelix.guestbook.image.dto.TextData;
import de.nadirhelix.guestbook.image.dto.TextImage;
import de.nadirhelix.guestbook.image.processing.util.ColorConverter;
import de.nadirhelix.guestbook.image.util.ImageCache;
import de.nadirhelix.guestbook.image.util.Point;
import de.nadirhelix.guestbook.image.util.TextCreationUtil;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * Extension of {@link PApplet}, which realizes the creation of the post images.
 *
 * @author Phil
 */
public class PostApplet extends PApplet {

	private static final String HANDLE_SEETINGS_ERROR_MESSAGE = "Cannot create posts when PApplet.handleSettings() is not accesable.";

	private static final String FILE_SUFFIX = ".png";
	
	private static final String FRAME = "frame";
	
	private static final Method HANDLE_SETTINGS;
	
	private static ThreadLocal<Integer> targetWidth = new ThreadLocal<>();

	private static ThreadLocal<Integer> targetHeight = new ThreadLocal<>();
	
	static {
		Method m = null;
		try {
			Class<PApplet> cls = PApplet.class;
			m = cls.getDeclaredMethod("handleSettings");
			m.setAccessible(true);
		} catch (SecurityException | NoSuchMethodException e) {
			throw new IllegalStateException(HANDLE_SEETINGS_ERROR_MESSAGE, e);
		}
		HANDLE_SETTINGS = m;		
	}

	/**
	 * Default private constructor.
	 */
	private PostApplet() {
		super();
		surface = initSurface();
		if (targetWidth.get() != null && targetWidth.get() >  0 && 
				targetHeight.get() != null && targetHeight.get() > 0) {
			g = createGraphics(targetWidth.get(), targetHeight.get());			
		} else {
			g = createGraphics(POST_WIDTH, POST_HEIGHT);
		}
		g.beginDraw();
	}
	
	/**
	 * Creates and initializes a new PostApplet.
	 * 
	 * @return the newly created PostApplet
	 */
	public static PostApplet create() {
		return create(0, 0);
	}	
	
	public static PostApplet create(int width, int height) {
		setTargetDimensions(width, height);
		PostApplet result = new PostApplet();
		try {
			HANDLE_SETTINGS.invoke(result);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new IllegalStateException(HANDLE_SEETINGS_ERROR_MESSAGE, e);
		}
		result.noLoop();
		return result;
	}

	/**
	 * @see processing.core.PApplet#settings()
	 */
	@Override
	public void settings() {
		if (targetWidth.get() != null && targetWidth.get() >  0 && 
				targetHeight.get() != null && targetHeight.get() > 0) {
			size(targetWidth.get(), targetHeight.get());			
		} else {
			size(POST_WIDTH, POST_HEIGHT);
		}
		smooth();
		sketchPath();
	}
	
	/**
	 * @see processing.core.PApplet#background(processing.core.PImage)
	 */
	@Override
	public void background(PImage image) {
		initBackground();
		super.background(image);
	}

	private void initBackground() {
		background(DEFAULT_BG_COLOR);
	}
	
	/**
	 * <p>Sets the background based on a hexadecimal interpretation of a color.</p>
	 *    
	 * @param color 
	 * 			value in hexadecimal format
	 */
	public void background(String color) {
		super.background(ColorConverter.convert(color));
	}
	
	
	/**
	 * <p>Returns a background image for a given id.</p>
	 * <p><i>The {@link ImageCache} is used to retrieve the desired image</i></p> 
	 * 
	 * @param imageId
	 * 			the id of the background image
	 * @return the {@link PImage}
	 */
	public PImage loadBackgroundImage(String imageId) {
		return getImage(imageId, BACKGROUND_IMAGE_PATH);
	}

	
	/**
	 * Applies the given image to the post.
	 * 
	 * @param image 
	 * 			the image to be drawn.
	 */
	public void addImage(ImageData image) {
		if (image == null || StringUtils.isBlank(image.getFile())) {
			return;
		}
		PImage postImage = loadImage(TEMP_IMAGE_PATH + image.getFile());
		applyGraphic(image, postImage);
	}

	/**
	 * Applies the given message to the post.
	 * 
	 * @param message the message to be drawn.
	 */
	
	public void addMessage(TextData message) {
		if (message == null || StringUtils.isBlank(message.getContent())) {
			return;
		}
		TextImage text = TextCreationUtil.createTextImage(message);
		applyGraphic(text, text.getImage());
	}

	/**
	 * Draws the Polaroid frame.
	 */
	public void drawFrame() {
		PImage frame = getImage(FRAME, ASSETS_PATH + "/images/");
		image(frame, 0, 0);
	}

	/**
	 * Applies the given subtext to the post.
	 * 
	 * @param text 
	 * 			the subtext to be added.
	 */
	public void addSubtext(String text) {
		if (StringUtils.isBlank(text)) {
			return;
		}
		String subtext = StringUtils.substring(text, 0, SUBTEXT_MAXLENGTH);
		textAlign(CENTER);
		PFont font = loadFont(DEFAULT_SUBTEXT_FONT);
		fill(DEFAULT_SUBTEXT_COLOR);
		textFont(font);
		text(subtext, DEFAULT_SUBTEXT_POS_X, DEFAULT_SUBTEXT_POS_Y);
	}

	private void applyGraphic(ComponentData image, PImage postImage) {
		boolean isRotation = !Integer.valueOf(0).equals(image.getRotation());
		Point buffer = image.getBuffer();
		Point absolutePosition = getLeftUpperCorner(image, buffer); 
		translate(absolutePosition.getX(), absolutePosition.getY());
		if (isRotation) {
			rotate(radians(image.getRotation()));
		}
		image(postImage, -buffer.getX(), -buffer.getY(), rescale(image.getWidth()), rescale(image.getHeight()));
		if (isRotation) {
			rotate(radians(-image.getRotation()));
		}
		translate(-absolutePosition.getX(), -absolutePosition.getY());
	}
	
	private float rescale(int value) {
		return value * SCALING_FACTOR;
	}


	private Point getLeftUpperCorner(ComponentData component, Point buffer) {
		float x = LEFT_UPPER_CORNER.getX() + component.getPosX() + component.getWidthEffective() * 0.5f;
		float y = LEFT_UPPER_CORNER.getY() + component.getPosY() + component.getHeightEffective() * 0.5f;
		return new Point(x, y);
	}

	private PImage getImage(String imageId, String path) {
		PImage result = ImageCache.get(imageId);
		if (result == null) {
			result = loadImage(path + imageId + FILE_SUFFIX);
			ImageCache.cache(imageId, result);
		}
		return result;
	}
	
	/**
	 * Stores the image by naming it like the postId.
	 * 
	 * @param postId
	 *			the postId
	 */
	public String storeImage(String postId) {
		String fileName = POST_FILE_PATH + postId + FILE_SUFFIX;
		save(fileName);
		return fileName;
	}

	private static void setTargetDimensions(int width, int height) {
		targetWidth.set(width);
		targetHeight.set(height);
	}
}
