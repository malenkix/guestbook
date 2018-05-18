package de.nadirhelix.image.dto;

import org.apache.commons.lang3.StringUtils;

/**
 * DOT containing all relevant data to create a background.
 * 
 * @author Phil
 */
public class BackgroundData {
	
	private boolean isImage = false;
	private String color = StringUtils.EMPTY;
	private String imageId = StringUtils.EMPTY;
	
	public boolean isImage() {
		return isImage;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getImageId() {
		return imageId;
	}
	
	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	
	
}
