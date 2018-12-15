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
	
	// [ContactController Ŭ����] ������ @RequestMapping(~) �� ����
	// ��� �޼ҵ尡 ȣ��Ǳ� ���� �ڵ����� ȣ��Ǵ� �޼ҵ� ����
	
		// @ModelAttribute("Ű����") �� �ٴ� �޼ҵ��� ȣ�������?
			// ���� �޼ҵ� �� @RequestMapping(~)�� ���� �޼ҵ� ȣ�� ���� �ڵ����� ȣ��ȴ�
		// @ModelAttribute("Ű����")�� ���� �޼ҵ��� ���ϰ��� ��� ����ǳ�?
			//	HttpServletRequest ��ü�� @ModelAttribute("Ű����")������ Ű��������  ����ȴ�.
			// �׷��Ƿ� ���� �޼ҵ� �� @RequestMapping(~) �� ���� �޼ҵ� ȣ�� �� �̵��ϴ�
			// JSP ���������� EL�� ���� �� �� �ִ�.		
	
	@ModelAttribute("saup_fieldList")
	public List<Map<String,String>> getSaup_fieldList(){
		// DB�� �ִ� [����о�] ����� �˻��� List<Map<String,String>> ��ü�� �����ϱ�
		List<Map<String,String>> saup_fieldList = this.contactService.getSaup_fieldList();
		return saup_fieldList;
	}
	
	
	
	
	// �����ּ� /erp/contactSearchForm.do �� �����ϸ� ȣ��Ǵ� �޼ҵ� ����
		// [��Ʈ�ѷ� Ŭ����] �� �޼ҵ忡 @ResponseBody �� ����, @RequestMapping�� �ٰ�
		//	�޼ҵ��� �������� String �� ��� �����ϴ� ���ڿ��� ȣ���� JSP �������� �̴�.
	@RequestMapping(value="/contactSearchForm.do")
	public ModelAndView contactSearchForm(
			// ��� �Ķ���Ͱ��� ����� [ContactSearchDTO ��ü]�� �Ű������� ����
			// �� �Ķ���Ͱ����� ����ó �˻� ȭ���� �����ϴµ� �ʿ��� �����͵��̴�.
			@ModelAttribute("contactSearchDTO") ContactSearchDTO contactSearchDTO
//			,HttpSession session
	) {
		
		//System.out.println("1111111111");
		ModelAndView mav= new ModelAndView();
		// ModelAndView ��ü�� [ȣ�� JSP ��������]�� �����ϱ�
		mav.setViewName("contactSearchForm");
		try {
//			String admin_id = (String)session.getAttribute("admin_id");
//			if(admin_id==null) {
//				mav.setViewName("error");
//				mav.addObject("msg","�α��ιٶ�");
//				return mav;
//			}
			// [ContactServiceImpl ��ü]�� �޼ҵ� ȣ��� [�˻��� ����ó �� ����]�� ���
			int contactListAllCnt = this.contactService.getContactListAllCnt(contactSearchDTO);
			
			
			// PageingData2 Ŭ������ getPageingData �޼ҵ带 ȣ���Ͽ� .
			// ����¡ ó�� ���� �����͸� HashMap ��ü�� ���
			int selectPageNo = contactSearchDTO.getSelectPageNo();
			int rowCntPerPage = contactSearchDTO.getRowCntPerPage();
			Map<String,Integer> pageingData = PageingData2.getPageingData(
					contactListAllCnt,				// totRowCnt 
					selectPageNo,  					// selectPageNo
					rowCntPerPage,					// pageNoCntperPage
					10);
			
			// �˻� �� ������ ���� ���� ������ ��ȣ�� �����Ͽ�
			// BoardSearchDTO ��ü�� �� �Է��� �ش�
			// <��> �� ������ 3���ε� ���õ� ������ ��ȣ�� 3�̸� ����̴�
			//	 	�� ��� ���õ� ������ ��ȣ�� 1�� ������ �ش�.
			// �� ���� �۾��� getPageingData �޼ҵ� ȣ�� �� �۾��� �ش�.
			
			contactSearchDTO.setSelectPageNo(pageingData.get("selectPageNo"));
			// ������ ��ȣ �´� ������ ��ȣ�� ContactSearchDTO��ü�� �־��ش�.
			contactSearchDTO.setBeginRowNo(pageingData.get("beginRowNo"));
			contactSearchDTO.setEndRowNo(pageingData.get("endRowNo"));
			
			// [ContactServiceImpl ��ü]�� �޼ҵ� ȣ��� [�˻��� ����ó ���] ���
			List<Map<String,String>> contactList= this.contactService.getContactList(contactSearchDTO);
			
			// ModelAndView ��ü�� [�˻��� ����ó �� ����] �� �����ϱ�
			// ModelAndView ��ü�� ����� �����ʹ� ȣ���� JSP���� ������ HTML �ڵ��� ��︮�� �ȴ�.
			mav.addObject("contactListAllCnt",contactListAllCnt);
			
			// ModelAndView ��ü�� [����¡ ó�� ���� ������]�� ����� HashMap ��ü �����ϱ�
			mav.addObject("pageingData",pageingData);
			// ModelAndView ��ü�� [�˻��� ����ó ���]�� �����ϱ�
			mav.addObject("contactList",contactList);
			
		}catch(Exception e) {
			System.out.println("ContactController.contactSearchForm() �޼ҵ� ȣ�� �� ���ܹ߻�");
		}
		return mav;
	}
	
	// [��Ʈ�ѷ� Ŭ����]�� �޼ҵ忡 @ResponseBody �� ����, @RequestMapping�� �ٰ�
	// �޼ҵ��� �������� String �� ��� �����ϴ� ���ڿ��� ȣ���� JSP ������ ���̴�.
	@RequestMapping(value="/contactRegForm.do")
	public String contactRegForm() {
		
		return "contactRegForm";
	}
	
	
	
	// /erp/contactRegProc.do �� ���ٽ� ȣ��Ǵ� �޼ҵ� ����
	// @ResponseBody �� �������� �� �޼ҵ尡 �����ϴ� �����Ͱ� JSON���� ���汫�� Ŭ���̾�Ʈ���� ���۵ȴ�.
	@RequestMapping(value="/contactRegProc.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody		// �޼ҵ��� ���ϰ��� JSON���� �����Ͽ� Ŭ���̾�Ʈ���� �����ϴ� ������̼� ����
	public int contactRegProc(
			// ��� �Ķ���Ͱ��� ����� [ContactDTO ��ü]�� �Ű������� ����
			@ModelAttribute("contactDTO") ContactDTO contactDTO
	) {
		// ����ó �Է� ���� ���� ���� ���� ���� ����
		int contactRegCnt=0;
		try {
			// [ContactServiceImpl ��ü]�� �޼ҵ� ȣ��� [����ó �Է�]�ϰ� [�Է� ���� ����] ���
			contactRegCnt = this.contactService.insertContact(contactDTO);
			
			
		}catch(Exception e) {
			System.out.println("LoginController.contactRegProc() ���� ���ܹ߻�");
			contactRegCnt=-1;
		}
		return contactRegCnt;
	}
	
	
	@RequestMapping(value="/contactUpDelForm.do", method=RequestMethod.POST)
	public ModelAndView contactUpDelForm(@RequestParam("contact_no") int contact_no) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contactUpDelForm");
		try {
			// [ContactServiceImpl ��ü] ��  getContact �޼ҵ� ȣ��� [����/������ ����ó] ������ ���
			// ModelAndView ��ü�� ȣ�� JSP ���������� �ݿ��� [����/������ ����ó] �����ϱ�
			ContactDTO contact=this.contactService.getContact(contact_no);
			mav.addObject("contact",contact);
		}catch(Exception e) {
			System.out.println("ContactController.contactUpDelForm() �޼ҵ� ���� �߻�");
		}
		return mav;
	}
	
	
	
	
	//**************************************************************************
	// /erp/contactUpDelProc.do �� ���ٽ� ȣ��Ǵ� �޼ҵ� ����
	//**************************************************************************
	@RequestMapping(           
		// Ŭ�� ���� URL ����
		value = "/contactUpDelProc.do"     
		// Ŭ�� �Ķ���͸� ������ ����� post�� ����. �� post ������� ���� �����͸� �ްڴٴ� �ǹ�
		, method=RequestMethod.POST         
		// Ŭ�� ������� �� �ִ� ������ ���İ� ���ڼ� ����
		, produces="application/json;charset=UTF-8" 
	)
	@ResponseBody    
	public int contactUpDelProc(
			//-----------------------------------------------------
			// ��� �Ķ���Ͱ��� ����� [ContactDTO ��ü]�� �Ű������� ����
			// upDel ��� �Ķ���͸��� �Ķ���Ͱ��� ������ String �� �Ű����� ����
			//-----------------------------------------------------
			@ModelAttribute("contactDTO") ContactDTO contactDTO
			,@RequestParam( value="upDel", required=false )   String upDel
	){	
		//------------------------------
		// ���� �Ǵ� ���� ���� �� ���� �Ǵ� ���� ���� ���� ���� ���� ���� ����
		//------------------------------
		int contactUpDelCnt = 0;
		try{
			//------------------------------
			// ����ó ���� ��� �̸�
			// [ContactServiceImpl ��ü]�� �޼ҵ� ȣ��� [����ó ����]�� �� �������� ���� ���
			//------------------------------
			if( upDel.equals("up") ){			
				contactUpDelCnt = this.contactService.updateContact( contactDTO  );
			}
			//------------------------------
			// ����ó ���� ��� �̸�
			// [ContactServiceImpl ��ü]�� �޼ҵ� ȣ��� [����ó ����]�� �� �������� ���� ���
			//------------------------------
			else{
				contactUpDelCnt = this.contactService.deleteContact( contactDTO.getContact_no( )  );
			}
		}catch(Exception ex){
			contactUpDelCnt = -1;
			System.out.println( "ContactController.contactUpDelProc(~) �޼ҵ� ���� �߻�!");
		}  
		//------------------------------
		// ���� �Ǵ� ���� ���� �� ���� �Ǵ� ���� ���� ���� ����
		//------------------------------ 
		return contactUpDelCnt;
	}

}



	
