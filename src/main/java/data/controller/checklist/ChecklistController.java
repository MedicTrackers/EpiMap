package data.controller.checklist;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.dto.ResultsDto;
import data.dto.SymptomsDto;
import data.service.ResultsService;
import data.service.SymptomsService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/checklist")
public class ChecklistController {
	
	final SymptomsService stService;
	final ResultsService resultsService;
	
	@GetMapping("")
	public String checklist(Model model) {
		List<SymptomsDto> list = stService.getCheckList();
		model.addAttribute("symptoms", list);
		
		return "page3/checklist";
	}
	
	@GetMapping("/result")
	public String checkResult(Model model,
			@RequestParam("symptoms") String symptoms) {
		ResultsDto dto = resultsService.findDisease(symptoms);
		
		model.addAttribute("dto", dto);
		
		return "page3/checkresult";
	}
	
	
}
