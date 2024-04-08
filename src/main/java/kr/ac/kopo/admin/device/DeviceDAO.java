package kr.ac.kopo.admin.device;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.admin.Pagination;
import kr.ac.kopo.device.rec.RecVO;
import kr.ac.kopo.device.share.ShareVO;

@Repository
public class DeviceDAO {

	@Autowired
	SqlSession sqlsession;
	
	public String selectMbrSnBySalt(String salt_vl) {
		return sqlsession.selectOne("Device.selectMbrSnBySalt", salt_vl);
	}
	
	public String selectDupDeviceInfo(DeviceVO deviceVO) {
		return sqlsession.selectOne("Device.selectDupDeviceInfo", deviceVO);
	}
	
	public String insertDeviceInfo(DeviceVO deviceVO) {
		String eqpmnt_info_sn = sqlsession.selectOne("Device.selectEqpmntInfoSn", deviceVO);
		deviceVO.setEqpmnt_info_sn(eqpmnt_info_sn);
		sqlsession.insert("Device.insertDeviceInfo", deviceVO);
		return eqpmnt_info_sn;
	}
	
	public void deleteDeviceInfo(DeviceVO deviceVO) {
		sqlsession.selectOne("Device.deleteDeviceInfo", deviceVO);
	}
	
	public List<DeviceVO> selectDeviceList(DeviceVO deviceVO) {
		return sqlsession.selectList("Device.selectDeviceList", deviceVO);
	}
	
	public String selectEqpmntNm(String eqpmnt_info_sn) {
		return sqlsession.selectOne("Device.selectEqpmntNm", eqpmnt_info_sn);
	}
	
	public void insertFavrtInfo(DeviceVO deviceVO) {
		sqlsession.update("Device.insertFavrtInfo", deviceVO);
	}
	
	public void deleteFavrtInfo(DeviceVO deviceVO) {
		sqlsession.update("Device.deleteFavrtInfo", deviceVO);
	}
	
	public List<DeviceVO> selectFavrtLst(DeviceVO deviceVO) {
		return sqlsession.selectList("Device.selectFavrtLst", deviceVO);
	}
	
	public String selectEmlByMbrSn(String shr_mbr_sn) {
		return sqlsession.selectOne("Device.selectEmlByMbrSn", shr_mbr_sn);
	}
	
	public String selectShrMbrSnByEml(String eml_addr) {
		return sqlsession.selectOne("Device.selectShrMbrSnByEml", eml_addr);
	}
	
	public String selectDupShrDeviceInfo(ShareVO shareVO) {
		return sqlsession.selectOne("Device.selectDupShrDeviceInfo", shareVO);
	}
	
	public void insertShrDeviceInfo(ShareVO shareVO) {
		sqlsession.insert("Device.insertShrDeviceInfo", shareVO);
	}
	
	public void deleteShrDeviceInfo(ShareVO shareVO) {
		sqlsession.delete("Device.deleteShrDeviceInfo", shareVO);
	}
	
	public List<ShareVO> selectShrDeviceList(ShareVO shareVO) {
		return sqlsession.selectList("Device.selectShrDeviceList", shareVO);
	}
	
	public void updateShrDeviceYn(String eml_addr, ShareVO shareVO) {
		String shr_mbr_sn = sqlsession.selectOne("Device.selectShrMbrSnByEml", eml_addr);
		shareVO.setShr_mbr_sn(shr_mbr_sn);
		sqlsession.update("Device.updateShrDeviceYn", shareVO);
	}
	
	public String selectDupRecSchdlInfo(RecVO recVO) {
		return sqlsession.selectOne("Device.selectDupRecSchdlInfo", recVO);
	}
	
	public void insertRecSchdlInfo(RecVO recVO) {
		String rec_schdl_sn = sqlsession.selectOne("Device.selectRecSchdlSn", recVO);
		recVO.setRec_schdl_sn(rec_schdl_sn);
		sqlsession.insert("Device.insertRecSchdlInfo", recVO);
	}
	
	public void deleteRecSchdlInfo(RecVO recVO) {
		sqlsession.delete("Device.deleteRecSchdlInfo", recVO);
	}
	
	public List<RecVO> selectRecSchdlList(RecVO recVO) {
		return sqlsession.selectList("Device.selectRecSchdlList", recVO);
	}
	
	public String selectDupRDeviceInfo(DeviceVO deviceVO) {
		return sqlsession.selectOne("Device.selectDupRDeviceInfo", deviceVO);
	}
	
	public String selectEqpmntInfoSnBySerialNo (String eqpmnt_serial_no) {
		return sqlsession.selectOne("Device.selectEqpmntInfoSnBySerialNo", eqpmnt_serial_no);
	}
	
	public void insertRDeviceInfo(DeviceVO deviceVO) {
		sqlsession.insert("Device.insertRDeviceInfo", deviceVO);
	}
	
	public void updateRDeviceInfo(DeviceVO deviceVO) {
		sqlsession.update("Device.updateRDeviceInfo", deviceVO);
	}
	
	public List<DeviceVO> selectDeviceEList(DeviceVO deviceVO) {
		return sqlsession.selectList("Device.selectDeviceEList", deviceVO);
	}
	
	public Map<String, String> selectDeviceInfo(DeviceVO deviceVO) {
		return sqlsession.selectOne("Device.selectDeviceInfo", deviceVO);
	}
	
	public Pagination selectDeviceListPagingInfo(DeviceVO deviceVO) {
		return sqlsession.selectOne("Device.selectDeviceListPagingInfo", deviceVO);
	}
	
}
