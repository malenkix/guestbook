package de.nadirhelix.guestbook.image.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO containing all relevant data to create a background.
 * 
 * @author Phil
 */
@XmlRootElement
public class BackgroundData {
	
	private boolean isImage;
	private String color;
	private String imageId;
	
	public boolean isImage() {
		return isImage;
	}
	
	/*
	 * only used for marshalling
	 */
	public boolean getIsImage() {
		return isImage;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getImageId() {
		return imageId;
	}

	@XmlElement
	public void setIsImage(boolean isImage) {
		this.isImage = isImage;
	}

	@XmlElement
	public void setColor(String color) {
		this.color = color;
	}

	@XmlElement
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	
	
}
