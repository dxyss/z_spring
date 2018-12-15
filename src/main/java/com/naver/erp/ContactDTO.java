
package com.naver.erp;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class ContactDTO{
	//*****************************************************
	// 속성변수 선언
	//*****************************************************
	private int contact_no;             // 연락처 번호 저장
	private String contact_name;        // 연락처명 저장
	private String phone;               // 전화번호 저장
	private String reg_date;            // 등록일 저장	
	private List<Integer> saup_field;	// 연락처 사업분야 저장


	//*****************************************************
	// 메소드 선언
	//*****************************************************
	public int getContact_no() {
		return contact_no;
	}
	public void setContact_no(int contact_no) {
		this.contact_no = contact_no;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public List<Integer> getSaup_field() {
		return saup_field;
	}
	public void setSaup_field(List<Integer> saup_field) {
		this.saup_field = saup_field;
	}	
}





























