package org.travelmate.tmate.admin.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDAO;
	
	@Override
	public Map<String, String> checkUser(UserVO userVO) {
		return userDAO.selectUser(userVO);
	}

	@Override
	public List<UserVO> getUserList(UserVO userVO) {
		return userDAO.selectUserList(userVO);
	}
	
	@Override
	public void reflectUserSalt(UserVO userVO) {
		userDAO.updateUserSalt(userVO);
	}
	
	@Override
	public void registerUserInfo(UserVO userVO) {
		userDAO.insertUserInfo(userVO);
	}
	
	@Override
	public Map<String, String> checkDupMail(String eml_addr) {
		return userDAO.selectDupMail(eml_addr);
	}
	
	@Override
	public Map<String, String> findUserId(String mbr_tel) {
		return userDAO.selectUserId(mbr_tel);
	}
	
	@Override
	public void setUserTempPswd(UserVO userVO) {
		userDAO.updateUserTempPswd(userVO);
	}
	
	@Override
	public String changeUserPswd(UserVO userVO) {
		return userDAO.updateUserPswd(userVO);
	}
	
	@Override
	public void reflectUserResign(UserVO userVO) {
		userDAO.updateUserResign(userVO);
	}
	
	@Override
	public UserVO getUserInfo(UserVO userVO) {
		return userDAO.selectUserInfo(userVO);
	}

	@Override
	public void changeUserDetail(UserVO userVO) {
		userDAO.updateUserDetail(userVO);
	}
	
}
