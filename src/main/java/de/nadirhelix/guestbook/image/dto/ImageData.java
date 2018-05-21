package de.nadirhelix.guestbook.image.dto;

/**
 * DTO containing all relevant data to describe an image.
 * 
 * @author Phil
 */
public class ImageData implements ComponentData {

	private int posX;
	private int posY;
	private int width;
	private int height;
	private float rotation;
	private String file;
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public float getRotation() {
		return rotation;
	}

	public String getFile() {
		return file;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void setFile(String file) {
		this.file = file;
	}
}
