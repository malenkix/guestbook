package de.nadirhelix.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestbookController {

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping(value = { "/pinnwall", "/pinnwall/index" })
	public String getPinwall() {
		return "pinnwall/index";
	}
}
