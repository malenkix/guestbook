package de.nadirhelix.guestbook.image.dto;

import java.util.Date;

/**
 * DTO containing all relevant data to create a Post.
 * 
 * @author Phil
 */
public class PostData {

	private Date date;
	private ImageData image;
	private TextData message;
	private BackgroundData background;
	private String subtext;
	private String wishes;
	private String name;
	
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

	public String getName() {
		return name;
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
	
	public void setName(String name) {
		this.name = name;
	}
}
