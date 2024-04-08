package kr.ac.kopo.device.share;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.admin.device.DeviceService;
import kr.ac.kopo.admin.log.LogService;
import kr.ac.kopo.admin.log.LogVO;

@RestController
public class DeviceShareController {

	@Autowired
	DeviceService deviceService;
	@Autowired
	LogService logService;
	
	//	http://localhost:8080/MshareAdd?salt_vl=PnIgaDipUXOtrCcOscbBoLeqzrdcqxSC&eml_addr=test9@ggulb.net&eqpmnt_info_sn=6&eqpmnt_shr_role=100
	@GetMapping("/MshareAdd")
	public JSONObject MshareAdd(@RequestParam(required = true) String salt_vl, @RequestParam(required = true) String eml_addr, 
			@RequestParam(required = true) String eqpmnt_info_sn, @RequestParam(required = true) String eqpmnt_shr_role, Model model, ShareVO shareVO, LogVO logVO) {
		
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		String mbr_sn = deviceService.getMbrSnBySalt(salt_vl);
		
		try {
			shareVO.setMbr_sn(mbr_sn);
			shareVO.setEqpmnt_info_sn(eqpmnt_info_sn);
			shareVO.setEqpmnt_shr_role(eqpmnt_shr_role);
			
			String shr_mbr_sn = deviceService.getShrMbrSnByEml(eml_addr);
			shareVO.setShr_mbr_sn(shr_mbr_sn);
			
			String dupChk = deviceService.checkDupShrDeviceInfo(shareVO);
			
			if(dupChk.equals("N")) {
				deviceService.registerShrDeviceInfo(shareVO);
				
				rtnMap.put("rtnCode", "1");
				
				logVO.setLog_content("공유: 추가 (shr_mbr_sn: "+shr_mbr_sn+"/ eqpmnt_info_sn: "+eqpmnt_info_sn+")");
				logVO.setMbr_sn(mbr_sn);
				logVO.setEqpmnt_info_sn(eqpmnt_info_sn);
				
				// 로그
				logVO.setLog_cat("D");
				logService.registerUserLog(logVO);
			} else {
				rtnMap.put("rtnCode", "0");
				rtnMap.put("rtnMsg", "이미 등록된 기기 공유 정보 입니다");
			}
			
		} catch(Exception e) {
			rtnMap.put("rtnCode", "0");
			rtnMap.put("rtnMsg", "기기 공유 등록을 실패하였습니다");
		}
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;
	}
	
	//	http://localhost:8080/MshareDel?salt_vl=PnIgaDipUXOtrCcOscbBoLeqzrdcqxSC&shr_mbr_sn=15&eqpmnt_info_sn=6
	@GetMapping("/MshareDel")
	public JSONObject MshareDel(@RequestParam(required = true) String salt_vl, @RequestParam(required = true) String shr_mbr_sn, @RequestParam(required = true) String eqpmnt_info_sn,
			Model model,ShareVO shareVO, LogVO logVO) {
	
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		String mbr_sn = deviceService.getMbrSnBySalt(salt_vl);
		
		try {
			shareVO.setMbr_sn(mbr_sn);
			shareVO.setShr_mbr_sn(shr_mbr_sn);
			shareVO.setEqpmnt_info_sn(eqpmnt_info_sn);
			
			deviceService.removeShrDeviceInfo(shareVO);
			
			rtnMap.put("rtnCode", "1");
			
			logVO.setLog_content("공유: 삭제 (shr_mbr_sn: "+shr_mbr_sn+"/ eqpmnt_info_sn: "+eqpmnt_info_sn+")");
			logVO.setMbr_sn(mbr_sn);
			logVO.setEqpmnt_info_sn(eqpmnt_info_sn);
			
			// 로그
			logVO.setLog_cat("D");
			logService.registerUserLog(logVO);
		} catch(Exception e) {
			rtnMap.put("rtnCode", "0");
			rtnMap.put("rtnMsg", "기기 공유 삭제를 실패하였습니다");
		}
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;
	}
	
