package de.nadirhelix.guestbook.post.util;

public class PostUtil {

	public static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf('.'));
	}
	
}
