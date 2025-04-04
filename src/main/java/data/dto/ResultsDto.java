package data.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Alias("ResultsDto")
public class ResultsDto {
	private int results_id;
	private String triggers;
	private String disease;
	private String result;
	@JsonFormat(pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
	private Timestamp writeday;
	private int myresult_id;
}
