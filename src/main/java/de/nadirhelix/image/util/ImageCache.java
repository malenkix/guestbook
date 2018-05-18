package de.nadirhelix.image.util;

import java.util.HashMap;
import java.util.Map;

import processing.core.PImage;

public class ImageCache {
	
	private static Map<String, PImage> imageCacheMap = new HashMap<>();

	public static PImage get(String imageId) {
		synchronized (imageCacheMap) {
			return imageCacheMap.get(imageId);
		}
	}
	
	public static void cache(String imageId, PImage image) {
		if (imageId == null || image == null) {
			return;
		}
		synchronized (imageCacheMap) {
			imageCacheMap.put(imageId, image);
		}
	}
	
	public static void clearCache() {
		synchronized (imageCacheMap) {
			imageCacheMap.clear();
		}
	}

}
