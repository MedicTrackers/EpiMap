package data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("SymptomsDto")
public class SymptomsDto {
	private int idx;
	private String symptom;
}