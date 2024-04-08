package kr.ac.kopo.admin.log;

import java.util.List;

import kr.ac.kopo.admin.Pagination;

public interface LogService {

	public abstract void registerUserLog(LogVO logVO);
	
	public abstract List<LogVO> getLogList(LogVO logVO);
	
}
