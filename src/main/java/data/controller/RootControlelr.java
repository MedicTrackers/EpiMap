package data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootControlelr {
	@RequestMapping("/")
	public String hello() {
		return "layout/root";
	}
}
