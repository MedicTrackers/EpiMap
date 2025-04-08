package data.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("UsersDto")
public class UsersDto {
	private int users_id;
	private String user_id;
	private String kakao_id;
	private String password;
	private String nickname;
	private boolean admin;
	private Timestamp createdday;
	private String kakao_nickname; 
}
