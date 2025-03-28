package data.controller.checklist;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import data.dto.SymptomsDto;
import data.service.SymptomsService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/checklist")
public class ChecklistController {
	
	final SymptomsService stService;
	
	@GetMapping("")
	public String checklist(Model model) {
		List<SymptomsDto> list = stService.getCheckList();
		model.addAttribute("symptoms", list);
		
		return "page3/checklist";
	}
	
	@GetMapping("/result")
	public String test() {
		return "page3/checkresult";
	}
	
	
}
