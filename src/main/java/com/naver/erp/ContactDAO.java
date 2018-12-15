package com.naver.erp;

import java.util.List;
import java.util.Map;

public interface ContactDAO {
	//*****************************************************
	// JSP �������� select, checkbox, radio �� ���Ե� 
	// [����ó ����о� ���]�� �����ϴ�  �޼ҵ� ����
	//*****************************************************
	List<Map<String, String>>  getSaup_fieldList( );
	int getContactListAllCnt(ContactSearchDTO contactSearchDTO);
	List<Map<String, String>> getContactList(ContactSearchDTO contactSearchDTO);
	int insertContact(ContactDTO contactDTO);
	int insertContactSaup_field(ContactDTO contactDTO);
	ContactDTO getContact(int contact_no);
	List<Integer> getContactSaup_field(int contact_no);
	// [1�� ����ó �����ϰ� ���� ���� ����]�� �����ϴ� �޼ҵ� ����
	int updateContact(ContactDTO contactDTO);
	// [1�� ����ó�� ��� �о� �����ϰ� �������� ����]�� �����ϴ� �޼ҵ� ����
	int deleteContactSaup_field(int contact_no);
	int deleteContact(int contact_no);
}	
	
	

