package com.naver.erp;

import java.util.List;
import java.util.Map;
import com.naver.erp.*;

//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
// [ContactService 인터페이스] 선언
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
public interface ContactService {
	//*****************************************************
	// JSP 페이지의 checkbox에 삽입될 
	// [연락처 사업분야 목록]을 리턴하는  메소드 선언
	//*****************************************************
	List<Map<String, String>>  getSaup_fieldList( );
	int getContactListAllCnt(ContactSearchDTO contactSearchDTO);
	List<Map<String, String>> getContactList(ContactSearchDTO contactSearchDTO);
	int insertContact(ContactDTO contactDTO);
	ContactDTO getContact(int contact_no);
	int updateContact(ContactDTO contactDTO);
	int deleteContact(int contact_no);
}

