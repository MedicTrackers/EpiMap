package data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import data.dto.SymptomsDto;

@Mapper
public interface SymptomsMapper {
	public List<SymptomsDto> getCheckList();
}