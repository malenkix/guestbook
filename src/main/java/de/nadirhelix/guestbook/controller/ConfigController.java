package de.nadirhelix.guestbook.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.nadirhelix.guestbook.config.BackgroundProvider;
import de.nadirhelix.guestbook.config.ColorProvider;

@RestController
public class ConfigController {

	@Resource
	private ColorProvider colorProvider;
	
	@Resource
	private BackgroundProvider backgroundProvider;
	
	@GetMapping("/colors")
	public ResponseEntity<List<String>> getColors() {
		List<String> result = colorProvider.getColors();
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/backgrounds")
	public ResponseEntity<List<String>> getBackgrounds() {
		List<String> result = backgroundProvider.getBackgrounds();
		return ResponseEntity.ok(result);
	}
	
	
	
}
