package de.nadirhelix.guestbook.image.dto;

import de.nadirhelix.guestbook.image.util.Point;

/**
 * Interface for drawable components.
 * 
 * @author Phil
 */
public interface ComponentData {

	float getPosX();
	
	float getPosY();

	float getRotation();

	float getWidth();

	float getHeight();
	
	default float getWidthEffective() {
		return getWidth();
	}

	default float getHeightEffective() {
		return getHeight();
	}
	
	default Point getBuffer() {
		return new Point(getWidthEffective() * 0.5f, getHeightEffective() * 0.5f);
	}

}
