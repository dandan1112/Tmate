package kr.ac.kopo.device.favorit;

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
import kr.ac.kopo.admin.device.DeviceVO;
import kr.ac.kopo.admin.log.LogService;
import kr.ac.kopo.admin.log.LogVO;

@RestController
public class DeviceFavoritController {

	@Autowired
	DeviceService deviceService;
	@Autowired
	LogService logService;
	
//	http://localhost:8080/MfavrtAdd?salt_vl=PnIgaDipUXOtrCcOscbBoLeqzrdcqxSC&eqpmnt_info_sn=2
	@GetMapping("/MfavrtAdd")
	public JSONObject MfavrtAdd(@RequestParam(required = true) String salt_vl, @RequestParam(required = true) String eqpmnt_info_sn, Model model, DeviceVO deviceVO, LogVO logVO) {
		
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		String mbr_sn = deviceService.getMbrSnBySalt(salt_vl);
		String eqpmnt_nm = deviceService.getEqpmntNm(eqpmnt_info_sn);
		
		try{
			deviceVO.setMbr_sn(mbr_sn);
			deviceVO.setEqpmnt_info_sn(eqpmnt_info_sn);
			 
			deviceService.registerFavrtInfo(deviceVO);
			
			rtnMap.put("rtnCode", "1");
			
			logVO.setLog_content("즐겨찾기: 추가 (eqpmnt_nm: "+eqpmnt_nm+")");
			logVO.setMbr_sn(mbr_sn);
			logVO.setEqpmnt_info_sn(eqpmnt_info_sn);
			
			// 로그
			logVO.setLog_cat("D");
			logService.registerUserLog(logVO);
			
		} catch(Exception e) {
			rtnMap.put("rtnCode", "0");
			rtnMap.put("rtnMsg", "즐겨찾기 채널 등록을 실패하였습니다");
			
		}
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;	
	}
	
	//	http://localhost:8080/MfavrtDel?salt_vl=PnIgaDipUXOtrCcOscbBoLeqzrdcqxSC&eqpmnt_info_sn=2
	@GetMapping("/MfavrtDel")
	public JSONObject MfavrtDel(@RequestParam(required = true) String salt_vl, @RequestParam(required = true) String eqpmnt_info_sn, Model model, DeviceVO deviceVO, LogVO logVO) {
		
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		String mbr_sn = deviceService.getMbrSnBySalt(salt_vl);
		String eqpmnt_nm = deviceService.getEqpmntNm(eqpmnt_info_sn);
		
		try{
			deviceVO.setMbr_sn(mbr_sn);
			deviceVO.setEqpmnt_info_sn(eqpmnt_info_sn);
			 
			deviceService.removeFavrtInfo(deviceVO);
			
			rtnMap.put("rtnCode", "1");
			
			logVO.setLog_content("즐겨찾기: 삭제 (eqpmnt_nm: "+eqpmnt_nm+")");
			logVO.setMbr_sn(mbr_sn);
			logVO.setEqpmnt_info_sn(eqpmnt_info_sn);
			
			// 로그
			logVO.setLog_cat("D");
			logService.registerUserLog(logVO);
			
		} catch(Exception e) {
			rtnMap.put("rtnCode", "0");
			rtnMap.put("rtnMsg", "즐겨찾기 채널 삭제를 실패하였습니다");
			
		}
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;
	}
	
	//	http://localhost:8080/MfavrtList?salt_vl=PnIgaDipUXOtrCcOscbBoLeqzrdcqxSC
	@SuppressWarnings("unchecked")
	@GetMapping("/MfavrtList")
	public JSONArray MfavrtList(@RequestParam(required = true) String salt_vl, Model model, DeviceVO deviceVO) {
		
		String mbr_sn = deviceService.getMbrSnBySalt(salt_vl);
		
		deviceVO.setMbr_sn(mbr_sn);
			 
		List<DeviceVO> favrtList = deviceService.getFavrtList(deviceVO);
			 
			JSONArray ja = new JSONArray();
			
			for(DeviceVO l : favrtList) {
				
				JSONObject jo = new JSONObject();
				
				jo.put("eqpmnt_info_sn", l.getEqpmnt_info_sn());
				jo.put("eqpmnt_type_ct", l.getEqpmnt_type_ct());
				jo.put("eqpmnt_cntn_addr", l.getEqpmnt_cntn_addr());
				jo.put("eqpmnt_cntn_port", l.getEqpmnt_cntn_port());
				
				String eqpmnt_nm = l.getEqpmnt_nm();
				String eqpmnt_id = l.getEqpmnt_id();
				String eqpmnt_pswd = l.getEqpmnt_pswd();
	
				if(l.getEqpmnt_nm() == null) {
					eqpmnt_nm="";
				}
				if(l.getEqpmnt_id() == null) {
					eqpmnt_id="";
				}
				if(l.getEqpmnt_pswd() == null) {
					eqpmnt_pswd="";
				}
				
				jo.put("eqpmnt_nm", eqpmnt_nm);
				jo.put("eqpmnt_id", eqpmnt_id);
				jo.put("eqpmnt_pswd", eqpmnt_pswd);
				
				jo.put("mbr_sn", l.getMbr_sn());
				jo.put("fvrt_stng_yn", l.getFvrt_stng_yn());
				
				ja.add(jo);
			
			}
			
			return ja;
	}

}
