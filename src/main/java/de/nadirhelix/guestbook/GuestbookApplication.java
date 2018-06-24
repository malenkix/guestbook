package de.nadirhelix.guestbook;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class GuestbookApplication {

	private static final String DEV_MODE_KEY = "-DdevMode=";
	private static boolean isDevMode = false;

	public static void main(String[] args) {
		for (String arg : args) {
			if (StringUtils.isNotEmpty(arg) && arg.contains(DEV_MODE_KEY)) {
				String value = arg.substring(DEV_MODE_KEY.length());
				isDevMode = BooleanUtils.toBoolean(value);
			}
		}
		SpringApplicationBuilder builder = new SpringApplicationBuilder(GuestbookApplication.class);
		builder.headless(false).run(args);
	}

	public static boolean isDevMode() {
		return isDevMode;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}
}
