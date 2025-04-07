package data.controller.chatbot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import data.dto.ChatbotRequestDto;
import data.service.ChatbotService;

@RestController
@RequestMapping("/clova")
public class ChatbotController {
	private final ChatbotService chatbotService;
	
	public ChatbotController(ChatbotService chatbotService) {
		this.chatbotService = chatbotService;
	}
	
	@PostMapping("/chat")
	public ResponseEntity<String> chatWithClova(@RequestBody ChatbotRequestDto requestDto) {
		String response = chatbotService.sendMessageToClova(requestDto.getMessage());
		return ResponseEntity.ok(response);
	}
	
}