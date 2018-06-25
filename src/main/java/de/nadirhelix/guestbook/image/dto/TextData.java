package de.nadirhelix.guestbook.image.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO containing all relevant data to create a text.
 * Will be transformed to a {@link TextImage}.
 * 
 * @author Phil
 */
@XmlRootElement
public class TextData {
	
	private String content;
	private String color;
	private int size;
	private int posX;
	private int posY;
	private float rotation;
	private String font;
	
	public String getContent() {
		return content;
	}
	
	public String getColor() {
		return color;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public String getFont() {
		return font;
	}

	@XmlElement
	public void setContent(String content) {
		this.content = content;
	}

	@XmlElement
	public void setColor(String color) {
		this.color = color;
	}

	@XmlElement
	public void setSize(int size) {
		this.size = size;
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
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	@XmlElement
	public void setFont(String font) {
		this.font = font;
	}
}
