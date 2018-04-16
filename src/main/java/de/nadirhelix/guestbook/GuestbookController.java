package de.nadirhelix.guestbook;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestbookController {

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping("/{path}")
	public String getTemplate(@PathVariable String path) {

		if (StringUtils.isBlank(path)) {
			return "index";
		}

		return path + "/index";
	}

	@RequestMapping("/{path}/{name}")
	public String getTemplate(@PathVariable String path, @PathVariable String name) {

		if (StringUtils.isBlank(path)) {
			throw new NotFoundException();
		}

		if (StringUtils.isBlank(name)) {
			name = "index";
		}

		return path + "/" + name;
	}
}
