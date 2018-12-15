package com.naver.erp;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDAOImple implements ContactDAO{
	@Autowired
	   private SqlSessionTemplate sqlSession;
	
	//********************************************************************
		// JSP �������� select, checkbox, radio �� ���Ե� 
		// [����ó ����о� ���]�� �����ϴ�  �޼ҵ� ����
		//********************************************************************
		public List<Map<String, String>>  getSaup_fieldList( ){
			//-------------------------------------------------------------------------------------
			// SqlSessionTemplate ��ü�� [selectList(~) �޼ҵ�] ȣ��� [��� ���� ���]�� ����.
			//-------------------------------------------------------------------------------------
			// sqlSession.selectList( "com.naver.erp.ContactDAO.getSaup_fieldList" ); �ǹ�
			//-------------------------------------------------------------------------------------
				// ���̹�Ƽ�� SQL ���� ���� XML����(=mapper_contact.xml))����
				// <mapper namespace="com.naver.erp.ContactDAO">�±� ������
				// <select id="getSaup_fieldList" ~> �±� ������ 
				// [n�� ���� select ������]�� �����Ͽ� ���� �����͸�  List<Map<String, String>> ��ü ��� �����Ѵ�.
				// 2��° ���ڴ� [n�� ���� select ������]�� ���Ե� �������̴�.
				// ���� �ڷ����� <select id="getSaup_fieldList" ~> �±��� resultType �Ӽ����� �����Ѵ�
			//-------------------------------------------------------------------------------------
			List<Map<String, String>>  saup_fieldList 
				= sqlSession.selectList(  "com.naver.erp.ContactDAO.getSaup_fieldList" );
			return saup_fieldList;
		}
		
		public int getContactListAllCnt(ContactSearchDTO contactSearchDTO) {
			// SqlSessionTemplate ��ü�� selectOne �޼ҵ� ȣ��� [�˻� ����ó �� ���� ����]�� ����
			// selectOne(" com.naver.erp.ContactDAO.getContactListAllCnt", comtactSearchDTO); �ǹ�
				// ���̹�Ƽ�� SQL ���� ���� XML����(=mapper_contact.xml))����
				// <mapper namespace="com.naver.erp.ContactDAO">�±� ������
				//	<select id="getContactListAllCnt"~> �±� ������
				//	[1�� ���� select ������]�� �����ϰ� ���� �����͸� int �� �����Ѵ�.
				// 	2��° ���ڴ� [1�� n�� ���� select ������] �� ���Ե� �������̴�.
				// 	
			int contactListAllCnt = sqlSession.selectOne("com.naver.erp.ContactDAO.getContactListAllCnt",contactSearchDTO);
			return contactListAllCnt;
		}
		
		public List<Map<String, String>> getContactList(ContactSearchDTO contactSearchDTO){
			List<Map<String, String>> contactList = sqlSession.selectList("com.naver.erp.ContactDAO.getContactList",contactSearchDTO);
			return contactList;
		}
		
		public int insertContact(ContactDTO contactDTO){
			int contactRegCnt = sqlSession.insert("com.naver.erp.ContactDAO.insertContact",contactDTO);
			return contactRegCnt;
		}
		public int insertContactSaup_field(ContactDTO contactDTO) {
			int saup_fieldRegCnt = sqlSession.insert("com.naver.erp.ContactDAO.insertContactSaup_field",contactDTO);
			return saup_fieldRegCnt;
		}
		
		public ContactDTO getContact(int contact_no) {
			ContactDTO contactDTO = sqlSession.selectOne("com.naver.erp.ContactDAO.getContact",contact_no);
			return contactDTO;
		}
		
		public List<Integer> getContactSaup_field(int contact_no){
			List<Integer> ContactSaup_fieldList = sqlSession.selectList("com.naver.erp.ContactDAO.getContactSaup_field",contact_no);
			return ContactSaup_fieldList;
		}
		
		//********************************************************************
		// [1�� ����ó �����ϰ� ���� ���� ����]�� �����ϴ� �޼ҵ� ����
		//********************************************************************
		public int  updateContact( ContactDTO contactDTO ){
			//-------------------------------------------------------------------------------------
			// SqlSessionTemplate ��ü�� update �޼ҵ� ȣ��� ���� ���� �� ���� ���� ������ ����.
			//-------------------------------------------------------------------------------------
			// update( "com.naver.erp.ContactDao.updateContact", contactDto ); �ǹ�
			//-------------------------------------------------------------------------------------
				// ���̹�Ƽ�� SQL ���� ���� XML����(=mapper_contact.xml))����
				// <mapper namespace="com.naver.erp.ContactDao">�±� ������
				// <update id="updateContact" ~> �±� ������ 
				// update �������� �����ϰ� ���� ���� ���� ������ �����Ѵ�.
			//-------------------------------------------------------------------------------------
			return sqlSession.update(
				"com.naver.erp.ContactDAO.updateContact", contactDTO
			);
		}

		//********************************************************************
		// [1�� ����ó�� ��� �о� �����ϰ� ���� ���� ����]�� �����ϴ� �޼ҵ� ����
		//********************************************************************
		public int  deleteContactSaup_field( int contact_no ){
			//-------------------------------------------------------------------------------------
			// SqlSessionTemplate ��ü�� delete �޼ҵ� ȣ��� [1�� ����ó ��� ����] ���� ���� �� ���� ���� ������ ����.
			//-------------------------------------------------------------------------------------
			// delete( "com.naver.erp.ContactDao.deleteContactSaup_field", contactDto ); �ǹ�
			//-------------------------------------------------------------------------------------
				// ���̹�Ƽ�� SQL ���� ���� XML����(=mapper_contact.xml))����
				// <mapper namespace="com.naver.erp.ContactDao">�±� ������
				// <delete id="deleteContactSaup_field" ~> �±� ������ 
				// delete �������� �����ϰ� ���� ���� ������ �����Ѵ�.
			return sqlSession.delete(
				"com.naver.erp.ContactDAO.deleteContactSaup_field", contact_no
			);
		}

		//********************************************************************
		// [1�� ����ó �����ϰ� ���� ���� ����]�� �����ϴ� �޼ҵ� ����
		//********************************************************************
		public int  deleteContact( int contact_no ){
			//-------------------------------------------------------------------------------------
			// SqlSessionTemplate ��ü�� delete �޼ҵ� ȣ��� 1�� ����ó ���� ���� ���� �� ���� ���� ������ ����.
			//-------------------------------------------------------------------------------------
			// delete( "com.naver.erp.ContactDao.deleteContact", contact_no ); �ǹ�
			//-------------------------------------------------------------------------------------
				// ���̹�Ƽ�� SQL ���� ���� XML����(=mapper_contact.xml))����
				// <mapper namespace="com.naver.erp.ContactDao">�±� ������
				// <delete id="deleteContact" ~> �±� ������ 
				// delete �������� �����ϰ� ���� ���� ������ �����Ѵ�.
			return (int)sqlSession.delete(
				"com.naver.erp.ContactDAO.deleteContact", contact_no
			);
		}

}
