package data.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import data.dto.ResultsDto;
import data.mapper.ResultsMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultsService {
	
	final ResultsMapper resultsMapper;
	
	/**
	 * 자가진단 결과를 반환하는 함수
	 * @param : String symptoms
	 * @return: ResultsDto 
	 */
	public ResultsDto findDisease(String symptoms) {
		List<ResultsDto> results = resultsMapper.getAllResult();
		List<String> inputSymptoms = Arrays.asList(symptoms.split(","));
		ResultsDto bestMatch = null;
		int maxMatches = 0;
		
		for(ResultsDto result : results) {
			if(result.getResults_id() == 10) {
				continue;
			}
			
			// 받은 문자열 중에서 얼마나 포함되어있는지 갯수를 세서 적합한 데이터 찾기 
			List<String> triggerList = Arrays.asList(result.getTriggers().split(","));
			long matchCount = inputSymptoms.stream().filter(triggerList::contains).count();
			
			if(matchCount > maxMatches) {
				maxMatches = (int)matchCount;
				bestMatch = result;
			}
			
		}
		
		return (bestMatch != null && maxMatches > 1) ? bestMatch:results.stream()
				.filter(r->r.getTriggers().equals("X"))
				.findFirst().orElse(null);
	}
	
	/**
	 * 내 자가진단 결과 저장
	 * @param : int users_id
	 * @param : int results_id
	 */
	public void insertMyResult(int users_id, int results_id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("users_id", users_id);
		paramMap.put("results_id", results_id);
		
		resultsMapper.insertMyResult(paramMap);
	}
	
	/**
	 * 내 자가진단 목록 반환
	 * @param : int users_id
	 */
	public List<ResultsDto> getMyAllResult(int users_id) {
		return resultsMapper.getMyAllResult(users_id);
	}
	/** 
	 * @param users_id
	 * @param offset
	 * @param size
	 */
	public List<ResultsDto> getPagedResult(int users_id, int offset, int size) {
		return resultsMapper.getPagedResult(users_id, offset, size);
	}
	/**
	 * @param users_id
	 */
	public int getMyResultCount(int users_id) {
		return resultsMapper.getMyResultCount(users_id);
	}

}
