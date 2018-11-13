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

	private float posX;
	private float posY;
	private float width;
	private float height;
	private float rotation;
	private String file;

	@Override
	public float getPosX() {
		return posX;
	}

	@Override
	public float getPosY() {
		return posY;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public float getRotation() {
		return rotation;
	}

	public String getFile() {
		return file;
	}

	@XmlElement
	public void setPosX(float posX) {
		this.posX = posX;
	}

	@XmlElement
	public void setPosY(float posY) {
		this.posY = posY;
	}

	@XmlElement
	public void setWidth(float width) {
		this.width = width;
	}

	@XmlElement
	public void setHeight(float height) {
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
