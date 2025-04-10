package data.controller.chatbot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.dto.FeedbackDto;
import data.dto.UsersDto;
import data.service.FeedbacksService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FeedbacksController {
	final FeedbacksService feedService;
	
	@GetMapping("/feedbacks")
	public String feedbakcs(HttpSession session, Model model,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		UsersDto usersdto = (UsersDto) session.getAttribute("loginUser");
		
	    if (usersdto == null) {
	        return "redirect:/login";
	    }
	    
	    if(!usersdto.isAdmin()) {
	    	return "redirect:/statistics";
	    }
		
	    int perPage = 5; // 페이지 당 글 개수
        int perBlock = 3; // 블럭 당 페이지 개수
        int totalCnt = feedService.getAllFeedbacks();
        int totalPage = (int) Math.ceil((double) totalCnt / perPage); // 총 페이지 수
        int startNum = (pageNum - 1) * perPage; // 각 페이지에서 가져올 시작번호
	    
        int startPage = ((pageNum - 1) / perBlock) * perBlock + 1;
        int endPage = Math.min(startPage + perBlock - 1, totalPage);
        
        Map<String, Object> map = new HashMap<>();
        map.put("startNum", startNum);
        map.put("perPage", perPage);
        
        List<FeedbackDto> feedlist = feedService.getPagingFeedbacks(map);
        
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("feedbacks", feedlist);
        
		return "page6/feedbacks";
	}
	
}
