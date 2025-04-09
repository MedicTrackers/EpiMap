package data.controller.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import data.dto.ResultsDto;
import data.dto.ScrabDto;
import data.dto.UsersDto;
import data.service.ResultsService;
import data.service.ScrabService;
import data.service.UsersService;
import jakarta.servlet.http.HttpSession;




@Controller
public class MypageController {
	
	@Autowired
	UsersService usersService;
	@Autowired
	ResultsService resultsService;
	@Autowired
	ScrabService scrabService;
	
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
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); // ✅ 세션 무효화
	    return "redirect:/login"; // ✅ 로그인 페이지로 이동
	}
	
	// signup 페이지 불러오기
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
//	@PostMapping("/selfdiag")
//	@ResponseBody
//	public List<ResultsDto> selfdiag(HttpSession session) {
//		List<ResultsDto> list = new ArrayList<>();
//		
//		if (session.getAttribute("loginUser") == null){
//			return list;
//		}
//
//		UsersDto dto = (UsersDto) session.getAttribute("loginUser");
//		
//		return resultsService.getMyAllResult(dto.getUsers_id());
//	}
	//자가진단 데이터 불러오기
	@PostMapping("/selfdiag")
	@ResponseBody
	public Map<String, Object> selfdiagpage(@RequestParam("page") int page, HttpSession session) {
		UsersDto dto = (UsersDto) session.getAttribute("loginUser");
		int users_id = dto.getUsers_id();
		
		int size = 3;
		int offset = (page - 1) * size;
		
		List<ResultsDto> list = resultsService.getPagedResult(users_id, offset, size);
		int totalCount = resultsService.getMyResultCount(users_id);
		int totalPages = (int) Math.ceil((double) totalCount / size);
		
		Map<String, Object> res = new HashMap<>();
		res.put("list", list);
		res.put("totalPages", totalPages);
		return res;
	}
	//자가진단 데이터 삭제
	@GetMapping("/deleteMyResult")
	@ResponseBody
	public void deleteMyResult(@RequestParam("myresult_id") int myresult_id) {
		usersService.deleteMyResult(myresult_id);
	}	
	//뉴스페이지에서 뉴스 스크랩 기능
	@PostMapping("/scrabInsert")
	@ResponseBody
	public String scrabInsert(@RequestParam("url") String url,
	                        @RequestParam("title") String title,
	                        HttpSession session) {

	    UsersDto udto = (UsersDto) session.getAttribute("loginUser");
	    if (udto == null) {
	        System.out.println("로그인 정보 없음!");
	        return "fail";
	    }

	    int usersId = udto.getUsers_id(); // NullPointer 방지됨

	    ScrabDto dto = new ScrabDto();
	    dto.setUsers_id(usersId);
	    dto.setUrl(url);
	    dto.setTitle(title);

	    scrabService.insertScrab(dto);

	    return "success";
	}
	//스크랩 데이터 불러오기
	@GetMapping("/scrablist")
	@ResponseBody
	public Map<String, Object> getScrabs(@RequestParam("pageNum") int pageNum,
            HttpSession session) {
		UsersDto udto = (UsersDto) session.getAttribute("loginUser");
		int users_id = udto.getUsers_id();
		
		int perPage = 5;
		int start = (pageNum - 1) * perPage;
		
		List<ScrabDto> scrabs =	scrabService.getPagedScrabs(users_id, start, perPage);
		int totalCount = scrabService.getScrabsCount(users_id);
		int totalPage = (int) Math.ceil((double) totalCount / perPage);
		
		return Map.of(
		        "status", "ok",
		        "scrabs", scrabs,
		        "pageNum", pageNum,
		        "totalPage", totalPage
		    );
	}
	//스크랩 데이터 삭제
	@PostMapping("/deletescrab")
	@ResponseBody
	public Map<String, String> deleteScrab(@RequestParam("scrabs_id") int scrabs_id,
	                                       HttpSession session) {
	    UsersDto user = (UsersDto) session.getAttribute("loginUser");

	    usersService.deleteMyScrab(scrabs_id);
	    return Map.of("status", "success");
	}	
	//마이페이지 카카오계정 연동 기능
	@PostMapping("/connectKakao")
	@ResponseBody
	public String connectKakao(@RequestParam("kakao_id") String kakao_id,
	                           @RequestParam("kakao_nickname") String kakao_nickname,
	                           HttpSession session) {

	    UsersDto loginUser = (UsersDto) session.getAttribute("loginUser");
	    if (loginUser == null) return "fail";

	    UsersDto existing = usersService.findByKakaoId(kakao_id);

	    if (existing != null && existing.getUsers_id() != loginUser.getUsers_id()) {
	        return "already_connected";
	    }

	    loginUser.setKakao_id(kakao_id);
	    loginUser.setKakao_nickname(kakao_nickname);
	    usersService.connectKakao(loginUser);
	    session.setAttribute("loginUser", loginUser);
	    return "ok";
	}
	//마이페이지 카카오 계정 연동 시 중복확인
	@PostMapping("/isKakaoConnected")
	@ResponseBody
	public boolean isKakaoConnected(HttpSession session) {
	    UsersDto loginUser = (UsersDto) session.getAttribute("loginUser");
	    return loginUser != null && loginUser.getKakao_id() != null;
	}
	//로그인 페이지 카카오로그인 기능
	@PostMapping("/kakaoLogin")
	@ResponseBody
	public String kakaoLogin(@RequestParam("kakao_id") String kakao_id,
	                         @RequestParam("kakao_nickname") String kakao_nickname,
	                         HttpSession session) {

	    UsersDto existingUser = usersService.findByKakaoId(kakao_id);

	    if (existingUser != null) {
	        session.setAttribute("loginUser", existingUser);
	        return "login";
	    }

	    // 신규 회원가입 처리
	    UsersDto dto = new UsersDto();
	    dto.setUser_id("kakao_" + kakao_id); // 내부용 ID
	    dto.setKakao_id(kakao_id);
	    dto.setKakao_nickname(kakao_nickname);
	    usersService.insertKakaoUser(dto);

	    session.setAttribute("loginUser", dto);
	    return "signup";
	}
}
