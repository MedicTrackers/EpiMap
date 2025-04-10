package data.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import data.dto.ChatbotDto2;
import data.mapper.ChatbotMapper2;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbacksService {
	final ChatbotMapper2 chatbotMapper;
	
	public int getAllFeedbacks() {
		return chatbotMapper.getAllFeedbacks();
	}
	
	public List<ChatbotDto2> getPagingFeedbacks(Map<String, Object> map) {
		return chatbotMapper.getPagingFeedbacks(map);
	}
	
}