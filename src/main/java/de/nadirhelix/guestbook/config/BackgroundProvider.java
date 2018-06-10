package de.nadirhelix.guestbook.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BackgroundProvider {
	
	private static final String PATH = "/backgrounds/";

	private static final String SUFFIX = "_thumb.png";
	
	private static final List<String> BACKGROUNDS = Arrays.asList(
				PATH + '0' + SUFFIX,
				PATH + '1' + SUFFIX,
				PATH + '2' + SUFFIX,
				PATH + '3' + SUFFIX
			);
			

	public List<String> getBackgrounds() {
		return BACKGROUNDS;
	}

}
