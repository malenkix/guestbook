package de.nadirhelix.image.dto;

import org.apache.commons.lang3.StringUtils;

public class TextData {
	
	private String content = StringUtils.EMPTY;
	private String color = StringUtils.EMPTY;
	private int size = 0;
	private int posX = 0;
	private int posY = 0;
	private float rotation = 0;
	private String font = StringUtils.EMPTY;
	
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
