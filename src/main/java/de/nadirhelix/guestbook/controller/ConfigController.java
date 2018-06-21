package de.nadirhelix.guestbook.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.nadirhelix.guestbook.config.BackgroundProvider;
import de.nadirhelix.guestbook.config.ColorProvider;
import de.nadirhelix.guestbook.resources.util.FontsProvider;

/**
 * This controller fetches the essential information about colors, backgrounds and fonts
 * that are currently available for post creation.
 *   
 * @author Phil
 */
@RestController
public class ConfigController {

	@Resource
	private ColorProvider colorProvider;
	
	@Resource
	private BackgroundProvider backgroundProvider;

	/**
	 * Retrieves all available colors.
	 * 
	 * @return {@link ResponseEntity} with color list
	 */
	@GetMapping("/colors")
	public ResponseEntity<List<String>> getColors() {
		List<String> result = colorProvider.getColors();
		return ResponseEntity.ok(result);
	}

	/**
	 * Retrieves all available backgrounds.
	 * 
	 * @return {@link ResponseEntity} with background list
	 */
	@GetMapping("/backgrounds")
	public ResponseEntity<List<String>> getBackgrounds() {
		List<String> result = backgroundProvider.getBackgrounds();
		return ResponseEntity.ok(result);
	}

	/**
	 * Retrieves all available fonts.
	 * 
	 * @return {@link ResponseEntity} with font list
	 */
	@GetMapping("/fonts")
	public ResponseEntity<List<String>> getFonts() {
		List<String> result = FontsProvider.getFonts();
		return ResponseEntity.ok(result);
	}
	
	
	
}
