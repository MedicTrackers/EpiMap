package data.service;

import java.util.List;

import org.springframework.stereotype.Service;

import data.dto.SymptomsDto;
import data.mapper.SymptomsMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SymptomsService {
	final SymptomsMapper stMapper;
	
	/**
	 * 증상 리스트를 가져온다.
	 * @return : List<SymptomsDto> 형식의 리스트
	 */
	public List<SymptomsDto> getCheckList() {
		return stMapper.getCheckList();
	}
}
