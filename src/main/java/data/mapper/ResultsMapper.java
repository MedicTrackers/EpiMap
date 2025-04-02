package data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import data.dto.ResultsDto;

@Mapper
public interface ResultsMapper {
	public List<ResultsDto> getAllResult();
	public void insertMyResult(Map<String, Object> paramMap);
	public List<ResultsDto> getMyAllResult(int users_id);
}
