package de.nadirhelix.guestbook.image.processing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import processing.core.PGraphics;

/**
 * Utility class to convert a hexadecimal String representation of a color into
 * an int value that can be interpreted by processing.
 * 
 * @author Phil
 */
public class ColorConverter extends PGraphics {
	
	private static final Logger LOG = LoggerFactory.getLogger(ColorConverter.class);

	private static ColorConverter instance = new ColorConverter();
	
	private ColorConverter() {
		colorMode(RGB, 255, 255, 255, 255);
	}	
	
	/**
	 * <p>Converts a hexadecimal color to an int representation.</p>
	 * <p><table border="1">
	 * <tr><th>Input</th><th>Interpretation</th></tr>
	 * <tr><td>#ffffff</td><td>white RGB(255,255,255)</td></tr>
	 * <tr><td>#fff</td><td>white RGB(255,255,255)</td></tr>
	 * <tr><td>#000000</td><td>black RGB(0,0,0)</td></tr>
	 * <tr><td>#000</td><td>black RGB(0,0,0)</td></tr>
	 * </table></p>
	 * <p>Will default to <b><i>0</i></b> if any problems occur with parsing the given input.</p>
	 * 
	 * @param 
	 * 		input color as hexadecimal
	 * @return int value for given input.
	 */
	public static int convert(String input) {
		synchronized (instance) {
			return instance.convertInternal(input);
		}
	}
	
	private int convertInternal(String input) {
		if (input != null && input.startsWith("#")) {
			String hexColor = input.substring(1);
			
			String clRed = "0";
			String clGreen = "0";
			String clBlue = "0";
			
			switch (hexColor.length()) {
			
			case 3: 
				clRed = hexColor.substring(0,1) + hexColor.substring(0,1);
				clGreen = hexColor.substring(1,2) + hexColor.substring(1,2);
				clBlue = hexColor.substring(2,3) + hexColor.substring(2,3);
				break;
				
			case 6:
				clRed = hexColor.substring(0,2);
				clGreen = hexColor.substring(2,4);
				clBlue = hexColor.substring(4,6);
				break;
			}
			
			return color(getColorValue(clRed), getColorValue(clGreen), getColorValue(clBlue));
			
		}	
		
		return 0;
	}

	private int getColorValue(String stringValue) {
		try {
			return Integer.parseInt(stringValue, 16);
		} catch (NumberFormatException e) {
			LOG.debug("invalid number value: {}", stringValue);
			return 0;
		}
	}
	
	
}
