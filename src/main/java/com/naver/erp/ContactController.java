package com.naver.erp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
	// 
	
	@Autowired
	private ContactService contactService;
	
	// [ContactController 클래스] 내부의 @RequestMapping(~) 이 붙은
	// 모든 메소드가 호출되기 전에 자동으로 호출되는 메소드 선언
	
		// @ModelAttribute("키값명") 이 붙는 메소드의 호출시점은?
			// 동료 메소드 중 @RequestMapping(~)이 붙은 메소드 호출 전에 자동으로 호출된다
		// @ModelAttribute("키값명")이 붙은 메소드의 리턴값은 어디에 저장되나?
			//	HttpServletRequest 객체에 @ModelAttribute("키값명")에서의 키값명으로  저장된다.
			// 그러므로 동료 메소드 중 @RequestMapping(~) 이 붙은 메소드 호출 후 이동하는
			// JSP 페이지에서 EL로 꺼내 볼 수 있다.		
	
	@ModelAttribute("saup_fieldList")
	public List<Map<String,String>> getSaup_fieldList(){
		// DB에 있는 [사업분야] 목록을 검색해 List<Map<String,String>> 객체에 저장하기
		List<Map<String,String>> saup_fieldList = this.contactService.getSaup_fieldList();
		return saup_fieldList;
	}
	
	
	
	
	// 가상주소 /erp/contactSearchForm.do 로 접속하면 호출되는 메소드 선언
		// [컨트롤러 클래스] 의 메소드에 @ResponseBody 가 없고, @RequestMapping이 붙고
		//	메소드의 리턴형이 String 일 경우 리턴하는 문자열은 호출할 JSP 페이지명 이다.
	@RequestMapping(value="/contactSearchForm.do")
	public ModelAndView contactSearchForm(
			// 모든 파라미터값이 저장된 [ContactSearchDTO 객체]를 매개변수로 선언
			// 이 파라미터값들은 연락처 검색 화면을 구현하는데 필요한 데이터들이다.
			@ModelAttribute("contactSearchDTO") ContactSearchDTO contactSearchDTO
//			,HttpSession session
	) {
		
		//System.out.println("1111111111");
		ModelAndView mav= new ModelAndView();
		// ModelAndView 객체에 [호출 JSP 페이지명]을 저장하기
		mav.setViewName("contactSearchForm");
		try {
//			String admin_id = (String)session.getAttribute("admin_id");
//			if(admin_id==null) {
//				mav.setViewName("error");
//				mav.addObject("msg","로그인바람");
//				return mav;
//			}
			// [ContactServiceImpl 객체]의 메소드 호출로 [검색한 연락처 총 개수]를 얻기
			int contactListAllCnt = this.contactService.getContactListAllCnt(contactSearchDTO);
			
			
			// PageingData2 클래스의 getPageingData 메소드를 호출하여 .
			// 페이징 처리 관련 데이터를 HashMap 객체로 얻기
			int selectPageNo = contactSearchDTO.getSelectPageNo();
			int rowCntPerPage = contactSearchDTO.getRowCntPerPage();
			Map<String,Integer> pageingData = PageingData2.getPageingData(
					contactListAllCnt,				// totRowCnt 
					selectPageNo,  					// selectPageNo
					rowCntPerPage,					// pageNoCntperPage
					10);
			
			// 검색 총 개수에 따른 선택 페이지 번호를 보정하여
			// BoardSearchDTO 객체에 재 입력해 준다
			// <예> 총 개수가 3개인데 선택된 페이지 번호가 3이면 모순이다
			//	 	이 경우 선택된 페이지 번호를 1로 보정해 준다.
			// 이 보정 작업은 getPageingData 메소드 호출 시 작업해 준다.
			
			contactSearchDTO.setSelectPageNo(pageingData.get("selectPageNo"));
			// 페이지 번호 맞는 시작행 번호를 ContactSearchDTO객체에 넣어준다.
			contactSearchDTO.setBeginRowNo(pageingData.get("beginRowNo"));
			contactSearchDTO.setEndRowNo(pageingData.get("endRowNo"));
			
			// [ContactServiceImpl 객체]의 메소드 호출로 [검색한 연락처 목록] 얻기
			List<Map<String,String>> contactList= this.contactService.getContactList(contactSearchDTO);
			
			// ModelAndView 객체에 [검색한 연락처 총 개수] 를 저장하기
			// ModelAndView 객체에 저장된 데이터는 호출할 JSP에서 꺼내어 HTML 코딩과 어울리게 된다.
			mav.addObject("contactListAllCnt",contactListAllCnt);
			
			// ModelAndView 객체에 [페이징 처리 관련 데이터]가 저장된 HashMap 객체 저장하기
			mav.addObject("pageingData",pageingData);
			// ModelAndView 객체에 [검색한 연락처 목록]을 저장하기
			mav.addObject("contactList",contactList);
			
		}catch(Exception e) {
			System.out.println("ContactController.contactSearchForm() 메소드 호출 시 예외발생");
		}
		return mav;
	}
	
	// [컨트롤러 클래스]의 메소드에 @ResponseBody 가 없고, @RequestMapping이 붙고
	// 메소드의 리턴형이 String 일 경우 리턴하는 문자열은 호출할 JSP 페이지 명이다.
	@RequestMapping(value="/contactRegForm.do")
	public String contactRegForm() {
		
		return "contactRegForm";
	}
	
	
	
	// /erp/contactRegProc.do 로 접근시 호출되는 메소드 선언
	// @ResponseBody 를 붙음으로 이 메소드가 리턴하는 데이터가 JSON으로 변경괴더 클라이언트에게 전송된다.
	@RequestMapping(value="/contactRegProc.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody		// 메소드의 리턴값을 JSON으로 변경하여 클라이언트에게 전송하는 어노테이션 설정
	public int contactRegProc(
			// 모든 파라미터값이 저장된 [ContactDTO 객체]를 매개변수로 선언
			@ModelAttribute("contactDTO") ContactDTO contactDTO
	) {
		// 연락처 입력 적용 행의 개수 저장 변수 선언
		int contactRegCnt=0;
		try {
			// [ContactServiceImpl 객체]의 메소드 호출로 [연락처 입력]하고 [입력 행의 개수] 얻기
			contactRegCnt = this.contactService.insertContact(contactDTO);
			
			
		}catch(Exception e) {
			System.out.println("LoginController.contactRegProc() 에서 예외발생");
			contactRegCnt=-1;
		}
		return contactRegCnt;
	}
	
	
	@RequestMapping(value="/contactUpDelForm.do", method=RequestMethod.POST)
	public ModelAndView contactUpDelForm(@RequestParam("contact_no") int contact_no) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contactUpDelForm");
		try {
			// [ContactServiceImpl 객체] 의  getContact 메소드 호출로 [수정/삭제할 연락처] 데이터 얻기
			// ModelAndView 객체에 호출 JSP 페이지에서 반영할 [수정/삭제할 연락처] 저장하기
			ContactDTO contact=this.contactService.getContact(contact_no);
			mav.addObject("contact",contact);
		}catch(Exception e) {
			System.out.println("ContactController.contactUpDelForm() 메소드 예외 발생");
		}
		return mav;
	}
	
	
	
	
	//**************************************************************************
	// /erp/contactUpDelProc.do 로 접근시 호출되는 메소드 선언
	//**************************************************************************
	@RequestMapping(           
		// 클의 접속 URL 설정
		value = "/contactUpDelProc.do"     
		// 클이 파라미터를 보내는 방법은 post로 설정. 즉 post 방식으로 보낸 데이터만 받겠다는 의미
		, method=RequestMethod.POST         
		// 클이 응답받을 수 있는 데이터 형식과 문자셋 지정
		, produces="application/json;charset=UTF-8" 
	)
	@ResponseBody    
	public int contactUpDelProc(
			//-----------------------------------------------------
			// 모든 파라미터값이 저장된 [ContactDTO 객체]를 매개변수로 선언
			// upDel 라는 파라미터명의 파라미터값을 저장할 String 형 매개변수 선언
			//-----------------------------------------------------
			@ModelAttribute("contactDTO") ContactDTO contactDTO
			,@RequestParam( value="upDel", required=false )   String upDel
	){	
		//------------------------------
		// 수정 또는 삭제 실행 후 수정 또는 삭제 적용 행의 개수 저장 변수 선언
		//------------------------------
		int contactUpDelCnt = 0;
		try{
			//------------------------------
			// 연락처 수정 모드 이면
			// [ContactServiceImpl 객체]의 메소드 호출로 [연락처 수정]한 후 적용행의 개수 얻기
			//------------------------------
			if( upDel.equals("up") ){			
				contactUpDelCnt = this.contactService.updateContact( contactDTO  );
			}
			//------------------------------
			// 연락처 삭제 모드 이면
			// [ContactServiceImpl 객체]의 메소드 호출로 [연락처 삭제]한 후 적용행의 개수 얻기
			//------------------------------
			else{
				contactUpDelCnt = this.contactService.deleteContact( contactDTO.getContact_no( )  );
			}
		}catch(Exception ex){
			contactUpDelCnt = -1;
			System.out.println( "ContactController.contactUpDelProc(~) 메소드 예외 발생!");
		}  
		//------------------------------
		// 수정 또는 삭제 실행 후 수정 또는 삭제 적용 행의 개수
		//------------------------------ 
		return contactUpDelCnt;
	}

}



	
