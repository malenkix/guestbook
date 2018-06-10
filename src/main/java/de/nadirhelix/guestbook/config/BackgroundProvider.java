package de.nadirhelix.guestbook.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BackgroundProvider {
	
	private static final String PATH = "/backgrounds/";
	
	private static final List<String> BACKGROUNDS = Arrays.asList(
				PATH + "0.png",
				PATH + "1.png",
				PATH + "2.png",
				PATH + "3.png"
			);
			

	public List<String> getBackgrounds() {
		return BACKGROUNDS;
	}

}
