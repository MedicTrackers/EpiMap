package data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import data.dto.UsersDto;

@Mapper
public interface UsersMapper {
	
	public void insertUsers(UsersDto usersdto);
	public UsersDto loginUsers(UsersDto usersdto);
	public int checkUserId(String userId);
	public UsersDto getSelectById(int users_id);
	public void deleteMyResult(int myresult_id);
	public int deleteMyScrab(@Param("scrabs_id") int scrabs_id);
	public void connectKakao(@Param("users_id") int users_id,
	                      	 @Param("kakao_id") String kakao_id,
	                      	 @Param("kakao_nickname") String kakao_nickname);
	public UsersDto findById(@Param("users_id") int users_id);
}
