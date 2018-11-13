package de.nadirhelix.guestbook.post.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Post {

	private String id;
	private Date date;
	private String fileName;
	private String wishes;
	private String name;
	private boolean isPinned;

	public Post(String id, Date date, String fileName, String wishes,
			String name) {
		this.id = id;
		this.date = date;
		this.fileName = fileName;
		this.wishes = wishes;
		this.name = name;
		isPinned = true;
	}

	/**
	 * Constructor used for JAXB
	 */
	public Post() {
		//
	}

	public String getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public String getFileName() {
		return fileName;
	}

	public String getWishes() {
		return wishes;
	}

	public String getName() {
		return name;
	}

	public boolean isPinned() {
		return isPinned;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	@XmlElement
	public void setDate(Date date) {
		this.date = date;
	}

	@XmlElement
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@XmlElement
	public void setWishes(String wishes) {
		this.wishes = wishes;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute
	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}

}