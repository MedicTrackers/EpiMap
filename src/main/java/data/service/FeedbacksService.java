package data.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import data.dto.FeedbackDto;
import data.mapper.FeedbackMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbacksService {
	final FeedbackMapper feedbackMapper;
	
	public int getAllFeedbacks() {
		return feedbackMapper.getAllFeedbacks();
	}
	
	public List<FeedbackDto> getPagingFeedbacks(Map<String, Object> map) {
		return feedbackMapper.getPagingFeedbacks(map);
	}
	
}