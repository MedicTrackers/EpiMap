package data.controller.mypage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import data.dto.ResultsDto;
import data.dto.UsersDto;
import data.service.ResultsService;
import data.service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MypageController {
	
	@Autowired
	UsersService usersService;
	@Autowired
	ResultsService resultsService;
	
	@GetMapping("/login")
	public String loginform(Model model) {
		model.addAttribute("users",	new UsersDto());
		return "page5/login";
	}
	//login 로그인 기능
	@PostMapping("/login")
	public String login(@ModelAttribute("users") UsersDto usersdto, HttpSession session, Model model) {
		UsersDto loginUser = usersService.loginUsers(usersdto);
		
		if(loginUser != null) {
			session.setAttribute("loginUser", loginUser);
			session.setMaxInactiveInterval(3600);
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
	//signup 회원가입 기능
	@PostMapping("/signup")
	public String singup(@ModelAttribute("users") UsersDto usersDto) {
		usersService.insertUsers(usersDto);
		return "page5/login";
	}
	//login 시 mypage 로 이동 기능
	@GetMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
	    UsersDto usersdto = (UsersDto) session.getAttribute("loginUser");
	    if (usersdto == null) {
	        return "redirect:/login";
	    }
	    model.addAttribute("users", usersdto);
	    return "page5/mypage";
	}
	//signup 아이디 중복확인 기능
	@GetMapping("/checkId")
	@ResponseBody
	public String checkId(@RequestParam("userId") String userId) {
		boolean checkUserId = usersService.checkUserId(userId);
		if(checkUserId) {
			return "fail";
		} 
		return "pass";
	}
	//mypage 에 자가진단 결과 표시 기능
	@PostMapping("/selfdiag")
	@ResponseBody
	public List<ResultsDto> selfdiag(HttpSession session) {
		List<ResultsDto> list = new ArrayList<>();
		
		if (session.getAttribute("loginUser") == null){
			return list;
		}

		UsersDto dto = (UsersDto) session.getAttribute("loginUser");
		
		return resultsService.getMyAllResult(dto.getUsers_id());
	}
	
	@GetMapping("/deleteMyResult")
	@ResponseBody
	public void deleteMyResult(@RequestParam("myresult_id") int myresult_id) {
		usersService.deleteMyResult(myresult_id);
	}	
}
