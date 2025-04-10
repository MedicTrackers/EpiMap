package data.mapper;

import org.apache.ibatis.annotations.Mapper;

import data.dto.ChatbotDto;

@Mapper
public interface ChatbotMapper {
	public void insertChat(String message);
}
