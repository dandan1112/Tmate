package kr.ac.kopo.admin.device;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.admin.Pagination;
import kr.ac.kopo.device.rec.RecVO;
import kr.ac.kopo.device.share.ShareVO;

@Service
public class DeviceServiceImpl implements DeviceService{

	@Autowired
	DeviceDAO deviceDAO;
	
	@Override
	public String getMbrSnBySalt(String salt_vl) {
		return deviceDAO.selectMbrSnBySalt(salt_vl);
	}
	
	@Override
	public String checkDupDeviceInfo(DeviceVO deviceVO) {
		return deviceDAO.selectDupDeviceInfo(deviceVO);
	}
	
	@Override
	public String registerDeviceInfo(DeviceVO deviceVO) {
		return deviceDAO.insertDeviceInfo(deviceVO);
	}
	
	@Override
	public void removeDeviceInfo(DeviceVO deviceVO) {
		deviceDAO.deleteDeviceInfo(deviceVO);
	}
	
	@Override
	public List<DeviceVO> getDeviceList(DeviceVO deviceVO) {
		return deviceDAO.selectDeviceList(deviceVO);
	}
	@Override
	public String getEqpmntNm(String eqpmnt_info_sn) {
		return deviceDAO.selectEqpmntNm(eqpmnt_info_sn);
	}
	
	@Override
	public void registerFavrtInfo(DeviceVO deviceVO) {
		deviceDAO.insertFavrtInfo(deviceVO);
	}
	
	@Override
	public void removeFavrtInfo(DeviceVO deviceVO) {
		deviceDAO.deleteFavrtInfo(deviceVO);
	}
	
	
	@Override
	public List<DeviceVO> getFavrtList(DeviceVO deviceVO) {
		return deviceDAO.selectFavrtLst(deviceVO);
	}
 
	@Override
	public String getEmlByMbrSn(String shr_mbr_sn) {
		return deviceDAO.selectEmlByMbrSn(shr_mbr_sn);
	}
	
	@Override
	public String getShrMbrSnByEml(String eml_addr) {
		return deviceDAO.selectShrMbrSnByEml(eml_addr);
	}
	
	@Override
	public String checkDupShrDeviceInfo(ShareVO shareVO) {
		return deviceDAO.selectDupShrDeviceInfo(shareVO);
	}
	
	@Override
	public void registerShrDeviceInfo(ShareVO shareVO) {
		deviceDAO.insertShrDeviceInfo(shareVO);
	}
	
	@Override
	public void removeShrDeviceInfo(ShareVO shareVO) {
		deviceDAO.deleteShrDeviceInfo(shareVO);
	}
	
	@Override
	public List<ShareVO> getShrDeviceList(ShareVO shareVO) {
		return deviceDAO.selectShrDeviceList(shareVO);
	}
	
	@Override
	public void changeShrDeviceYn(String eml_addr, ShareVO shareVO) {
		deviceDAO.updateShrDeviceYn(eml_addr, shareVO);
	}
	
	@Override
	public String checkDupRecSchdlInfo(RecVO recVO) {
		return deviceDAO.selectDupRecSchdlInfo(recVO);
	}
	
	@Override
	public void registerRecSchdlInfo(RecVO recVO) {
		deviceDAO.insertRecSchdlInfo(recVO);
	}
	
	@Override
	public void removeRecSchdlInfo(RecVO recVO) {
		deviceDAO.deleteRecSchdlInfo(recVO);
	}
	
	@Override
	public List<RecVO> getRecSchdlList(RecVO recVO) {
		return deviceDAO.selectRecSchdlList(recVO);
	}
	
	@Override
	public String checkDupRDeviceInfo(DeviceVO deviceVO) {
		return deviceDAO.selectDupRDeviceInfo(deviceVO);
	}
	
	@Override
	public String getEqpmntInfoSnBySerialNo(String eqpmnt_serial_no) {
		return deviceDAO.selectEqpmntInfoSnBySerialNo(eqpmnt_serial_no);
	}
	
	@Override
	public void registerRDeviceInfo(DeviceVO deviceVO) {
		deviceDAO.insertRDeviceInfo(deviceVO);
	}
	
	@Override
	public void changeRDeviceInfo(DeviceVO deviceVO) {
		deviceDAO.updateRDeviceInfo(deviceVO);
	}
	
	@Override
	public List<DeviceVO> getDeviceEList(DeviceVO deviceVO) {
		return deviceDAO.selectDeviceEList(deviceVO);
	}
	
	@Override
	public Map<String, String> getDeviceInfo(DeviceVO deviceVO) {
		return deviceDAO.selectDeviceInfo(deviceVO);
	}
	
	@Override
	public Pagination getDeviceListPagingInfo(DeviceVO deviceVO) {
		return deviceDAO.selectDeviceListPagingInfo(deviceVO);
	}
}

