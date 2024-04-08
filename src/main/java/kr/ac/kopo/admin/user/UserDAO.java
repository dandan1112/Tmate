package kr.ac.kopo.admin.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.admin.Pagination;

@Repository
public class UserDAO {

	@Autowired
	SqlSession sqlsession;
	
	public Map<String, String> selectUser(UserVO userVO) {	// 1:일치 / 2:아이디미존재 / 3:비밀번호불일치
		return sqlsession.selectOne("User.selectUser", userVO);
	}
	
	public List<UserVO> selectUserList(UserVO userVO) {
		return sqlsession.selectList("User.selectUserList", userVO);
	}
	
	public void updateUserSalt(UserVO userVO) {
		sqlsession.update("User.updateUserSalt", userVO);
	}
	
	public void insertUserInfo(UserVO userVO) {
		sqlsession.insert("User.insertUserInfo", userVO);
	}
	
	public Map<String, String> selectDupMail(String eml_addr) {
		return sqlsession.selectOne("User.selectDupMail", eml_addr);
	}
	
	public Map<String, String> selectUserId(String mbr_tel) {
		return sqlsession.selectOne("User.selectUserId", mbr_tel);
	}
	
	public void updateUserTempPswd(UserVO userVO) {
		sqlsession.update("User.updateUserTempPswd", userVO);
	}
	
	public String updateUserPswd(UserVO userVO) {
		String used = sqlsession.selectOne("User.selectUsedPswd", userVO);
		if(used.equals("Y")) {
			sqlsession.update("User.updateUserPswd", userVO);
			return "Y";
		} else {
			return "N";
		}
	}
	
	public void updateUserResign(UserVO userVO) {
		sqlsession.update("User.updateUserResign", userVO);
	}
	
	public UserVO selectUserInfo(UserVO userVO) {
		return sqlsession.selectOne("User.selectUserInfo", userVO);
	}
	
	public void updateUserDetail(UserVO userVO) {
		sqlsession.update("User.updateUserDetail", userVO);
	}
	
}
