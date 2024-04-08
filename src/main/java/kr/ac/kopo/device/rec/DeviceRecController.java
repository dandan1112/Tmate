package kr.ac.kopo.device.rec;

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
public class DeviceRecController {

	@Autowired
	DeviceService deviceService;
	@Autowired
	LogService logService;
	
	//	http://localhost:8080/MrecSchdlAdd?salt_vl=PnIgaDipUXOtrCcOscbBoLeqzrdcqxSC&eqpmnt_info_sn=6&rec_bgng_dt=2023-11-11&rec_bgng_tm=01:11&rec_end_dt=2023-11-12&rec_end_tm=01:11
	@GetMapping("/MrecSchdlAdd")
	public JSONObject MrecSchdlAdd(@RequestParam(required = true) String salt_vl, @RequestParam(required = true) String eqpmnt_info_sn, @RequestParam(required = true) String rec_bgng_dt, 
			@RequestParam(required = true) String rec_bgng_tm, @RequestParam(required = true) String rec_end_dt, @RequestParam(required = true) String rec_end_tm, Model model, RecVO recVO, LogVO logVO) {
		
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		String mbr_sn = deviceService.getMbrSnBySalt(salt_vl);
		
		try {
			recVO.setMbr_sn(mbr_sn);
			recVO.setEqpmnt_info_sn(eqpmnt_info_sn);
			recVO.setRec_bgng_dt(rec_bgng_dt);
			recVO.setRec_bgng_tm(rec_bgng_tm);
			recVO.setRec_end_dt(rec_end_dt);
			recVO.setRec_end_tm(rec_end_tm);
			
			String dupChk = deviceService.checkDupRecSchdlInfo(recVO);
			
			if(dupChk.equals("N")) {
				deviceService.registerRecSchdlInfo(recVO);
				
				rtnMap.put("rtnCode", "1");
				
				logVO.setLog_content("녹화일정: 일정추가 (mbr_sn: "+mbr_sn+"/ eqpmnt_info_sn: "+eqpmnt_info_sn+")");
				logVO.setMbr_sn(mbr_sn);
				logVO.setEqpmnt_info_sn(eqpmnt_info_sn);
				
				// 로그
				logVO.setLog_cat("D");
				logService.registerUserLog(logVO);
			} else {
				rtnMap.put("rtnCode", "0");
				rtnMap.put("rtnMsg", "이미 등록된 녹화 스케줄 입니다");
			}
			
		} catch(Exception e) {
			rtnMap.put("rtnCode", "0");
			rtnMap.put("rtnMsg", "녹화 스케줄 등록을 실패하였습니다");
		}
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;
	}
	
	//	http://localhost:8080/MrecSchdlDel?salt_vl=PnIgaDipUXOtrCcOscbBoLeqzrdcqxSC&eqpmnt_info_sn=2&rec_schdl_sn=5
	@GetMapping("/MrecSchdlDel")
	public JSONObject MrecSchdlDel(@RequestParam(required = true) String salt_vl, @RequestParam(required = true) String eqpmnt_info_sn, @RequestParam(required = true) String rec_schdl_sn,
			Model model, RecVO recVO, LogVO logVO) {
	
		Map<String, String> rtnMap = new HashMap<String, String>();
		
		String mbr_sn = deviceService.getMbrSnBySalt(salt_vl);
		
		try {
			recVO.setMbr_sn(mbr_sn);
			recVO.setEqpmnt_info_sn(eqpmnt_info_sn);
			recVO.setRec_schdl_sn(rec_schdl_sn);
			
			deviceService.removeRecSchdlInfo(recVO);
			
			rtnMap.put("rtnCode", "1");
			
			logVO.setLog_content("녹화일정: 일정삭제 (mbr_sn: "+mbr_sn+"/ rec_schdl_sn: "+rec_schdl_sn+")");
			logVO.setMbr_sn(mbr_sn);
			logVO.setEqpmnt_info_sn(eqpmnt_info_sn);
			
			// 로그
			logVO.setLog_cat("D");
			logService.registerUserLog(logVO);
		} catch(Exception e) {
			rtnMap.put("rtnCode", "0");
			rtnMap.put("rtnMsg", "녹화 스케줄 삭제를 실패하였습니다");
		}
		
		JSONObject obj = new JSONObject(rtnMap);
		
		return obj;
	}
	
	//	http://localhost:8080/MrecSchdlList?salt_vl=PnIgaDipUXOtrCcOscbBoLeqzrdcqxSC
	@SuppressWarnings("unchecked")
	@GetMapping("/MrecSchdlList")
	public JSONArray MrecSchdlList(@RequestParam(required = true) String salt_vl, Model model, RecVO recVO) {
		
		String mbr_sn = deviceService.getMbrSnBySalt(salt_vl);
		
		recVO.setMbr_sn(mbr_sn);

		List<RecVO> recSchdlList = deviceService.getRecSchdlList(recVO);
		
		JSONArray ja = new JSONArray();
		
		for(RecVO l : recSchdlList) {
			
			JSONObject jo = new JSONObject();
			
			jo.put("mbr_sn", l.getMbr_sn());
			jo.put("eqpmnt_info_sn", l.getEqpmnt_info_sn());
			jo.put("rec_schdl_sn", l.getRec_schdl_sn());
			jo.put("rec_bgng_dt", l.getRec_bgng_dt());
			jo.put("rec_bgng_tm", l.getRec_bgng_tm());
			jo.put("rec_end_dt", l.getRec_end_dt());
			jo.put("rec_end_tm", l.getRec_end_tm());
			
			ja.add(jo);
		
		}
			
		return ja;
	}
	
}
