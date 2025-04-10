package data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import data.dto.FeedbackDto;

@Mapper
public interface FeedbackMapper {
	public int getAllFeedbacks();
	public List<FeedbackDto> getPagingFeedbacks(Map<String, Object> map);
}
