package data.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.ScrabDto;
import data.mapper.ScrabMapper;

@Service
public class ScrabService {
	@Autowired
	ScrabMapper scrabMapper;
	
	public void insertScrab(ScrabDto scrabDto) {
		scrabMapper.insertScrab(scrabDto);
	}
	
	public List<ScrabDto> getPagedScrabs(int users_id, int start, int perPage) {
		return scrabMapper.getPagedScrabs(users_id, start, perPage);
	}
	public int getScrabsCount(int users_id) {
		return scrabMapper.getScrabsCount(users_id);
	}
	
	public boolean isTitleScrapped(int users_id, String title) {
		return scrabMapper.isTitleScrapped(users_id, title);
	}
	
	public List<String> getScrabUrlsByUser(int users_id) {
	    return scrabMapper.getScrabUrlsByUser(users_id); // SELECT url FROM scrabs WHERE users_id = ?
	}
}
