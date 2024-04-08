package kr.ac.kopo.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.admin.log.LogService;
import kr.ac.kopo.admin.log.LogVO;
import kr.ac.kopo.admin.user.UserService;
import kr.ac.kopo.admin.user.UserVO;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	LogService logService;
	
	@GetMapping("/Mlogin")
	//	http://localhost:8080/Mlogin?eml_addr=test@ggulb.net&pswd=111
	public JSONObject Mlogin(@RequestParam(required = true) String eml_addr, @RequestParam(required = true) String pswd
			, UserVO userVO, LogVO logVO) {
		
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		userVO.setEml_addr(eml_addr);
		userVO.setPswd(pswd);
		
		Map<String, String> checkInfo = userService.checkUser(userVO);
		
		 String rtnCode = checkInfo.get("rtnCode");
		 String rtnMsg = checkInfo.get("rtnMsg");
		 
		if(rtnCode.equals("3") || rtnCode.equals("4")) {	// 3-정상, 4-비밀번호재설정
			if (rtnCode.equals("3")) rtnMap.put("rtnCode", "1");
			if (rtnCode.equals("4")) rtnMap.put("rtnCode", "10");
			
			rtnMap.put("rtnMsg", RandomStringUtils.randomAlphabetic(32));	// 랜덤 키 저장
			
			List<UserVO> userList = userService.getUserList(userVO);
			userVO.setMbr_sn(userList.get(0).getMbr_sn());
			userVO.setSalt_vl(rtnMap.get("rtnMsg"));
			userService.reflectUserSalt(userVO);	// salt 값 저장
			
			logVO.setMbr_sn(userList.get(0).getMbr_sn());
			logVO.setLog_content("로그인: 성공 (eml_addr: "+eml_addr+")");
		} else {
			rtnMap.put("rtnCode", "0");
			if(rtnCode.equals("1")) {
				rtnMap.put("rtnMsg", rtnMsg);
			} else if(rtnCode.equals("2")) {
				rtnMap.put("rtnMsg", rtnMsg);
			}
			
			logVO.setLog_content("로그인: 실패 (eml_addr: "+eml_addr+"/"+rtnMsg+")");
		}
		
		// 로그
		logVO.setLog_cat("U");
		logService.registerUserLog(logVO);
		
		JSONObject obj = new JSONObject(rtnMap);

		return obj;
	}
	
	//	http://localhost:8080/MsignIn?mbr_name=추가테스트&eml_addr=test@ggulb.net&pswd=111&mbr_tel=010-2222-2222
	@GetMapping("/MsignIn")
	public JSONObject MsignIn(@RequestParam(required = true) String mbr_name, @RequestParam(required = true) String eml_addr, @RequestParam(required = true) String pswd, @RequestParam(required = true) String mbr_tel, 
			UserVO userVO, LogVO logVO) {
		
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		try{
			userVO.setMbr_name(mbr_name);
			userVO.setEml_addr(eml_addr);
			userVO.setPswd(pswd);
			userVO.setMbr_tel(mbr_tel);
			
			int idx = eml_addr.indexOf('@');
			String mbr_id = eml_addr.substring(0, idx);
			
			userVO.setMbr_id(mbr_id);
			
			userService.registerUserInfo(userVO);
			
			rtnMap.put("rtnCode", "1");
			
			List<UserVO> userList = userService.getUserList(userVO);
			logVO.setMbr_sn(userList.get(0).getMbr_sn());
			logVO.setLog_content("회원가입: 가입완료 (eml_addr: "+eml_addr+")");
			
		} catch(Exception e) {
			rtnMap.put("rtnCode", "0");
			rtnMap.put("rtnMsg", "회원가입을 실패하였습니다");
			
			logVO.setLog_content("회원가입: 실패 (eml_addr: "+eml_addr+")");
		}
		
		// 로그
		logVO.setLog_cat("U");
		logService.registerUserLog(logVO);
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;
	}
	
	//	http://localhost:8080/MisMailUsed?eml_addr=test@ggulb.net
	@GetMapping("/MisMailUsed")
	public JSONObject MisMailUsed(@RequestParam(required = true) String eml_addr) {
		
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		rtnMap = userService.checkDupMail(eml_addr);
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;
	}
	
	//	http://localhost:8080/Mresign?salt_vl=bwevpnNtczyTYTDdkgFBgPmAOvBIPVMb&eml_addr=test@ggulb.net
	@GetMapping("/Mresign")
	public JSONObject Mresign(@RequestParam(required = true) String salt_vl, @RequestParam(required = true) String eml_addr, UserVO userVO, LogVO logVO) {
		
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		try{
			userVO.setSalt_vl(salt_vl);
			userVO.setEml_addr(eml_addr);
			
			userService.reflectUserResign(userVO);
			
			rtnMap.put("rtnCode", "1");
			
			logVO.setLog_content("회원탈퇴: 탈퇴완료 (eml_addr: "+eml_addr+")");
			
		} catch(Exception e) {
			rtnMap.put("rtnCode", "0");
			rtnMap.put("rtnMsg", "회원탈퇴를 실패하였습니다");
			
			logVO.setLog_content("회원탈퇴: 실패 (eml_addr: "+eml_addr+")");
		}
		
		// 로그
		logVO.setLog_cat("U");
		logService.registerUserLog(logVO);
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;
	}
	
	//	http://localhost:8080/MfindID?mbr_tel=010-1111-2222
	@GetMapping("/MfindID")
	public JSONObject MfindID(@RequestParam(required = true) String mbr_tel, UserVO userVO, LogVO logVO) {
	
		Map<String, String> rtnMap = new HashMap<String, String>();
		
			userVO.setMbr_tel(mbr_tel);
			
			rtnMap = userService.findUserId(mbr_tel);
			
			if(rtnMap.get("rtnCode").equals("1")) {
				List<UserVO> userList = userService.getUserList(userVO);
				logVO.setMbr_sn(userList.get(0).getMbr_sn());
				logVO.setLog_content("아이디 찾기: 성공 (mbr_tel: "+mbr_tel+")");
			} else {
				logVO.setLog_content("아이디 찾기: 실패 (mbr_tel: "+mbr_tel+"/"+rtnMap.get("rtnCode")+")");
			}
		
		// 로그
		logVO.setLog_cat("U");
		logService.registerUserLog(logVO);
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;
	}
	
	//	http://localhost:8080/MfindPwd?eml_addr=test@ggulb.net
	@GetMapping("/MfindPwd")
	public JSONObject MfindPwd(@RequestParam(required = true) String eml_addr, UserVO userVO, LogVO logVO) {
	
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		try {
			String pswd = RandomStringUtils.randomAlphabetic(6);
			
			userVO.setEml_addr(eml_addr);
			userVO.setPswd(pswd);
			
			userService.setUserTempPswd(userVO);
			
			rtnMap.put("rtnCode", "1");
			rtnMap.put("rtnMsg", pswd);
			
			List<UserVO> userList = userService.getUserList(userVO);
			logVO.setMbr_sn(userList.get(0).getMbr_sn());
			logVO.setLog_content("비밀번호 찾기: 성공 (pwsd: "+pswd+"/eml_addr: "+eml_addr+")");
		} catch(Exception e) {
			rtnMap.put("rtnCode", "0");
			logVO.setLog_content("비밀번호 찾기: 실패 (eml_addr: "+eml_addr+")");
		}
		
		// 로그
		logVO.setLog_cat("U");
		logService.registerUserLog(logVO);
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;
	}
	
	//	http://localhost:8080/MchangePwd?eml_addr=test@ggulb.net&temp_pswd=nOhxip&pswd=222
	@GetMapping("/MchangePwd")
	public JSONObject MfindPwd(@RequestParam(required = true) String eml_addr, @RequestParam(required = true) String temp_pswd, @RequestParam(required = true) String pswd, 
			UserVO userVO, LogVO logVO) {
		
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		try {
			userVO.setEml_addr(eml_addr);
			userVO.setTemp_pswd(temp_pswd);
			userVO.setPswd(pswd);
			
			String sttus = userService.changeUserPswd(userVO);
			
			if (sttus == "Y") {
				rtnMap.put("rtnCode", "1");
				
				logVO.setLog_content("비밀번호 재설정: 성공 (eml_addr: "+eml_addr+")");
			} else if (sttus == "N") {
				rtnMap.put("rtnCode", "0");
				rtnMap.put("rtnMsg", "기존 비밀번호 불일치");
				
				logVO.setLog_content("비밀번호 재설정: 실패 (eml_addr: "+eml_addr+")");
			}
			
		} catch(Exception e) {
			rtnMap.put("rtnCode", "0");
			rtnMap.put("rtnMsg", "비밀번호 재설정을 실패하였습니다");
			
			logVO.setLog_content("비밀번호 재설정: 실패 (eml_addr: "+eml_addr+")");
		}
		
		// 로그
		logVO.setLog_cat("U");
		logService.registerUserLog(logVO);
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;
	}
	
}
