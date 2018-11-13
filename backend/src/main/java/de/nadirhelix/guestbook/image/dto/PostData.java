package de.nadirhelix.guestbook.image.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO containing all relevant data to create a Post.
 * 
 * @author Phil
 */
@XmlRootElement
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
	
	@XmlElement
	public void setDate(Date date) {
		this.date = date;
	}

	@XmlElement
	public void setImage(ImageData image) {
		this.image = image;
	}

	@XmlElement
	public void setMessage(TextData message) {
		this.message = message;
	}

	@XmlElement
	public void setBackground(BackgroundData background) {
		this.background = background;
	}

	@XmlElement
	public void setSubtext(String subtext) {
		this.subtext = subtext;
	}

	@XmlElement
	public void setWishes(String wishes) {
		this.wishes = wishes;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
}
