package data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("ChatbotDto")
public class ChatbotDto {
	private String message;
}
