package com.naver.erp;

import java.util.Map;

public interface LoginDAO {
	// [�α��� ���̵�, ��ȣ ���� ����] �˻� �޼ҵ� ����
	int getAdminCnt(Map<String,String> admin_id_pwd);
}
