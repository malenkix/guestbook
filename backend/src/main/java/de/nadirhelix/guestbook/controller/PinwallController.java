package de.nadirhelix.guestbook.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.nadirhelix.guestbook.pinwall.exception.UnexpectedUpdateIdException;
import de.nadirhelix.guestbook.post.service.BroadcastingService;
import de.nadirhelix.guestbook.post.util.PinnedPosts;
import de.nadirhelix.guestbook.resources.facade.ResourceFacade;

@Controller
@RequestMapping("/pinwall")
public class PinwallController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PinwallController.class);
	
	@Resource
	private BroadcastingService broadcastingService;
	
	@Resource
	private ResourceFacade resourceFacade;
	
	@PostMapping("/update")
	public ResponseEntity<PinnedPosts> updatePinwall(@RequestParam("lastUpdate") int lastUpdate) {
		try {
			PinnedPosts posts = broadcastingService.getPinnedPosts(lastUpdate);
			return ResponseEntity.ok().body(posts);
		} catch (UnexpectedUpdateIdException e) {
			LOG.warn("Tried to get update on post with an unexpected update id {}. current update id is {}",
					lastUpdate, e.getCurrentUpdateId());
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@GetMapping
	public ResponseEntity<byte[]> getPinwall() {
		try {
			byte[] result =  resourceFacade.getHtml("pinwall");
			return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(result);
		} catch (IOException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
