package com.bigdata2017.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigdata2017.mysite.repository.UserDAO;
import com.bigdata2017.mysite.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDao;
	
	public boolean existUser(String email) {
		UserVO userVo = userDao.get(email);
		return userVo != null;
	}
	
	public boolean join(UserVO userVo) {
		return userDao.insert(userVo) == 1;
	}
	
	public UserVO getUser(String email, String password) {
		
		UserVO userVo = userDao.get(email, password);
		
		return userVo;
	}
	
	public UserVO getUser(Long no) {
		
		return userDao.get(no);
	}
	
	public boolean modifyUser(UserVO userVo) {
		
		return userDao.update(userVo) == 1;
	}
}











