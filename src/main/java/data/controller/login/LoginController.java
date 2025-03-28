package data.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		return "page5/login";
	}
	
	@GetMapping("/signup")
	public String singup() {
		return "page5/signup";
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "page5/mypage";
	}
}
