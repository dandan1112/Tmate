package kr.ac.kopo.admin.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.admin.Pagination;

@Controller
public class AdminLogController {
	
	@Autowired
	LogService logService;

	@RequestMapping("/logListUser")
	public String logListUser(Model model, LogVO logVO) {
		
		if(logVO.getSearchText() != "") {
			model.addAttribute("searchText", logVO.getSearchText());
		}
		if(logVO.getStartDate() != "" && logVO.getEndDate() != "") {
			model.addAttribute("startDate", logVO.getStartDate());
			model.addAttribute("endDate", logVO.getEndDate());
		}
		
		if (logVO.getPAGE_NUMBER() == null) {logVO.setPAGE_NUMBER(1);}

		// OFFSET
		logVO.setPAGE_SIZE(Pagination.PAGE_SIZE);
		logVO.setCURRENT_START((logVO.getPAGE_NUMBER() - 1) * Pagination.PAGE_SIZE);
		
		logVO.setLog_cat("U");
		List<LogVO> logList = logService.getLogList(logVO);
		model.addAttribute("logList", logList);
		
		// 페이지 정보 세팅
		if(!logList.isEmpty()) {
			Pagination pageinfo = new Pagination(logList.get(0).getTOTAL(), logVO.getPAGE_NUMBER());
			model.addAttribute("pagingInfo", pageinfo);
		}
		
		model.addAttribute("tab_id", "tab1");
		
		return "deviceLog";
	}
	
	@RequestMapping("/logListDevice")
	public String logListDevice(Model model, LogVO logVO) {
		
		if(logVO.getSearchText() != "") {
			model.addAttribute("searchText", logVO.getSearchText());
		}
		if(logVO.getStartDate() != "" && logVO.getEndDate() != "") {
			model.addAttribute("startDate", logVO.getStartDate());
			model.addAttribute("endDate", logVO.getEndDate());
		}
		
		if (logVO.getPAGE_NUMBER() == null) {logVO.setPAGE_NUMBER(1);}

		// OFFSET
		logVO.setPAGE_SIZE(Pagination.PAGE_SIZE);
		logVO.setCURRENT_START((logVO.getPAGE_NUMBER() - 1) * Pagination.PAGE_SIZE);
		
		logVO.setLog_cat("D");
		List<LogVO> logList = logService.getLogList(logVO);
		model.addAttribute("logList", logList);
		
		// 페이지 정보 세팅
		if(!logList.isEmpty()) {
			Pagination pageinfo = new Pagination(logList.get(0).getTOTAL(), logVO.getPAGE_NUMBER());
			model.addAttribute("pagingInfo", pageinfo);
		}
		
		model.addAttribute("tab_id", "tab2");
		
		return "deviceLog";
	}
	
}
