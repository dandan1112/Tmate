package kr.ac.kopo.admin.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.admin.Pagination;

@Service
public class LogServiceImpl implements LogService{

	@Autowired
	LogDAO logDAO;

	public void registerUserLog(LogVO logVO) {
		logDAO.insertUserLog(logVO);
	}
	
	@Override
	public List<LogVO> getLogList(LogVO logVO) {
		return logDAO.selectLogList(logVO);
	}
	
}
