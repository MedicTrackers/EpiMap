package data.controller.statistics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {
	@GetMapping("/statistics")
	public String hello() {
		return "page0/statistics";
	}
}
