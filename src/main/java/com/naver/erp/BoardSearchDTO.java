package com.naver.erp;

public class BoardSearchDTO {
	private String keyword;				// 검색 키워드 저장
	private String issue;
	private String keywordTarget;		// 검색 키워드의 타겟 저장
	private int rowCntPerPage=15;		// 한 화면에 보여지는 검색 결과 최대행 개수 저장. <주의> 반드시 초기값
	private int selectPageNo=1;			// 현재 선택된 페이지 번호 저장			<주의> 반드시 초기값 입력할 것
	private int beginRowNo;				// 페이지 번호에 맞는 시작행 번호 저장
	private int endRowNo;				// 페이지 번호에 맞는 끝행 번호 저장
	
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getKeywordTarget() {
		return keywordTarget;
	}
	public void setKeywordTarget(String keywordTarget) {
		this.keywordTarget = keywordTarget;
	}
	public int getRowCntPerPage() {
		return rowCntPerPage;
	}
	public void setRowCntPerPage(int rowCntPerPage) {
		this.rowCntPerPage = rowCntPerPage;
	}
	public int getSelectPageNo() {
		return selectPageNo;
	}
	public void setSelectPageNo(int selectPageNo) {
		this.selectPageNo = selectPageNo;
	}
	public int getBeginRowNo() {
		return beginRowNo;
	}
	public void setBeginRowNo(int beginRowNo) {
		this.beginRowNo = beginRowNo;
	}
	public int getEndRowNo() {
		return endRowNo;
	}
	public void setEndRowNo(int endRowNo) {
		this.endRowNo = endRowNo;
	}				
	
}
	