package org.travelmate.tmate.admin.device;

import java.util.List;
import java.util.Map;

import org.travelmate.tmate.admin.Pagination;


public interface DeviceService {

	public abstract String getMbrSnBySalt(String salt_vl);
	public abstract String checkDupDeviceInfo(DeviceVO deviceVO);
	public abstract String registerDeviceInfo(DeviceVO deviceVO);
	public abstract void removeDeviceInfo(DeviceVO deviceVO);
	public abstract List<DeviceVO> getDeviceList(DeviceVO deviceVO);
	
	public abstract String getEqpmntNm(String eqpmnt_info_sn);
	public abstract void registerFavrtInfo(DeviceVO deviceVO);
	public abstract void removeFavrtInfo(DeviceVO deviceVO);
	public abstract List<DeviceVO> getFavrtList(DeviceVO deviceVO);
	
	public abstract String getEmlByMbrSn(String shr_mbr_sn);
	public abstract String getShrMbrSnByEml(String eml_addr);
//	public abstract String checkDupShrDeviceInfo(ShareVO shareVO);
//	public abstract void registerShrDeviceInfo(ShareVO shareVO);
//	public abstract void removeShrDeviceInfo(ShareVO shareVO);
//	public abstract List<ShareVO> getShrDeviceList(ShareVO shareVO);
//	public abstract void changeShrDeviceYn(String eml_addr, ShareVO shareVO);
//	
//	public abstract String checkDupRecSchdlInfo(RecVO recVO);
//	public abstract void registerRecSchdlInfo(RecVO recVO);
//	public abstract void removeRecSchdlInfo(RecVO recVO);
//	public abstract List<RecVO> getRecSchdlList(RecVO recVO);
	
	public abstract String checkDupRDeviceInfo(DeviceVO deviceVO);
	public abstract String getEqpmntInfoSnBySerialNo(String eqpmnt_serial_no);
	public abstract void registerRDeviceInfo(DeviceVO deviceVO);
	public abstract void changeRDeviceInfo(DeviceVO deviceVO);
	
	public abstract List<DeviceVO> getDeviceEList(DeviceVO deviceVO);
	public abstract Map<String, String> getDeviceInfo(DeviceVO deviceVO);
	
	public abstract Pagination getDeviceListPagingInfo(DeviceVO deviceVO);
}
