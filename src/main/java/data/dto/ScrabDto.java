package data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("ScrabDto")
public class ScrabDto {
	private int scrabs_id;
	private int users_id;
	private String title;
	private String url;
}
