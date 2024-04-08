package tmate.admin.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
