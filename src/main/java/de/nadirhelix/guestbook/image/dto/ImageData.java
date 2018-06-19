package de.nadirhelix.guestbook.image.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO containing all relevant data to describe an image.
 * 
 * @author Phil
 */
@XmlRootElement
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

	@XmlElement
	public void setPosX(int posX) {
		this.posX = posX;
	}

	@XmlElement
	public void setPosY(int posY) {
		this.posY = posY;
	}

	@XmlElement
	public void setWidth(int width) {
		this.width = width;
	}

	@XmlElement
	public void setHeight(int height) {
		this.height = height;
	}

	@XmlElement
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	@XmlElement
	public void setFile(String file) {
		this.file = file;
	}
}
