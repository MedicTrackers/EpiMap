package data.service;

import java.util.Arrays;
import java.util.List;

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
	
	
}
