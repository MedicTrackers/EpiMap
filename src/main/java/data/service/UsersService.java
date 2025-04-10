package data.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.UsersDto;
import data.mapper.UsersMapper;

@Service
public class UsersService {
	
	@Autowired
	UsersMapper usersMapper;
	public void insertUsers(UsersDto usersdto) {
		usersMapper.insertUsers(usersdto);
	}
	
	public UsersDto loginUsers(UsersDto usersdto) {
		return usersMapper.loginUsers(usersdto);
	}
	public boolean checkUserId(String userId) {
		return usersMapper.checkUserId(userId) > 0;
	}
	
	public UsersDto getSelectById(int users_id) {
		return usersMapper.getSelectById(users_id);
	}
	
	public void deleteMyResult(int myresult_id) {
		usersMapper.deleteMyResult(myresult_id);
	}
	
	public void deleteMyScrab(int scrabs_id) {
		usersMapper.deleteMyScrab(scrabs_id);
	}
	
	public UsersDto findByKakaoId(String kakaoId) {
	    return usersMapper.findByKakaoId(kakaoId);
	}

	public void insertKakaoUser(UsersDto dto) {
	    usersMapper.insertKakaoUser(dto);
	}

	public void connectKakao(UsersDto dto) {
	    usersMapper.connectKakao(dto);
	}
}
