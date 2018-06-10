package de.nadirhelix.guestbook.image.dto;

/**
 * DTO containing all relevant data to create a text.
 * Will be transformed to a {@link TextImage}.
 * 
 * @author Phil
 */
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
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public void setFont(String font) {
		this.font = font;
	}
}
