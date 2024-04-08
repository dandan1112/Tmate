package kr.ac.kopo.admin.user;

public class UserVO {

	private String mbr_sn;
	private String mbr_id;
	private String mbr_name;
	private String eml_addr;
	private String mbr_tel;
	private String mbr_type;
	private String pswd;
	private String prvc_trms_agre_dt;
	private String srvc_trms_agre_dt;
	private String salt_vl;
	private String pswd_init_yn;
	private String resign_yn;
	
	private String temp_pswd;
	private String searchText;
	
	private Integer TOTAL;
	private Integer ROWNUM;
	private Integer PAGE_SIZE;
	private Integer PAGE_NUMBER;
	private Integer CURRENT_START;
	
	public String getMbr_sn() {
		return mbr_sn;
	}
	
	public void setMbr_sn(String mbr_sn) {
		this.mbr_sn = mbr_sn;
	}
	
	public String getMbr_id() {
		return mbr_id;
	}
	
	public void setMbr_id(String mbr_id) {
		this.mbr_id = mbr_id;
	}
	
	public String getMbr_name() {
		return mbr_name;
	}
	
	public void setMbr_name(String mbr_name) {
		this.mbr_name = mbr_name;
	}
	
	public String getEml_addr() {
		return eml_addr;
	}
	
	public void setEml_addr(String eml_addr) {
		this.eml_addr = eml_addr;
	}
	
	public String getMbr_tel() {
		return mbr_tel;
	}
	
	public void setMbr_tel(String mbr_tel) {
		this.mbr_tel = mbr_tel;
	}
	
	public String getMbr_type() {
		return mbr_type;
	}
	
	public void setMbr_type(String mbr_type) {
		this.mbr_type = mbr_type;
	}
	
	public String getPswd() {
		return pswd;
	}
	
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	
	public String getPrvc_trms_agre_dt() {
		return prvc_trms_agre_dt;
	}
	
	public void setPrvc_trms_agre_dt(String prvc_trms_agre_dt) {
		this.prvc_trms_agre_dt = prvc_trms_agre_dt;
	}
	
	public String getSrvc_trms_agre_dt() {
		return srvc_trms_agre_dt;
	}
	
	public void setSrvc_trms_agre_dt(String srvc_trms_agre_dt) {
		this.srvc_trms_agre_dt = srvc_trms_agre_dt;
	}
	
	public String getSalt_vl() {
		return salt_vl;
	}
	
	public void setSalt_vl(String salt_vl) {
		this.salt_vl = salt_vl;
	}
	
	public String getPswd_init_yn() {
		return pswd_init_yn;
	}
	
	public void setPswd_init_yn(String pswd_init_yn) {
		this.pswd_init_yn = pswd_init_yn;
	}
	
	public String getSearchText() {
		return searchText;
	}
	
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Integer getPAGE_NUMBER() {
		return PAGE_NUMBER;
	}

	public void setPAGE_NUMBER(Integer pAGE_NUMBER) {
		PAGE_NUMBER = pAGE_NUMBER;
	}

	public Integer getROWNUM() {
		return ROWNUM;
	}

	public void setROWNUM(Integer rOWNUM) {
		ROWNUM = rOWNUM;
	}

	public Integer getPAGE_SIZE() {
		return PAGE_SIZE;
	}

	public void setPAGE_SIZE(Integer pAGE_SIZE) {
		PAGE_SIZE = pAGE_SIZE;
	}

	public Integer getCURRENT_START() {
		return CURRENT_START;
	}

	public void setCURRENT_START(Integer cURRENT_START) {
		CURRENT_START = cURRENT_START;
	}

	public Integer getTOTAL() {
		return TOTAL;
	}

	public void setTOTAL(Integer tOTAL) {
		TOTAL = tOTAL;
	}

	public String getTemp_pswd() {
		return temp_pswd;
	}

	public void setTemp_pswd(String temp_pswd) {
		this.temp_pswd = temp_pswd;
	}

	public String getResign_yn() {
		return resign_yn;
	}

	public void setResign_yn(String resign_yn) {
		this.resign_yn = resign_yn;
	}

}
