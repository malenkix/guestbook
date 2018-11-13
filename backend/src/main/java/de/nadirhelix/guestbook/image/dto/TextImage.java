package de.nadirhelix.guestbook.image.dto;

import de.nadirhelix.guestbook.image.util.Point;
import processing.core.PImage;

/**
 * This is a data object which holds image data from a text as pixel graphic and will calculate the 
 * absolute dimensions of that text inside the {@link PImage}.
 *  
 * @author Phil
 */
public class TextImage implements ComponentData {

	private final PImage image;
	
	private float posX = 0;

	private float posY = 0;

	private float rotation = 0;

	private float widthEffective = 0;

	private float heightEffective = 0;

	private int horizontalTranslation = 0;

	private int verticalTranslation = 0;

	
	/**
	 * Private Constructor. Sets the initial {@link PImage}.
	 * 
	 * @param image
	 * 			initial Image
	 */
	private TextImage(PImage image) {
		this.image = image;
	}
	
	/**
	 * Creates a {@link TextImage} from a given {@link PImage}.
	 * @param image
	 * 			the {@link PImage} containing the text as graphic
	 * @return the {@link TextImage}
	 */
	public static TextImage create(PImage image) {
		return new TextImage(image);
	}
	
	/**
	 * Creates an empty image without text.
	 * 
	 * @return empty {@link TextImage}
	 */
	public static TextImage createEmpty() {
		return new TextImage(new PImage(0,0));
	}

	
	/**
	 * Gets the {@link PImage} containing the text as pixel graphic.
	 * 
	 * @return {@link PImage} containing the text
	 */
	public PImage getImage() {
		return image;
	}
	
	/**
	 * Gets the width of the text image.
	 * 
	 * @return the text image width
	 */
	@Override
	public float getWidth() {
		return image.width;
	}
	
	/**
	 * Gets the height of the text image.
	 * 
	 * @return the text image height
	 */
	@Override
	public float getHeight() {
		return image.height;
	}

	/**
	 * @see de.nadirhelix.guestbook.image.dto.ComponentData#getPosX()
	 */
	@Override
	public float getPosX() {
		return posX;
	}

	/**
	 * @see de.nadirhelix.guestbook.image.dto.ComponentData#getPosY()
	 */
	@Override
	public float getPosY() {
		return posY;
	}

	/**
	 * @see de.nadirhelix.guestbook.image.dto.ComponentData#getRotation()
	 */
	@Override
	public float getRotation() {
		return rotation;
	}
	
	/**
	 * Returns the effective width of the text.
	 * 
	 * @return effective width
	 */
	@Override
	public float getWidthEffective() {
		return widthEffective;
	}

	/**
	 * Returns the effective height of the text.
	 * 
	 * @return effective height
	 */
	@Override
	public float getHeightEffective() {
		return heightEffective;
	}

	/**
	 * Calculates the translation to the horizontal and vertical 
	 * center of the text.
	 */
	@Override
	public Point getBuffer() {
		return new Point(widthEffective * 0.5f + horizontalTranslation, 
				heightEffective * 0.5f + verticalTranslation);
	}

	/**
	 * Sets the absolute x position by subtracting the horizontal translation from the given value.
	 * 
	 * @param posX
	 * 			the x value
	 */
	public void setPosX(float posX) {
		this.posX = posX;  
	}


	/**
	 * Sets the absolute y position by subtracting the vertical translation from the given value.
	 * 
	 * @param posY
	 * 			the y value
	 */
	public void setPosY(float posY) {
		this.posY = posY; 		
	}
	
	/**
	 * Sets the rotation in degrees.
	 * 
	 * @param rotation 
	 * 				the rotation to set
	 */
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	/**
	 * Sets the horizontal translation.
	 * 
	 * @param horizontalTranslation
	 * 				the translation to set.
	 */
	public void setHorizontalTranslation(int horizontalTranslation) {
		this.horizontalTranslation = horizontalTranslation;
	}
	
	/**
	 * Sets the vertical translation.
	 * 
	 * @param verticalTranslation
	 * 				the translation to set.
	 */
	public void setVerticalTranslation(int verticalTranslation) {
		this.verticalTranslation = verticalTranslation;
	}
	
	/**
	 * Sets the effective width of the text.
	 * 
	 * @param widthEffective
	 * 				the effective width
	 */
	public void setWidthEffective(int widthEffective) {
		this.widthEffective = widthEffective;
	}
	
	/**
	 * Sets the effective height of the text.
	 * 
	 * @param heightEffective
	 * 				the effective height
	 */
	public void setHeightEffective(int heightEffective) {
		this.heightEffective = heightEffective;
	}
}
