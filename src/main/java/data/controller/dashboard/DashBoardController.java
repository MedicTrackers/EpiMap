package data.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoardController {
	@GetMapping("/dashboard")
	public String hello() {
		return "page1/dashboard";
	}
}
