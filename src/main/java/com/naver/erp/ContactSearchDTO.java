package com.naver.erp;

import java.util.List;


// [검색화면] 관련 자바빈 선언 시 주의 사항
//	<1>[검색화면] 구현에 반드시 있어야할 데이터가 저장되는 속성변수에는 디폴트 값을 저장한다 <예>선택페이지번호, 화면당 보일 검색 결과행의 개수
//	검색화면 처음 접속 시 자바빈의 속성변수에 데이터가 없을 경우 [검색화면] 구현 시 에러가 발생하기 때문
//	<2>검색 SQL 구문에 반드시 삽입되는 속성변수에는 디폴트 값을 저장한다. <예>order by 구문에 삽입될 속성변수등
public class ContactSearchDTO {
	private List<Integer> saup_field;		// 연락처사업 분야 저장
	private String min_reg_year;			// 등록일 최소 연도 저장
	private	String min_reg_month;			// 등록일 최소 월 저장
	private String max_reg_year;			// 등록일 최대 연도 저장
	private String max_reg_month;			// 등록일 최대 월 저장
	private String keyword1;				// 1번째 키워드 저장
	
	//	[검색 결과저장 DTO] 공통 속성변수 선언
	private int rowCntPerPage=10;			// 한 화면에 보여지는 검색 결과 최대행 개수 저장. <주의> 반드시 초기값
	private int selectPageNo=1;				// 현재 선택된 페이지 번호 저장.  <주의> 반드시 초기값 입력할 것
	private String sort;					// 정렬 데이터 저장.	<참고> 필요에 따라 초기값 입력 가능
	
	
	private int beginRowNo;
	private int endRowNo;
	
	
	
	public List<Integer> getSaup_field() {
		return saup_field;
	}
	public void setSaup_field(List<Integer> saup_field) {
		this.saup_field = saup_field;
	}
	public String getMin_reg_year() {
		return min_reg_year;
	}
	public void setMin_reg_year(String min_reg_year) {
		this.min_reg_year = min_reg_year;
	}
	public String getMin_reg_month() {
		return min_reg_month;
	}
	public void setMin_reg_month(String min_reg_month) {
		this.min_reg_month = min_reg_month;
	}
	public String getMax_reg_year() {
		return max_reg_year;
	}
	public void setMax_reg_year(String max_reg_year) {
		this.max_reg_year = max_reg_year;
	}
	public String getMax_reg_month() {
		return max_reg_month;
	}
	public void setMax_reg_month(String max_reg_month) {
		this.max_reg_month = max_reg_month;
	}
	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
