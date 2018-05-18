package de.nadirhelix.image.dto;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * DTO containing all relevant data to create a Post.
 * 
 * @author Phil
 */
public class PostData {

	private Date date = null;
	private ImageData image = null;
	private TextData message = null;
	private BackgroundData background = null;
	private String subtext = StringUtils.EMPTY;
	private String wishes = StringUtils.EMPTY;
	
	public Date getDate() {
		return date;
	}
	
	public ImageData getImage() {
		return image;
	}
	
	public TextData getMessage() {
		return message;
	}
	
	public BackgroundData getBackground() {
		return background;
	}
	
	public String getSubtext() {
		return subtext;
	}
	
	public String getWishes() {
		return wishes;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setImage(ImageData image) {
		this.image = image;
	}
	
	public void setMessage(TextData message) {
		this.message = message;
	}
	
	public void setBackground(BackgroundData background) {
		this.background = background;
	}
	
	public void setSubtext(String subtext) {
		this.subtext = subtext;
	}
	
	public void setWishes(String wishes) {
		this.wishes = wishes;
	}
}
