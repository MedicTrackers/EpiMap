package data.controller.dashboard;

import org.springframework.web.bind.annotation.RequestMapping;

public class DashBoardController {
	@RequestMapping("/dashboard")
	public String hello() {
		return "page1/dashboard";
	}
}
