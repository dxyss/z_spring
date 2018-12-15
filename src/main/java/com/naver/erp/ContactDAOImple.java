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
		// JSP 페이지의 select, checkbox, radio 에 삽입될 
		// [연락처 사업분야 목록]을 리턴하는  메소드 선언
		//********************************************************************
		public List<Map<String, String>>  getSaup_fieldList( ){
			//-------------------------------------------------------------------------------------
			// SqlSessionTemplate 객체의 [selectList(~) 메소드] 호출로 [사업 영역 목록]를 리턴.
			//-------------------------------------------------------------------------------------
			// sqlSession.selectList( "com.naver.erp.ContactDAO.getSaup_fieldList" ); 의미
			//-------------------------------------------------------------------------------------
				// 마이바티스 SQL 구문 설정 XML파일(=mapper_contact.xml))에서
				// <mapper namespace="com.naver.erp.ContactDAO">태그 내부의
				// <select id="getSaup_fieldList" ~> 태그 내부의 
				// [n행 리턴 select 쿼리문]을 실행하여 얻은 데이터를  List<Map<String, String>> 객체 담아 리턴한다.
				// 2번째 인자는 [n행 리턴 select 쿼리문]에 삽입될 데이터이다.
				// 리턴 자료형은 <select id="getSaup_fieldList" ~> 태그의 resultType 속성값에 설정한다
			//-------------------------------------------------------------------------------------
			List<Map<String, String>>  saup_fieldList 
				= sqlSession.selectList(  "com.naver.erp.ContactDAO.getSaup_fieldList" );
			return saup_fieldList;
		}
		
		public int getContactListAllCnt(ContactSearchDTO contactSearchDTO) {
			// SqlSessionTemplate 객체의 selectOne 메소드 호출로 [검색 연락처 총 행의 개수]를 리턴
			// selectOne(" com.naver.erp.ContactDAO.getContactListAllCnt", comtactSearchDTO); 의미
				// 마이바티스 SQL 구문 설정 XML파일(=mapper_contact.xml))에서
				// <mapper namespace="com.naver.erp.ContactDAO">태그 내부의
				//	<select id="getContactListAllCnt"~> 태그 내부의
				//	[1행 리턴 select 쿼리문]을 실행하고 얻은 데이터를 int 로 리턴한다.
				// 	2번째 인자는 [1행 n열 리턴 select 쿼리문] 에 삽입될 데이터이다.
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
		// [1개 연락처 수정하고 수정 행의 개수]를 리턴하는 메소드 선언
		//********************************************************************
		public int  updateContact( ContactDTO contactDTO ){
			//-------------------------------------------------------------------------------------
			// SqlSessionTemplate 객체의 update 메소드 호출로 수정 실행 후 적용 행의 개수를 리턴.
			//-------------------------------------------------------------------------------------
			// update( "com.naver.erp.ContactDao.updateContact", contactDto ); 의미
			//-------------------------------------------------------------------------------------
				// 마이바티스 SQL 구문 설정 XML파일(=mapper_contact.xml))에서
				// <mapper namespace="com.naver.erp.ContactDao">태그 내부의
				// <update id="updateContact" ~> 태그 내부의 
				// update 쿼리문을 실행하고 얻은 수정 행의 개수를 리턴한다.
			//-------------------------------------------------------------------------------------
			return sqlSession.update(
				"com.naver.erp.ContactDAO.updateContact", contactDTO
			);
		}

		//********************************************************************
		// [1개 연락처의 사업 분야 삭제하고 삭제 행의 개수]를 리턴하는 메소드 선언
		//********************************************************************
		public int  deleteContactSaup_field( int contact_no ){
			//-------------------------------------------------------------------------------------
			// SqlSessionTemplate 객체의 delete 메소드 호출로 [1개 연락처 사업 정보] 삭제 실행 후 적용 행의 개수를 리턴.
			//-------------------------------------------------------------------------------------
			// delete( "com.naver.erp.ContactDao.deleteContactSaup_field", contactDto ); 의미
			//-------------------------------------------------------------------------------------
				// 마이바티스 SQL 구문 설정 XML파일(=mapper_contact.xml))에서
				// <mapper namespace="com.naver.erp.ContactDao">태그 내부의
				// <delete id="deleteContactSaup_field" ~> 태그 내부의 
				// delete 쿼리문을 실행하고 삭제 행의 개수를 리턴한다.
			return sqlSession.delete(
				"com.naver.erp.ContactDAO.deleteContactSaup_field", contact_no
			);
		}

		//********************************************************************
		// [1개 연락처 삭제하고 삭제 행의 개수]를 리턴하는 메소드 선언
		//********************************************************************
		public int  deleteContact( int contact_no ){
			//-------------------------------------------------------------------------------------
			// SqlSessionTemplate 객체의 delete 메소드 호출로 1개 연락처 정보 삭제 실행 후 적용 행의 개수를 리턴.
			//-------------------------------------------------------------------------------------
			// delete( "com.naver.erp.ContactDao.deleteContact", contact_no ); 의미
			//-------------------------------------------------------------------------------------
				// 마이바티스 SQL 구문 설정 XML파일(=mapper_contact.xml))에서
				// <mapper namespace="com.naver.erp.ContactDao">태그 내부의
				// <delete id="deleteContact" ~> 태그 내부의 
				// delete 쿼리문을 실행하고 삭제 행의 개수를 리턴한다.
			return (int)sqlSession.delete(
				"com.naver.erp.ContactDAO.deleteContact", contact_no
			);
		}

}
