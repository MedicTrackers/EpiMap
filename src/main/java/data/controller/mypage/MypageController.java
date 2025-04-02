package data.controller.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import data.dto.UsersDto;
import data.service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MypageController {
	
	@Autowired
	UsersService usersService;
	
	@GetMapping("/login")
	public String loginform(Model model) {
		model.addAttribute("users",	new UsersDto());
		return "page5/login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("users") UsersDto usersdto, HttpSession session, Model model) {
		UsersDto loginUser = usersService.loginUsers(usersdto);
		
		if(loginUser != null) {
			session.setAttribute("loginUser", loginUser);
			return "redirect:/mypage";
		} else {
			model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다");
			return "page5/login";
		}
	}
	
	@GetMapping("/signup")
	public String singupform(Model model) {
		model.addAttribute("users", new UsersDto());
		return "page5/signup";
	}
	
	@PostMapping("/signup")
	public String singup(@ModelAttribute("users") UsersDto usersDto) {
		usersService.insertUsers(usersDto);
		return "page5/login";
	}
	
	@GetMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
	    UsersDto usersdto = (UsersDto) session.getAttribute("loginUser");
	    if (usersdto == null) {
	        return "redirect:/login";
	    }
	    model.addAttribute("users", usersdto);
	    return "page5/mypage";
	}
	
	@GetMapping("/checkId")
	@ResponseBody
	public String checkId(@RequestParam("userId") String userId) {
		boolean checkUserId = usersService.checkUserId(userId);
		if(checkUserId) {
			return "fail";
		} 
		return "pass";
	}
	
}
