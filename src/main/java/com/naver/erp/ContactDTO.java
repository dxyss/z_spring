
package com.naver.erp;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class ContactDTO{
	//*****************************************************
	// �Ӽ����� ����
	//*****************************************************
	private int contact_no;             // ����ó ��ȣ ����
	private String contact_name;        // ����ó�� ����
	private String phone;               // ��ȭ��ȣ ����
	private String reg_date;            // ����� ����	
	private List<Integer> saup_field;	// ����ó ����о� ����


	//*****************************************************
	// �޼ҵ� ����
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





























