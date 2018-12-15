package com.naver.erp;

import java.util.List;


// [�˻�ȭ��] ���� �ڹٺ� ���� �� ���� ����
//	<1>[�˻�ȭ��] ������ �ݵ�� �־���� �����Ͱ� ����Ǵ� �Ӽ��������� ����Ʈ ���� �����Ѵ� <��>������������ȣ, ȭ��� ���� �˻� ������� ����
//	�˻�ȭ�� ó�� ���� �� �ڹٺ��� �Ӽ������� �����Ͱ� ���� ��� [�˻�ȭ��] ���� �� ������ �߻��ϱ� ����
//	<2>�˻� SQL ������ �ݵ�� ���ԵǴ� �Ӽ��������� ����Ʈ ���� �����Ѵ�. <��>order by ������ ���Ե� �Ӽ�������
public class ContactSearchDTO {
	private List<Integer> saup_field;		// ����ó��� �о� ����
	private String min_reg_year;			// ����� �ּ� ���� ����
	private	String min_reg_month;			// ����� �ּ� �� ����
	private String max_reg_year;			// ����� �ִ� ���� ����
	private String max_reg_month;			// ����� �ִ� �� ����
	private String keyword1;				// 1��° Ű���� ����
	
	//	[�˻� ������� DTO] ���� �Ӽ����� ����
	private int rowCntPerPage=10;			// �� ȭ�鿡 �������� �˻� ��� �ִ��� ���� ����. <����> �ݵ�� �ʱⰪ
	private int selectPageNo=1;				// ���� ���õ� ������ ��ȣ ����.  <����> �ݵ�� �ʱⰪ �Է��� ��
	private String sort;					// ���� ������ ����.	<����> �ʿ信 ���� �ʱⰪ �Է� ����
	
	
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
