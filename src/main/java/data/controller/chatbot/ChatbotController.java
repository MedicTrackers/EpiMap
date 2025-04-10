package data.controller.chatbot;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import data.dto.ChatbotDto;
import data.service.ChatbotService;

@Controller
@RequestMapping("/clova")
public class ChatbotController {
	private final ChatbotService chatbotService;
	
	public ChatbotController(ChatbotService chatbotService) {
		this.chatbotService = chatbotService;
	}
	
	@GetMapping("/chatbot")
	public String chatbotPage() {
		return "layout/chatbot";
	}
	
	@PostMapping("/chat")
	@ResponseBody
	public ResponseEntity<String> chatWithClova(@RequestBody ChatbotDto requestDto) {
		String response = chatbotService.sendMessageToClova(requestDto.getMessage());
		return ResponseEntity.ok(response);
	}	// 사용자가 /clova/chat에 POST 요청을 보내면 chatbotService를 통해 clova에 메시지를 보내고, 응답을 클라이언트에게 돌려줌.
	
	@PostMapping("/feedback")
	public ResponseEntity<String> saveFeedback(@RequestParam("message") String message) {
		chatbotService.saveFeedback(message);
	    return ResponseEntity.ok("저장 완료");
	}

}