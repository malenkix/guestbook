package de.nadirhelix.guestbook.resources.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds the basic fonts that are used for the message of a post.
 * 
 * @author Phil
 */
public class FontsProvider {

	public static final String DEFAULT_MESSAGE_FONT = "Sansation-Regular.vlw";
	
	private static final Map<String, String> FONTS;
	
	static {
		FONTS = new HashMap<>();
		FONTS.put("Alice", "Alice-Regular.vlw");
		FONTS.put("Concert One", "ConcertOne-Regular.vlw");
		FONTS.put("Sansation", "Sansation-Regular.vlw");
		FONTS.put("Ubuntu Titling", "UbuntuTitling-Bold.vlw");
		FONTS.put("Walter Turncoat", "WalterTurncoat.vlw");
	}
	
	/**
	 * Returns a {@link List} of all font names.
	 * 
	 * @return {@link List} of font names
	 */
	public static List<String> getFonts() {
		return new ArrayList<>(FONTS.keySet());
	}
	
	/**
	 * Returns the file name of a font corresponding to the given font name.
	 * 
	 * @param fontName
	 * 			the name of the required font
	 * @return the file name of the font
	 */
	public static String getFileNameForFont(String fontName) {
		return FONTS.get(fontName);
	}
	
}
