package com.naver.erp;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginDAO loginDAO;
	
	public int getAdminCnt(Map<String,String> admin_id_pwd) {
		int adminCnt = this.loginDAO.getAdminCnt(admin_id_pwd);
		return adminCnt;
	}
}
