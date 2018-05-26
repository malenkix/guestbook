package de.nadirhelix.guestbook;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GuestbookApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(GuestbookApplication.class);
	    builder.headless(false).run(args);
	}
}
