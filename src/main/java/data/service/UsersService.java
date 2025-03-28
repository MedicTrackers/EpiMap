package data.service;

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
}
