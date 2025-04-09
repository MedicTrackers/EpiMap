package data.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import data.dto.ChatbotDto;
import data.mapper.ChatbotMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbacksService {
	final ChatbotMapper chatbotMapper;
	
	public int getAllFeedbacks() {
		return chatbotMapper.getAllFeedbacks();
	}
	
	public List<ChatbotDto> getPagingFeedbacks(Map<String, Object> map) {
		return chatbotMapper.getPagingFeedbacks(map);
	}
	
}