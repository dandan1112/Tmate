package kr.ac.kopo.admin.user;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.admin.Pagination;

public interface UserService {

	public abstract Map<String, String> checkUser(UserVO userVO);
	public abstract List<UserVO> getUserList(UserVO userVO);
	public abstract void reflectUserSalt(UserVO userVO);
	
	public abstract void registerUserInfo(UserVO userVO);
	
	public abstract Map<String, String> checkDupMail(String eml_addr);
	
	public abstract Map<String, String> findUserId(String mbr_tel);
	
	public abstract void setUserTempPswd(UserVO userVO);
	
	public abstract String changeUserPswd(UserVO userVO);
	
	public abstract void reflectUserResign(UserVO userVO);
	
	public abstract UserVO getUserInfo(UserVO userVO);
	public abstract void changeUserDetail(UserVO userVO);
	
}
