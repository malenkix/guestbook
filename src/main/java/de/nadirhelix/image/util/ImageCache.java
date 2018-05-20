package de.nadirhelix.image.util;

import java.util.HashMap;
import java.util.Map;

import processing.core.PImage;

/**
 * Utility class which holds several images in memory.
 * 
 * @author Phil
 */
public class ImageCache {
	
	private static Map<String, PImage> imageCacheMap = new HashMap<>();

	/**
	 * Retrieves a {@link PImage} by id from cache.
	 *  
	 * @param imageId
	 * 			the given id.
	 * @return the {@link PImage} linked to the id or null if none exist.
	 */
	public static PImage get(String imageId) {
		synchronized (imageCacheMap) {
			return imageCacheMap.get(imageId);
		}
	}
	
	/**
	 * Stores a {@link PImage} with a given id in the cache.
	 * 
	 * @param imageId 
	 * 			the id of the image
	 * @param image
	 * 			the {@link PImage}
	 */
	public static void cache(String imageId, PImage image) {
		if (imageId == null || image == null) {
			return;
		}
		synchronized (imageCacheMap) {
			imageCacheMap.put(imageId, image);
		}
	}
	
	/**
	 * Clears the cached files.
	 */
	public static void clearCache() {
		synchronized (imageCacheMap) {
			imageCacheMap.clear();
		}
	}

}
