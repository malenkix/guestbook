package de.nadirhelix.guestbook.config;

import java.util.Arrays;
import java.util.List;

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

	public List<String> getColors() {
		return COLORS;
	}

	
	
}
