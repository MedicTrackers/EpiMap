package data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("ResultsDto")
public class ResultsDto {
	private int results_id;
	private String triggers;
	private String disease;
	private String result;
	
}
