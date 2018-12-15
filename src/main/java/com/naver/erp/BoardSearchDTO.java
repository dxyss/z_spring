package com.naver.erp;

public class BoardSearchDTO {
	private String keyword;				// �˻� Ű���� ����
	private String issue;
	private String keywordTarget;		// �˻� Ű������ Ÿ�� ����
	private int rowCntPerPage=15;		// �� ȭ�鿡 �������� �˻� ��� �ִ��� ���� ����. <����> �ݵ�� �ʱⰪ
	private int selectPageNo=1;			// ���� ���õ� ������ ��ȣ ����			<����> �ݵ�� �ʱⰪ �Է��� ��
	private int beginRowNo;				// ������ ��ȣ�� �´� ������ ��ȣ ����
	private int endRowNo;				// ������ ��ȣ�� �´� ���� ��ȣ ����
	
	
	
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
	