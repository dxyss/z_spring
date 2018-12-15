package com.naver.erp;

import java.util.List;
import java.util.Map;

public interface ContactDAO {
	//*****************************************************
	// JSP 페이지의 select, checkbox, radio 에 삽입될 
	// [연락처 사업분야 목록]을 리턴하는  메소드 선언
	//*****************************************************
	List<Map<String, String>>  getSaup_fieldList( );
	int getContactListAllCnt(ContactSearchDTO contactSearchDTO);
	List<Map<String, String>> getContactList(ContactSearchDTO contactSearchDTO);
	int insertContact(ContactDTO contactDTO);
	int insertContactSaup_field(ContactDTO contactDTO);
	ContactDTO getContact(int contact_no);
	List<Integer> getContactSaup_field(int contact_no);
	// [1개 연락처 수정하고 수정 행의 개수]를 리턴하는 메소드 선언
	int updateContact(ContactDTO contactDTO);
	// [1개 연락처의 사업 분야 삭제하고 삭제행의 개수]를 리턴하는 메소드 선언
	int deleteContactSaup_field(int contact_no);
	int deleteContact(int contact_no);
}	
	
	

