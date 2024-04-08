package kr.ac.kopo.admin.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.kopo.admin.Pagination;
import kr.ac.kopo.admin.device.DeviceService;
import kr.ac.kopo.admin.device.DeviceVO;

@Controller
public class AdminUserController {

	@Autowired
	UserService userService;
	@Autowired
	DeviceService deviceService;
	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, String> login(UserVO userVO, HttpServletRequest request) {

		Map<String, String> rtnMap = new HashMap<String, String>();
		
		Map<String, String> checkInfo = userService.checkUser(userVO);
		
		 String rtnCode = checkInfo.get("rtnCode");
		 String rtnMsg = checkInfo.get("rtnMsg");
			
		if(rtnCode.equals("3")||rtnCode.equals("4")) {	// 3-정상, 4-비밀번호재설정
			if (rtnCode.equals("3")) rtnMap.put("rtnCode", "1");
			if (rtnCode.equals("4")) rtnMap.put("rtnCode", "10");
			
			request.getSession().invalidate();
			HttpSession session = request.getSession(true);	// 세션 생성
			session.setAttribute("eml_addr", userVO.getEml_addr());
		} else {
			rtnMap.put("rtnCode", "0");
			if(rtnCode.equals("1")) {
				rtnMap.put("rtnMsg", rtnMsg);
			} else if(rtnCode.equals("2")) {
				rtnMap.put("rtnMsg", rtnMsg);
			}
		}		
		
		return rtnMap;
	}
	
	@RequestMapping("/userList")
	public String userList(Model model, UserVO userVO) {
		
		userVO.setMbr_sn("");

		if(userVO.getSearchText() != "") {
			model.addAttribute("searchText", userVO.getSearchText());
		}
		
		if (userVO.getPAGE_NUMBER() == null) {userVO.setPAGE_NUMBER(1);}

		// OFFSET
		userVO.setPAGE_SIZE(Pagination.PAGE_SIZE);
		userVO.setCURRENT_START((userVO.getPAGE_NUMBER() - 1) * Pagination.PAGE_SIZE);
		
		List<UserVO> userList = userService.getUserList(userVO);
		model.addAttribute("userList", userList);
		
		// 페이지 정보 세팅
		if(!userList.isEmpty()) {
			Pagination pageinfo = new Pagination(userList.get(0).getTOTAL(), userVO.getPAGE_NUMBER());
			model.addAttribute("pagingInfo", pageinfo);
		}
		
		return "userList";
	}
	
	@RequestMapping("/userDetail")
	public String userDetail(Model model, UserVO userVO, DeviceVO deviceVO) {
		
		UserVO userInfo = userService.getUserInfo(userVO);
		
		List<DeviceVO> deviceList = deviceService.getDeviceList(deviceVO);
		
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("deviceList", deviceList);
		
		return "userDetail";
	}
	
	@RequestMapping("/changeDetail")
	@ResponseBody
	public Map<String, String> changeDetail(UserVO userVO) {
		Map<String, String> resMap = new HashMap<String, String>();
		try {
				userService.changeUserDetail(userVO);
				resMap.put("rtnCode", "0");
				resMap.put("rtnMsg", "회원정보 변경 완료");
		} catch(Exception e) {
			resMap.put("rtnCode", "1");
			resMap.put("rtnMsg", "회원정보 변경 실패");
		}
		return resMap;
	}
	
}
