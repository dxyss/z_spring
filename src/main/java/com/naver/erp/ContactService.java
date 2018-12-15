package com.naver.erp;

import java.util.List;
import java.util.Map;
import com.naver.erp.*;

//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
// [ContactService �������̽�] ����
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
public interface ContactService {
	//*****************************************************
	// JSP �������� checkbox�� ���Ե� 
	// [����ó ����о� ���]�� �����ϴ�  �޼ҵ� ����
	//*****************************************************
	List<Map<String, String>>  getSaup_fieldList( );
	int getContactListAllCnt(ContactSearchDTO contactSearchDTO);
	List<Map<String, String>> getContactList(ContactSearchDTO contactSearchDTO);
	int insertContact(ContactDTO contactDTO);
	ContactDTO getContact(int contact_no);
	int updateContact(ContactDTO contactDTO);
	int deleteContact(int contact_no);
}

