package com.naver.erp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.naver.erp.*;

//=======================================
//[BoardService �������̽�] ����
//=======================================
public interface LoginService {
   //---------------------------------
   //[�˻��� �Խ��� ��� ����] �����ϴ� �޼ҵ� ����
   //---------------------------------
   int getAdminCnt(Map<String,String> admin_id_pwd);
   
 
}
