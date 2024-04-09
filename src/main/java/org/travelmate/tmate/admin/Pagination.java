package org.travelmate.tmate.admin;

public class Pagination {

	public static final int PAGE_SIZE = 10;
	public static final int BLOCK_SIZE = 10;
	
	private Integer PAGE_NUMBER;
	private Integer TOTAL_PAGE;
	private Integer CURRENT_BLOCK;
	private Integer TOTAL_BLOCK;
	private Integer BEGIN_BLOCK;
	private Integer END_BLOCK;
	private Integer PREV_PAGE;
	private Integer NEXT_PAGE;
	
	public Pagination(Integer total, Integer page_num) {
		PAGE_NUMBER = (int) page_num;	// 현재 페이지 번호
		TOTAL_PAGE = (int) Math.ceil(total * 1.0 / PAGE_SIZE);	// 전체 페이지 수
		CURRENT_BLOCK = (int) Math.ceil((page_num-1) / BLOCK_SIZE) + 1;	// 현재 속한 블록
		TOTAL_BLOCK = (int) Math.ceil((double)TOTAL_PAGE / BLOCK_SIZE);	// 전체 블록
		BEGIN_BLOCK = (CURRENT_BLOCK - 1) * BLOCK_SIZE + 1;	// 현재 블록의 시작 페이지 번호
		END_BLOCK = CURRENT_BLOCK * BLOCK_SIZE;	// 현재 블록의 끝 페이지 번호
		if(END_BLOCK > TOTAL_PAGE) {END_BLOCK = TOTAL_PAGE;}
		PREV_PAGE = (page_num == 1) ? 1 : (CURRENT_BLOCK - 1) * BLOCK_SIZE;	// 이전 페이지
		NEXT_PAGE = CURRENT_BLOCK > TOTAL_BLOCK ? (CURRENT_BLOCK * BLOCK_SIZE):(CURRENT_BLOCK * BLOCK_SIZE) + 1;	// 다음 페이지	
		if(NEXT_PAGE >= TOTAL_PAGE) {NEXT_PAGE = TOTAL_PAGE;}
	}
	
	public Integer getPAGE_NUMBER() {
		return PAGE_NUMBER;
	}
	public void setPAGE_NUMBER(Integer pAGE_NUMBER) {
		PAGE_NUMBER = pAGE_NUMBER;
	}
	public Integer getTOTAL_PAGE() {
		return TOTAL_PAGE;
	}
	public void setTOTAL_PAGE(Integer tOTAL_PAGE) {
		TOTAL_PAGE = tOTAL_PAGE;
	}
	public Integer getCURRENT_BLOCK() {
		return CURRENT_BLOCK;
	}
	public void setCURRENT_BLOCK(Integer cURRENT_BLOCK) {
		CURRENT_BLOCK = cURRENT_BLOCK;
	}
	public Integer getTOTAL_BLOCK() {
		return TOTAL_BLOCK;
	}
	public void setTOTAL_BLOCK(Integer tOTAL_BLOCK) {
		TOTAL_BLOCK = tOTAL_BLOCK;
	}
	public Integer getBEGIN_BLOCK() {
		return BEGIN_BLOCK;
	}
	public void setBEGIN_BLOCK(Integer bEGIN_BLOCK) {
		BEGIN_BLOCK = bEGIN_BLOCK;
	}
	public Integer getEND_BLOCK() {
		return END_BLOCK;
	}
	public void setEND_BLOCK(Integer eND_BLOCK) {
		END_BLOCK = eND_BLOCK;
	}
	public Integer getPREV_PAGE() {
		return PREV_PAGE;
	}
	public void setPREV_PAGE(Integer pREV_PAGE) {
		PREV_PAGE = pREV_PAGE;
	}
	public Integer getNEXT_PAGE() {
		return NEXT_PAGE;
	}
	public void setNEXT_PAGE(Integer nEXT_PAGE) {
		NEXT_PAGE = nEXT_PAGE;
	}
	public static int getPageSize() {
		return PAGE_SIZE;
	}
	public static int getBlockSize() {
		return BLOCK_SIZE;
	}
	
}
