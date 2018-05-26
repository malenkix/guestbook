package de.nadirhelix.guestbook.image.dto;

/**
 * DTO containing all relevant data to create a background.
 * 
 * @author Phil
 */
public class BackgroundData {
	
	private boolean isImage;
	private String color;
	private String imageId;
	
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
