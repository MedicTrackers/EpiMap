package data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import data.dto.ResultsDto;

@Mapper
public interface ResultsMapper {
	public List<ResultsDto> getAllResult();
}
