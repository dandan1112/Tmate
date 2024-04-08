package tmate.admin.log;

import java.util.List;

public interface LogService {

	public abstract void registerUserLog(LogVO logVO);
	
	public abstract List<LogVO> getLogList(LogVO logVO);
	
}
