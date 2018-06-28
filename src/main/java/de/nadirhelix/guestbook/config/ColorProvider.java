package de.nadirhelix.guestbook.config;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ColorProvider {
	
	private static final List<String> COLORS = Arrays.asList(
				"#DED083",
				"#DE9236",
				"#BE9752",
				"#E3B4B0",
				"#98B28A",
				"#848C77",
				"#599560",
				"#586987",
				"#cdcdcd"
			);
	
	private static final Map<String, String> FONT_COLORS = new LinkedHashMap<>();
	
	static {
		FONT_COLORS.put("Anthrazit", "#333");
		FONT_COLORS.put("Marine", "#338");
		FONT_COLORS.put("Schnee", "#ddd");
	}
	
	public List<String> getColors() {
		return COLORS;
	}

	public Map<String, String> getFontColors() {
		return FONT_COLORS;
	}

	
	
}
