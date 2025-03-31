package data.mapper;

import org.apache.ibatis.annotations.Mapper;

import data.dto.UsersDto;

@Mapper
public interface UsersMapper {
	
	public void insertUsers(UsersDto usersdto);
	public UsersDto loginUsers(UsersDto usersdto);
	public int checkUserId(String userId);
	public UsersDto getSelectById(int users_id);
}
