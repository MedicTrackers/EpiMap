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
	// 사용자가 /clova/chat에 POST 요청을 보내면 chatbotService를 통해 clova에 메시지를 보내고, 응답을 클라이언트에게 돌려줌.
}