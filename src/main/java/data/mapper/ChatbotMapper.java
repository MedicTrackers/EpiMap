package data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import data.dto.ChatbotDto;

@Mapper
public interface ChatbotMapper {
	public int getAllFeedbacks();
	public List<ChatbotDto> getPagingFeedbacks(Map<String, Object> map);
}
