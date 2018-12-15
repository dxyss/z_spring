package com.naver.erp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
//[서비스 클래스]인 [ContactServiceImpl 클래스] 선언
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
	// [서비스 클래스]에는  @Service 와 @Transactional 를 붙인다.
	//------------------------------------------------------------
	//	@Service       => [서비스 클래스] 임을 지정하고 bean 태그로 자동 등록된다.
	//	@Transactional => [서비스 클래스]의 메소드 내부에서 일어나는 모든 작업에는 [트랜잭션]이 걸린다.
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
@Transactional  //(readOnly=false,rollbackFor=Exception.class)
@Service
//////////////////////////////////////////
public class ContactServiceImpl  implements ContactService {
//////////////////////////////////////////

	//**************************************************************************
	// 속성변수 contactDAO 선언하고, ContactDAO 인터페이스를 구현한 클래스를 객체 생성해 저장.
	// ContactDAO 인터페이스를 구현한 클래스명은 알 필요가 없다.
	//**************************************************************************
		// @Autowired 역할 => 속성변수에 붙은 자료형인 [인터페이스]를 구현한 [클래스]를 객체화하여 저장한다.
		//                    [인터페이스]를 구현한 [클래스]가 1개가 아니면 에러가 발생한다.
		//                    단 @Autowired( required=false )로 선언하면 0개 이어도 에러없이 null 이 저장된다.
		//                    Spring에서만 지원하는 어노테이션이다. 
		//                    자바에서 지원하는 @Inject 를 사용할수도 있으나 required=false는 사용불가능 하다.
		//                    @Inject 사용하려면 pom.xml 에 아래를 API 수입 설정을 해야한다.
		//                    <dependency><groupld>javax.inject
		//**************************************************************************
	@Autowired
	private ContactDAO contactDAO;

	//*****************************************************
	// JSP 페이지의 select, checkbox, radio 에 삽입될 
	// [연락처 사업분야 목록]을 리턴하는  메소드 선언
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
		// 연락처를 입력하고 입력행의 개수 얻기
		int contactRegCnt = this.contactDAO.insertContact(contactDTO);
		// 연락처 사업분야를 입력하고 입력행의 개수 얻기
		contactRegCnt = contactRegCnt + this.contactDAO.insertContactSaup_field(contactDTO);
		return contactRegCnt;
	}
	
	public ContactDTO getContact(int contact_no) {
		ContactDTO contactDTO =this.contactDAO.getContact(contact_no);
		// ContactDTO 객체가 null 아니면..
		if(contactDTO!=null) {
			//	[contactDAOImpl 객체] 의 getContact 메소드 호출로 연락처 사업분야 번호 정보 얻기
			List<Integer> contactSaup_fieldList = this.contactDAO.getContactSaup_field(contact_no);
			// 연락처 사업분야 번호 정보를 ContactDTO 객체의 속성변수 Saup_fieldList에 저장
			contactDTO.setSaup_field(contactSaup_fieldList);
		}
		return contactDTO;
	}
	
	public int updateContact(ContactDTO contactDTO) {
		int contact_no = contactDTO.getContact_no();
		
		// [1개 연락처 기본정보 수정]하고 수정행의 개수 를 얻기
		// [1개 연락처 사업분야 정보] 삭제하고 삭제행의 개수를 얻기
		// [1개 연락처 사업분야 정보] 입력하고 입력행의 개수를 얻기
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