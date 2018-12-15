package com.naver.erp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
//[���� Ŭ����]�� [ContactServiceImpl Ŭ����] ����
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
	// [���� Ŭ����]����  @Service �� @Transactional �� ���δ�.
	//------------------------------------------------------------
	//	@Service       => [���� Ŭ����] ���� �����ϰ� bean �±׷� �ڵ� ��ϵȴ�.
	//	@Transactional => [���� Ŭ����]�� �޼ҵ� ���ο��� �Ͼ�� ��� �۾����� [Ʈ�����]�� �ɸ���.
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
@Transactional  //(readOnly=false,rollbackFor=Exception.class)
@Service
//////////////////////////////////////////
public class ContactServiceImpl  implements ContactService {
//////////////////////////////////////////

	//**************************************************************************
	// �Ӽ����� contactDAO �����ϰ�, ContactDAO �������̽��� ������ Ŭ������ ��ü ������ ����.
	// ContactDAO �������̽��� ������ Ŭ�������� �� �ʿ䰡 ����.
	//**************************************************************************
		// @Autowired ���� => �Ӽ������� ���� �ڷ����� [�������̽�]�� ������ [Ŭ����]�� ��üȭ�Ͽ� �����Ѵ�.
		//                    [�������̽�]�� ������ [Ŭ����]�� 1���� �ƴϸ� ������ �߻��Ѵ�.
		//                    �� @Autowired( required=false )�� �����ϸ� 0�� �̾ �������� null �� ����ȴ�.
		//                    Spring������ �����ϴ� ������̼��̴�. 
		//                    �ڹٿ��� �����ϴ� @Inject �� ����Ҽ��� ������ required=false�� ���Ұ��� �ϴ�.
		//                    @Inject ����Ϸ��� pom.xml �� �Ʒ��� API ���� ������ �ؾ��Ѵ�.
		//                    <dependency><groupld>javax.inject
		//**************************************************************************
	@Autowired
	private ContactDAO contactDAO;

	//*****************************************************
	// JSP �������� select, checkbox, radio �� ���Ե� 
	// [����ó ����о� ���]�� �����ϴ�  �޼ҵ� ����
	//*****************************************************
	public List<Map<String, String>>  getSaup_fieldList( ){
		List<Map<String, String>>  saup_fieldList = this.contactDAO.getSaup_fieldList(  );
		return saup_fieldList;
	}
	
	public int getContactListAllCnt(ContactSearchDTO contactSearchDTO) {
		int contactListAllCnt = this.contactDAO.getContactListAllCnt(contactSearchDTO);
		return contactListAllCnt;
	}
	
	public List<Map<String, String>> getContactList(ContactSearchDTO contactSearchDTO){
		List<Map<String, String>> contactList = this.contactDAO.getContactList(contactSearchDTO);
		return contactList;
	}
	
	public int insertContact(ContactDTO contactDTO){
		// ����ó�� �Է��ϰ� �Է����� ���� ���
		int contactRegCnt = this.contactDAO.insertContact(contactDTO);
		// ����ó ����о߸� �Է��ϰ� �Է����� ���� ���
		contactRegCnt = contactRegCnt + this.contactDAO.insertContactSaup_field(contactDTO);
		return contactRegCnt;
	}
	
	public ContactDTO getContact(int contact_no) {
		ContactDTO contactDTO =this.contactDAO.getContact(contact_no);
		// ContactDTO ��ü�� null �ƴϸ�..
		if(contactDTO!=null) {
			//	[contactDAOImpl ��ü] �� getContact �޼ҵ� ȣ��� ����ó ����о� ��ȣ ���� ���
			List<Integer> contactSaup_fieldList = this.contactDAO.getContactSaup_field(contact_no);
			// ����ó ����о� ��ȣ ������ ContactDTO ��ü�� �Ӽ����� Saup_fieldList�� ����
			contactDTO.setSaup_field(contactSaup_fieldList);
		}
		return contactDTO;
	}
	
	public int updateContact(ContactDTO contactDTO) {
		int contact_no = contactDTO.getContact_no();
		
		// [1�� ����ó �⺻���� ����]�ϰ� �������� ���� �� ���
		// [1�� ����ó ����о� ����] �����ϰ� �������� ������ ���
		// [1�� ����ó ����о� ����] �Է��ϰ� �Է����� ������ ���
		int contactUpCnt = this.contactDAO.updateContact(contactDTO);
		if(contactUpCnt>0) {
			contactUpCnt  = contactUpCnt + this.contactDAO.deleteContactSaup_field(contact_no);
			contactUpCnt = contactUpCnt+this.contactDAO.insertContactSaup_field(contactDTO);
		}
		return contactUpCnt;
	}
	public int deleteContact(int contact_no) {
		
		int contactDelCnt = this.contactDAO.deleteContactSaup_field(contact_no);
		contactDelCnt = contactDelCnt + this.contactDAO.deleteContact(contact_no);
		return contactDelCnt;
	}
}