package de.nadirhelix.guestbook.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ColorProvider {
	
	private static final List<String> COLORS = Arrays.asList(
				"#FF0000",
				"#00FF00",
				"#0000FF"
			);

	public List<String> getColors() {
		return COLORS;
	}

	
	
}
