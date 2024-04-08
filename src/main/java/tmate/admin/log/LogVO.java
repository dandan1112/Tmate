package tmate.admin.log;

public class LogVO {

	private String log_cat;
	private String log_tm;
	private String mbr_sn;
	private String eqpmnt_info_sn;
	private String log_content;
	
	private String eml_addr;
	private String order;
	private String searchText;
	private String startDate;
	private String endDate;
	
	private Integer TOTAL;
	private Integer ROWNUM;
	private Integer PAGE_SIZE;
	private Integer PAGE_NUMBER;
	private Integer CURRENT_START;
	
	public String getLog_cat() {
		return log_cat;
	}
	
	public void setLog_cat(String log_cat) {
		this.log_cat = log_cat;
	}
	
	public String getLog_tm() {
		return log_tm;
	}
	
	public void setLog_tm(String log_tm) {
		this.log_tm = log_tm;
	}
	
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
	
	public String getLog_content() {
		return log_content;
	}
	
	public void setLog_content(String log_content) {
		this.log_content = log_content;
	}
	
	public String getEml_addr() {
		return eml_addr;
	}
	
	public void setEml_addr(String eml_addr) {
		this.eml_addr = eml_addr;
	}
	
	public String getOrder() {
		return order;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
