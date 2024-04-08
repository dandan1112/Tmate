package kr.ac.kopo.admin.device;

public class DeviceVO {

	private String mbr_sn;
	private String eqpmnt_info_sn;
	private String eqpmnt_type_ct;
	private String eqpmnt_cntn_addr;
	private String eqpmnt_cntn_port;
	private String eqpmnt_nm;
	private String eqpmnt_id;
	private String eqpmnt_pswd;
	private String fvrt_stng_yn;
	private String eqpmnt_serial_no;
	private String eqpmnt_safety_cd;
	
	private String eml_addr;
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

	public String getEqpmnt_info_sn() {
		return eqpmnt_info_sn;
	}

	public void setEqpmnt_info_sn(String eqpmnt_info_sn) {
		this.eqpmnt_info_sn = eqpmnt_info_sn;
	}

	public String getEqpmnt_type_ct() {
		return eqpmnt_type_ct;
	}

	public void setEqpmnt_type_ct(String eqpmnt_type_ct) {
		this.eqpmnt_type_ct = eqpmnt_type_ct;
	}

	public String getEqpmnt_cntn_addr() {
		return eqpmnt_cntn_addr;
	}

	public void setEqpmnt_cntn_addr(String eqpmnt_cntn_addr) {
		this.eqpmnt_cntn_addr = eqpmnt_cntn_addr;
	}

	public String getEqpmnt_cntn_port() {
		return eqpmnt_cntn_port;
	}

	public void setEqpmnt_cntn_port(String eqpmnt_cntn_port) {
		this.eqpmnt_cntn_port = eqpmnt_cntn_port;
	}

	public String getEqpmnt_nm() {
		return eqpmnt_nm;
	}

	public void setEqpmnt_nm(String eqpmnt_nm) {
		this.eqpmnt_nm = eqpmnt_nm;
	}

	public String getEqpmnt_id() {
		return eqpmnt_id;
	}

	public void setEqpmnt_id(String eqpmnt_id) {
		this.eqpmnt_id = eqpmnt_id;
	}

	public String getEqpmnt_pswd() {
		return eqpmnt_pswd;
	}

	public void setEqpmnt_pswd(String eqpmnt_pswd) {
		this.eqpmnt_pswd = eqpmnt_pswd;
	}

	public String getFvrt_stng_yn() {
		return fvrt_stng_yn;
	}

	public void setFvrt_stng_yn(String fvrt_stng_yn) {
		this.fvrt_stng_yn = fvrt_stng_yn;
	}

	public String getEqpmnt_serial_no() {
		return eqpmnt_serial_no;
	}

	public void setEqpmnt_serial_no(String eqpmnt_serial_no) {
		this.eqpmnt_serial_no = eqpmnt_serial_no;
	}

	public String getEqpmnt_safety_cd() {
		return eqpmnt_safety_cd;
	}

	public void setEqpmnt_safety_cd(String eqpmnt_safety_cd) {
		this.eqpmnt_safety_cd = eqpmnt_safety_cd;
	}
	
	public String getEml_addr() {
		return eml_addr;
	}
	
	public void setEml_addr(String eml_addr) {
		this.eml_addr = eml_addr;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Integer getROWNUM() {
		return ROWNUM;
	}

	public void setROWNUM(Integer rOWNUM) {
		ROWNUM = rOWNUM;
	}

	public Integer getPAGE_NUMBER() {
		return PAGE_NUMBER;
	}

	public void setPAGE_NUMBER(Integer pAGE_NUMBER) {
		PAGE_NUMBER = pAGE_NUMBER;
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

}
