package data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import data.dto.ResultsDto;

@Mapper
public interface ResultsMapper {
	public List<ResultsDto> getAllResult();
	public void insertMyResult(Map<String, Object> paramMap);
	public List<ResultsDto> getMyAllResult(int users_id);
	public List<ResultsDto> getPagedResult(@Param("users_id") int users_id,@Param("offset") int offset,@Param("size") int size);
	public int getMyResultCount(@Param("users_id") int users_id);
}