	//	http://localhost:8080/MshareList?salt_vl=PnIgaDipUXOtrCcOscbBoLeqzrdcqxSC
	@SuppressWarnings("unchecked")
	@GetMapping("/MshareList")
	public JSONArray MshareList(@RequestParam(required = true) String salt_vl, Model model, ShareVO shareVO) {
		
		String mbr_sn = deviceService.getMbrSnBySalt(salt_vl);
		
		shareVO.setMbr_sn(mbr_sn);

		List<ShareVO> shrDeviceList = deviceService.getShrDeviceList(shareVO);
		
		
		JSONArray ja = new JSONArray();
		
		for(ShareVO l : shrDeviceList) {
			
			JSONObject jo = new JSONObject();
			
			jo.put("mbr_sn", l.getMbr_sn());
			jo.put("shr_mbr_sn", l.getShr_mbr_sn());
			
			String eml_addr = deviceService.getEmlByMbrSn(l.getShr_mbr_sn());
			jo.put("eml_addr", eml_addr);
			jo.put("eqpmnt_info_sn", l.getEqpmnt_info_sn());
			jo.put("eqpmnt_shr_role", l.getEqpmnt_shr_role());
			jo.put("eqpmnt_shr_req_tm", l.getEqpmnt_shr_req_tm());
			
			String eqpmnt_shr_yn = l.getEqpmnt_shr_yn();
			String eqpmnt_shr_rsp_tm = l.getEqpmnt_shr_rsp_tm();
			
			if(eqpmnt_shr_yn == null) {
				eqpmnt_shr_yn="";
			}
			if(eqpmnt_shr_rsp_tm == null) {
				eqpmnt_shr_rsp_tm="";
			}
			
			jo.put("eqpmnt_shr_yn", eqpmnt_shr_yn);
			jo.put("eqpmnt_shr_rsp_tm", eqpmnt_shr_rsp_tm);
			
			jo.put("fvrt_stng_yn", l.getFvrt_stng_yn());
			
			ja.add(jo);
		
		}
			
		return ja;
	}
		
	//	http://localhost:8080/MshareResponse?salt_vl=PnIgaDipUXOtrCcOscbBoLeqzrdcqxSC&shr_mbr_sn15&eqpmnt_info_sn=6&eqpmnt_shr_yn=Y
	@GetMapping("/MshareResponse")
	public JSONObject MshareResponse(@RequestParam(required = true) String salt_vl, @RequestParam(required = true) String shr_mbr_sn, @RequestParam(required = true) String eqpmnt_info_sn, 
			@RequestParam(required = true) String eqpmnt_shr_yn, Model model, ShareVO shareVO, LogVO logVO) {
		
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		String mbr_sn = deviceService.getMbrSnBySalt(salt_vl);
		
		
		try {
			shareVO.setMbr_sn(mbr_sn);
			String eml_addr = deviceService.getEmlByMbrSn(shr_mbr_sn);
			shareVO.setEqpmnt_info_sn(eqpmnt_info_sn);
			shareVO.setEqpmnt_shr_yn(eqpmnt_shr_yn);
			
			deviceService.changeShrDeviceYn(eml_addr, shareVO);
			
			rtnMap.put("rtnCode", "1");
			
			if(eqpmnt_shr_yn.equals("Y")) {
				logVO.setLog_content("메시지 요청: 수락 (shr_mbr_sn: "+shr_mbr_sn+"/ eqpmnt_info_sn: "+eqpmnt_info_sn+")");
			} else if(eqpmnt_shr_yn.equals("N")) {
				logVO.setLog_content("메시지 요청: 거절 (shr_mbr_sn: "+shr_mbr_sn+"/ eqpmnt_info_sn: "+eqpmnt_info_sn+")");
			}
			
			logVO.setMbr_sn(mbr_sn);
			logVO.setEqpmnt_info_sn(eqpmnt_info_sn);
			
			// 로그
			logVO.setLog_cat("D");
			logService.registerUserLog(logVO);
		} catch(Exception e) {
			rtnMap.put("rtnCode", "0");
			
			if(eqpmnt_shr_yn.equals("Y")) {
				rtnMap.put("rtnMsg", "기기 공유 승인을 실패하였습니다");
			} else if(eqpmnt_shr_yn.equals("N")) {
				rtnMap.put("rtnMsg", "기기 공유 거절을 실패하였습니다");
			}
		}
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;
	}
	
}
