package data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import data.dto.ScrabDto;

@Mapper
public interface ScrabMapper {
	
	public void insertScrab(ScrabDto scrabDto);
	public List<ScrabDto> getPagedScrabs(
			@Param("users_id") int users_id,
			@Param("start") int start,
			@Param("perPage") int perPage);
	public int getScrabsCount(@Param("users_id") int users_id);
	public boolean isTitleScrapped(@Param("users_id") int users_Id,
             @Param("title") String title);
}
