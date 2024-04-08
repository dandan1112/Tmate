package kr.ac.kopo.admin.device;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.admin.Pagination;
import kr.ac.kopo.admin.log.LogService;
import kr.ac.kopo.admin.log.LogVO;

@Controller
public class AdminDeviceController {

	@Autowired
	DeviceService deviceService;
	@Autowired
	LogService logService;
	
	@RequestMapping("/deviceList")
	public String deviceList(Model model, DeviceVO deviceVO) {
		
		if(deviceVO.getSearchText() != "") {
			model.addAttribute("searchText", deviceVO.getSearchText());
		}

		if (deviceVO.getPAGE_NUMBER() == null) {deviceVO.setPAGE_NUMBER(1);}
		
		// OFFSET
		deviceVO.setPAGE_SIZE(Pagination.PAGE_SIZE);
		deviceVO.setCURRENT_START((deviceVO.getPAGE_NUMBER() - 1) * Pagination.PAGE_SIZE);
		
		List<DeviceVO> deviceEList = deviceService.getDeviceEList(deviceVO);
		model.addAttribute("deviceEList", deviceEList);
		
		// 페이지 정보 세팅
		
		if(!deviceEList.isEmpty()) {
			Pagination pageinfo = new Pagination(deviceEList.get(0).getTOTAL(), deviceVO.getPAGE_NUMBER());
			model.addAttribute("pagingInfo", pageinfo);
		}
		
		return "deviceList";
	}
	
	@RequestMapping("/deviceDetail")
	public String deviceDetail(Model model, DeviceVO deviceVO, LogVO logVO) {
		
		Map<String, String> deviceInfo = deviceService.getDeviceInfo(deviceVO);

		model.addAttribute("deviceInfo", deviceInfo);
		
		if(logVO.getOrder() == null) logVO.setOrder("rsnt");
		
		logVO.setLog_cat("D");
		List<LogVO> logList = logService.getLogList(logVO);
		
		model.addAttribute("eqpmnt_info_sn", deviceVO.getEqpmnt_info_sn());
		model.addAttribute("logList", logList);
		model.addAttribute("order", logVO.getOrder());
		
		return "deviceDetail";
	}
	
}
