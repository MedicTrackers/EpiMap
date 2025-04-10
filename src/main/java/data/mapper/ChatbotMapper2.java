package data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import data.dto.ChatbotDto2;

@Mapper
public interface ChatbotMapper2 {
	public int getAllFeedbacks();
	public List<ChatbotDto2> getPagingFeedbacks(Map<String, Object> map);
}
