package de.nadirhelix.guestbook.post.model;

import java.util.Date;

public class Post {
	
	private String id;
	private Date date;
	private String fileName;
	private String wishes;
	private String name;
	private boolean isPinned = true;
	
	public Post(String id, Date date, String fileName, String wishes, String name) {
		this.id = id;
		this.date = date;
		this.fileName = fileName;
		this.wishes = wishes;
		this.name = name;			
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
	
	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}
}