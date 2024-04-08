package kr.ac.kopo.device;

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
public class DeviceController {

	@Autowired
	DeviceService deviceService;
	@Autowired
	LogService logService;
	
	//	http://localhost:8080/MdeviceAdd?salt_vl=ELtGbqSxUuQwrcHFbUSsXVjYbkOmvYLU&eqpmnt_type_ct=D&eqpmnt_cntn_addr=111&eqpmnt_cntn_port=111&eqpmnt_serial_no=111&eqpmnt_safety_cd=111
	@GetMapping("/MdeviceAdd")
	public JSONObject MdeviceAdd(@RequestParam(required = true) String salt_vl, @RequestParam(required = true) String eqpmnt_type_ct, @RequestParam(required = true) String eqpmnt_cntn_addr,
			@RequestParam(required = true) String eqpmnt_cntn_port, @RequestParam(required = true) String eqpmnt_nm, @RequestParam(required = true) String eqpmnt_id, 
			@RequestParam(required = true) String eqpmnt_pswd, @RequestParam(required = true) String eqpmnt_serial_no, @RequestParam(required = false) String eqpmnt_safety_cd,
			Model model, DeviceVO deviceVO, LogVO logVO) {
		
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		String mbr_sn = deviceService.getMbrSnBySalt(salt_vl);
		
		try{
			deviceVO.setMbr_sn(mbr_sn);
			deviceVO.setEqpmnt_type_ct(eqpmnt_type_ct);
			deviceVO.setEqpmnt_cntn_addr(eqpmnt_cntn_addr);
			deviceVO.setEqpmnt_cntn_port(eqpmnt_cntn_port);
			deviceVO.setEqpmnt_nm(eqpmnt_nm);
			deviceVO.setEqpmnt_id(eqpmnt_id);
			deviceVO.setEqpmnt_pswd(eqpmnt_pswd);
			deviceVO.setEqpmnt_serial_no(eqpmnt_serial_no);
			deviceVO.setEqpmnt_safety_cd(eqpmnt_safety_cd);

			String dupChk = deviceService.checkDupDeviceInfo(deviceVO);
			
			if(dupChk.equals("N")) {
				String eqpmnt_info_sn = deviceService.registerDeviceInfo(deviceVO);
				
				rtnMap.put("rtnCode", "1");
				
				logVO.setLog_content("장치추가: 추가 (eqpmnt_type_ct: "+eqpmnt_type_ct+"/eqpmnt_info_sn: "+eqpmnt_info_sn+")");
				logVO.setMbr_sn(mbr_sn);
				logVO.setEqpmnt_info_sn(eqpmnt_info_sn);
				
				// 로그
				logVO.setLog_cat("D");
				logService.registerUserLog(logVO);
			} else {
				rtnMap.put("rtnCode", "0");
				rtnMap.put("rtnMsg", "이미 등록된 기기 접속 정보 입니다");
			}
			
		} catch(Exception e) {
			rtnMap.put("rtnCode", "0");
			rtnMap.put("rtnMsg", "기기 접속 정보 등록을 실패하였습니다");
		}
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;
	}
	
	//	http://localhost:8080/MdeviceDel?salt_vl=PnIgaDipUXOtrCcOscbBoLeqzrdcqxSC&eqpmnt_info_sn=1
	@GetMapping("/MdeviceDel")
	public JSONObject MdeviceDel(@RequestParam(required = true) String salt_vl, @RequestParam(required = true) String eqpmnt_info_sn, Model model, DeviceVO deviceVO, LogVO logVO) {
	
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		String mbr_sn = deviceService.getMbrSnBySalt(salt_vl);
		
		try{
			deviceVO.setMbr_sn(mbr_sn);
			deviceVO.setEqpmnt_info_sn(eqpmnt_info_sn);
			 
			deviceService.removeDeviceInfo(deviceVO);
			
			rtnMap.put("rtnCode", "1");
			
			logVO.setLog_content("장치삭제: 삭제 (eqpmnt_info_sn: "+eqpmnt_info_sn+")");
			logVO.setMbr_sn(mbr_sn);
			logVO.setEqpmnt_info_sn(eqpmnt_info_sn);
			
			// 로그
			logVO.setLog_cat("D");
			logService.registerUserLog(logVO);
			
		} catch(Exception e) {
			rtnMap.put("rtnCode", "0");
			rtnMap.put("rtnMsg", "기기 접속 정보 삭제를 실패하였습니다");
		}
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;
	}
	
	//	http://localhost:8080/MdeviceList?salt_vl=PnIgaDipUXOtrCcOscbBoLeqzrdcqxSC
	@SuppressWarnings("unchecked")
	@GetMapping("/MdeviceList")
	public JSONArray MdeviceList(@RequestParam(required = true) String salt_vl, Model model, DeviceVO deviceVO) {
		
		String mbr_sn = deviceService.getMbrSnBySalt(salt_vl);
		
		deviceVO.setMbr_sn(mbr_sn);

		List<DeviceVO> deviceList = deviceService.getDeviceList(deviceVO);
		
		JSONArray ja = new JSONArray();
		
		for(DeviceVO l : deviceList) {
			
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
			
		model.addAttribute("result", ja);
		
		return ja;
	}
	
	//	http://localhost:8080/RdeviceRegister?eqpmnt_serial_no=111&eqpmnt_safety_cd=111&eqpmnt_type_ct=D&eqpmnt_cntn_addr=111&eqpmnt_cntn_port=111
	@GetMapping("/RdeviceRegister")
	public void RdeviceRegister(@RequestParam(required = true) String eqpmnt_serial_no, @RequestParam(required = true) String eqpmnt_safety_cd, 
			@RequestParam(required = true) String eqpmnt_type_ct, @RequestParam(required = true) String eqpmnt_cntn_addr, @RequestParam(required = true) String eqpmnt_cntn_port, DeviceVO deviceVO) {
		
		deviceVO.setEqpmnt_info_sn(deviceService.getEqpmntInfoSnBySerialNo(eqpmnt_serial_no));
		deviceVO.setEqpmnt_safety_cd(eqpmnt_safety_cd);
		deviceVO.setEqpmnt_type_ct(eqpmnt_type_ct);
		deviceVO.setEqpmnt_cntn_addr(eqpmnt_cntn_addr);
		deviceVO.setEqpmnt_cntn_port(eqpmnt_cntn_port);
		
		String dupChk = deviceService.checkDupRDeviceInfo(deviceVO);
		
		if(dupChk.equals("N")) {
			deviceService.registerRDeviceInfo(deviceVO);
		} else {
			deviceService.changeRDeviceInfo(deviceVO);
		}
			
	}
	
}
