package kr.ac.kopo.admin.log;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.admin.Pagination;

@Repository
public class LogDAO {

	@Autowired
	SqlSession sqlsession;
	
	public void insertUserLog(LogVO logVO) {
		sqlsession.insert("Log.insertUserLog", logVO);
	}
	
	public List<LogVO> selectLogList(LogVO logVO) {
		return sqlsession.selectList("Log.selectLogList", logVO);
	}
	
}
