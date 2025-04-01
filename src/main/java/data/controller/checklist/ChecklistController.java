package data.controller.checklist;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.dto.ResultsDto;
import data.dto.SymptomsDto;
import data.dto.UsersDto;
import data.service.ResultsService;
import data.service.SymptomsService;
import data.service.UsersService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/checklist")
public class ChecklistController {
	
	final UsersService userService;
	final SymptomsService stService;
	final ResultsService resultsService;
	
	@GetMapping("")
	public String checklist(Model model) {
		List<SymptomsDto> list = stService.getCheckList();
		model.addAttribute("symptoms", list);
		
		return "page3/checklist";
	}
	
	@GetMapping("/result")
	public String checkResult(Model model, HttpSession session,
			@RequestParam("symptoms") String symptoms) {
		ResultsDto dto = resultsService.findDisease(symptoms);
		if(session.getAttribute("loginUser") != null) {
			UsersDto udto = (UsersDto) session.getAttribute("loginUser");

			int users_id = udto.getUsers_id();
			int results_id = dto.getResults_id();
			
			resultsService.insertMyResult(users_id, results_id);
		}
		
		model.addAttribute("dto", dto);
		
		return "page3/checkresult";
	}
	
}
