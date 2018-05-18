package de.nadirhelix.image.dto;

import org.apache.commons.lang3.StringUtils;


/**
 * DTO containing all relevant data to describe an image.
 * 
 * @author Phil
 */
public class ImageData implements ComponentData {

	private int posX = 0;
	private int posY = 0;
	private int width = 0;
	private int height = 0;
	private float rotation = 0;
	private String fileName = StringUtils.EMPTY;
	
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
	
	public String getFileName() {
		return fileName;
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

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
