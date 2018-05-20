package de.nadirhelix.image.dto;

import de.nadirhelix.image.util.Point;

/**
 * Interface for drawable components.
 * 
 * @author Phil
 */
public interface ComponentData {

	int getPosX();
	
	int getPosY();

	float getRotation();

	int getWidth();

	int getHeight();
	
	default int getWidthEffective() {
		return getWidth();
	}

	default int getHeightEffective() {
		return getHeight();
	}
	
	default Point getBuffer() {
		return new Point(getWidth() * 0.5f, getHeight() * 0.5f);
	}

}
